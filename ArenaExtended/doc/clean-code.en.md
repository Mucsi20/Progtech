# Clean Code (Java)

Code is clean if it can be understood easily — by everyone on the team. Clean code can be read and enhanced by a developer other than its original author. With understandability comes readability, changeability, extensibility and maintainability.

This guide restates the rules with **Java-specific examples**. Prefer the ✓ version.

---

## General rules

### Follow standard conventions

Use Java naming conventions: `PascalCase` for classes, `camelCase` for methods and fields, `UPPER_SNAKE_CASE` for constants. Follow the [Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) and your team's formatter (Checkstyle, Spotless, the IDE default).

```java
// ✗ Inconsistent, non-Java names
public class person_record {
    public int Age;
    public void SET_name(String n) { }
}

// ✓ Standard Java conventions
public class Person {
    public static final int DEFAULT_AGE = 0;
    private int age;

    public void setName(String name) { }
}
```

### Keep it simple stupid (KISS)

Simpler is always better. Reduce complexity as much as possible. Do not add a framework, a pattern, or an extra class until you need it.

```java
// ✗ Over-engineered for a three-item queue demo
public abstract class AbstractBoundedFifoQueueFactory<T> { /* ... */ }

// ✓ A list is enough
public class Queue {
    private final ArrayList<String> data = new ArrayList<>();

    public void push(String element) {
        data.add(element);
    }
}
```

### Boy Scout rule

Leave the campground cleaner than you found it. When you touch a method, fix a misleading name, drop a dead comment, or extract a tiny helper — even if that was not the ticket.

```java
// You opened this method to add a print. Also rename the unclear flag.
public void printPerson(Person person) {
    System.out.println(person.getName() + " (" + person.getAge() + ")");
}
```

### Always find the root cause

Do not patch the symptom. If `pop()` returns `null` and callers keep crashing, the bug may be an empty-queue contract — not a missing `if (result != null)` in the tenth caller.

```java
// ✗ Symptom: hide the empty queue at every call site
String name = queue.pop();
if (name == null) {
    name = "";
}

// ✓ Root cause: define the empty-queue behaviour in one place
public Optional<String> pop() {
    if (data.isEmpty()) {
        return Optional.empty();
    }
    return Optional.of(data.remove(0));
}
```

---

## Design rules

### Keep configurable data at high levels

Constants, limits, and policy values belong near `main` or a config object — not buried in a helper.

```java
// ✗ Hidden policy
public class Calc {
    public static void main(String[] args) {
        new CalcGUI(); // font size 30 is hardcoded inside CalcGUI
    }
}

// ✓ Configurable at the top
public class Calc {
    private static final int DISPLAY_FONT_SIZE = 30;

    public static void main(String[] args) {
        new CalcGUI(DISPLAY_FONT_SIZE);
    }
}
```

### Prefer polymorphism to if/else or switch/case

When behaviour changes with a type, give each type its own class.

```java
// ✗ Growing switch
public double pay(Employee employee) {
    switch (employee.getType()) {
        case HOURLY:
            return employee.getHours() * employee.getRate();
        case SALARIED:
            return employee.getSalary() / 12;
        case INTERN:
            return 0;
        default:
            throw new IllegalStateException();
    }
}

// ✓ Each type knows how it is paid
public interface Employee {
    double monthlyPay();
}

public final class HourlyEmployee implements Employee {
    private final double hours;
    private final double rate;

    public HourlyEmployee(double hours, double rate) {
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double monthlyPay() {
        return hours * rate;
    }
}
```

### Separate multi-threading code

Keep locks, executors, and `synchronized` in a thin wrapper. Domain classes stay single-threaded and easy to test.

```java
// ✗ Queue mixed with concurrency
public class Queue {
    private final List<String> data = new ArrayList<>();

    public synchronized void push(String e) { data.add(e); }
    public synchronized String pop() { return data.remove(0); }
}

// ✓ Domain object + concurrent facade
public class Queue {
    private final List<String> data = new ArrayList<>();
    public void push(String e) { data.add(e); }
    public String pop() { return data.remove(0); }
}

public class ConcurrentQueue {
    private final Queue queue = new Queue();
    private final Object lock = new Object();

    public void push(String e) {
        synchronized (lock) {
            queue.push(e);
        }
    }
}
```

### Prevent over-configurability

Do not expose every internal knob. A calculator that takes font size is fine; a calculator with 20 optional builder flags is not.

```java
// ✗ Everything is configurable
new CalcGUI(fontSize, fontName, theme, beepOnError, animationMs, logPath, locale, ...);

// ✓ Only the decisions that actually vary
new CalcGUI(30);
```

### Use dependency injection

Pass collaborators in. Do not construct hidden infrastructure inside the class that uses it.

```java
// ✗ Hidden dependency
public class PeopleReport {
    public void printAll() {
        Database db = new Database("jdbc:h2:mem:people");
        db.findAll().forEach(System.out::println);
    }
}

// ✓ Injected dependency
public class PeopleReport {
    private final PersonRepository people;

    public PeopleReport(PersonRepository people) {
        this.people = people;
    }

    public void printAll() {
        people.findAll().forEach(System.out::println);
    }
}
```

### Follow the Law of Demeter

A class should know only its direct dependencies. Do not reach through objects (`a.getB().getC().doX()`).

```java
// ✗ Talking to strangers
String city = order.getCustomer().getAddress().getCity();

// ✓ Ask a direct neighbour
String city = order.deliveryCity();
```

---

## Understandability tips

### Be consistent

If you do something a certain way, do all similar things the same way. If `Queue` uses `push` / `pop` / `isEmpty`, do not add `enqueue` later.

```java
// ✗ Mixed vocabulary for the same idea
queue.push("Ada");
queue.enqueue("Alan");

// ✓ One pair of names
queue.push("Ada");
queue.push("Alan");
```

### Use explanatory variables

Name the intermediate result instead of nesting expressions.

```java
// ✗ What is this checking?
if (person.getName().trim().split(" ").length >= 2 && person.getAge() >= 18) { }

// ✓ Intention is visible
boolean hasFullName = person.getName().trim().split(" ").length >= 2;
boolean isAdult = person.getAge() >= 18;
if (hasFullName && isAdult) { }
```

### Encapsulate boundary conditions

Boundary conditions are hard to keep track of. Put the processing for them in one place.

```java
// ✗ Empty-queue check copied in top() and pop()
if (data.isEmpty()) {
    return null;
}

// ✓ One helper
private boolean hasElements() {
    return !data.isEmpty();
}

public String top() {
    return hasElements() ? data.get(0) : null;
}
```

### Prefer dedicated value objects to primitive types

A raw `int` can mean age, font size, or a count. A small type documents the meaning and can validate itself.

```java
// ✗ Primitive obsession
public Person(String name, int age) { }

// ✓ Meaning and validation in one place
public final class Age {
    private final int years;

    public Age(int years) {
        if (years < 0 || years > 150) {
            throw new IllegalArgumentException("age out of range: " + years);
        }
        this.years = years;
    }

    public int years() {
        return years;
    }
}

public Person(String name, Age age) { }
```

### Avoid logical dependency

Do not write methods that only work if another method on the same class was called first.

```java
// ✗ init() must run before print()
public class People {
    private List<Person> loaded;

    public void init() {
        loaded = List.of(new Person("Doug", 31));
    }

    public void print() {
        loaded.forEach(p -> System.out.println(p.getName()));
    }
}

// ✓ The method owns what it needs
public void print(List<Person> people) {
    people.forEach(p -> System.out.println(p.getName()));
}
```

### Avoid negative conditionals

Positive names are easier to read than a pile of `!` operators.

```java
// ✗
if (!queue.isEmpty()) {
    process(queue.pop());
}

// ✓
if (queue.hasElements()) {
    process(queue.pop());
}
```

---

## Names rules

### Choose descriptive and unambiguous names

```java
// ✗
Person p1 = new Person("Doug", 31);
int d;

// ✓
Person firstGuest = new Person("Doug", 31);
int delayInSeconds;
```

### Make meaningful distinctions

Do not add noise words that do not change the meaning.

```java
// ✗
class PersonInfo { }
class PersonData { }
class PersonObject { }

// ✓
class Person { }
class PersonName { }
class RegisteredPerson { }
```

### Use pronounceable names

If you cannot say it in a standup, rename it.

```java
// ✗
private int genymdhms;
private String nm;

// ✓
private int generationTimestamp;
private String name;
```

### Use searchable names

Single-letter names and magic numbers cannot be grepped usefully.

```java
// ✗
if (e.length() > 7) { }

// ✓
private static final int MIN_PASSWORD_LENGTH = 8;
if (password.length() >= MIN_PASSWORD_LENGTH) { }
```

### Replace magic numbers with named constants

```java
// ✗
new CalcGUI(30);

// ✓
private static final int DISPLAY_FONT_SIZE = 30;
new CalcGUI(DISPLAY_FONT_SIZE);
```

### Avoid encodings

Do not append prefixes or type information (`strName`, `iAge`, Hungarian notation, `I` prefixes on every interface).

```java
// ✗
String strName;
int iAge;
IPersonRepository iRepo;

// ✓
String name;
int age;
PersonRepository people;
```

---

## Functions rules

### Small

A method should fit on a screen and do something you can name in one breath.

```java
// ✗ One method that parses, validates, stores, and prints
public void handle(String line) { /* 80 lines */ }

// ✓
public void handle(String line) {
    Person person = parse(line);
    people.add(person);
    System.out.println(person.getName());
}
```

### Do one thing

```java
// ✗ pop() also logs and updates a counter
public String pop() {
    System.out.println("pop");
    count++;
    return data.remove(0);
}

// ✓ pop() only removes the head
public String pop() {
    return data.remove(0);
}
```

### Use descriptive names

The name is the documentation. `empty()` vs `clear()`: pick the one that matches the team's language (`Queue.empty()` in this project means “remove all”).

```java
// ✗
public void f(Person p) { }

// ✓
public void changeName(Person person) { }
```

### Prefer fewer arguments

Zero or one is ideal; three is already a crowd. Bundle related values.

```java
// ✗
public void register(String name, int age, String city, String zip) { }

// ✓
public void register(Person person, Address address) { }
```

### Have no side effects

A method should not silently change something the caller did not ask for.

```java
// ✗ checkPassword also initializes a session
public boolean checkPassword(String password) {
    session.start();
    return hash(password).equals(storedHash);
}

// ✓
public boolean checkPassword(String password) {
    return hash(password).equals(storedHash);
}
```

Java primitives are passed by value; objects share a reference. Changing a `Person` inside a method is a side effect the caller will see. Changing an `int` parameter is not.

```java
changeName(p2);          // p2's name changes
changeInt(i);            // i is unchanged
i = increaseInt(i);      // only assignment updates i
```

### Don't use flag arguments

Split the method so the client calls the behaviour it wants.

```java
// ✗
public void print(Person person, boolean includeAge) { }

// ✓
public void printName(Person person) { }
public void printNameAndAge(Person person) { }
```

---

## Comments rules

### Always try to explain yourself in code

```java
// ✗
// check if the person is an adult
if (person.getAge() >= 18) { }

// ✓
if (person.isAdult()) { }
```

### Don't be redundant

```java
// ✗
/** Returns the name. */
public String getName() {
    return name;
}
```

### Don't add obvious noise

```java
// ✗
i = i + 1; // increment i
```

### Don't use closing brace comments

```java
// ✗
public void push(String e) {
    data.add(e);
} // end push

// ✓
public void push(String e) {
    data.add(e);
}
```

### Don't comment out code — just remove it

Git remembers. Commented-out blocks rot and confuse readers.

```java
// ✗
// public void empty() {
//     data.clear();
// }
```

### Use comments as explanation of intent

```java
// Prefer returning Optional so callers must handle an empty queue
public Optional<String> pop() { }
```

### Use comments as clarification of code

```java
// ArrayList.remove(0) is O(n); acceptable while the queue stays small
return data.remove(0);
```

### Use comments as warning of consequences

```java
// Changing this font size also changes the preferred window size in CalcGUI
new CalcGUI(30);
```

---

## Source code structure

### Separate concepts vertically

Leave a blank line between unrelated ideas: fields, constructor, queries, commands.

### Related code should appear vertically dense

Keep `top()` and `pop()` next to each other — they share the empty-queue rule.

### Declare variables close to their usage

```java
// ✗
int i = 5;
Person p1 = new Person("Doug", 31);
System.out.println(p1.getName());
// ... 20 lines ...
changeInt(i);

// ✓
Person firstGuest = new Person("Doug", 31);
System.out.println(firstGuest.getName());

int count = 5;
changeInt(count);
```

### Dependent functions should be close

If `printAll` calls `formatName`, place `formatName` just below `printAll`.

### Similar functions should be close

`changeInt` and `increaseInt` belong together.

### Place functions in the downward direction

A file should read like a newspaper: high-level story first, details below.

```java
public static void main(String[] args) {
    runDemo();
}

private static void runDemo() {
    Person person = new Person("Doug", 31);
    print(person);
}

private static void print(Person person) {
    System.out.println(person.getName());
}
```

### Keep lines short

Break long concatenations or argument lists.

```java
// ✗
System.out.println("p2's name changed: " + p2.getName() + " and age is " + p2.getAge() + " after update");

// ✓
System.out.println("p2's name changed: " + p2.getName());
```

### Don't use horizontal alignment

```java
// ✗
private String name  = "Doug";
private int    age   = 31;

// ✓
private String name = "Doug";
private int age = 31;
```

### Use white space to associate related things

Group the two `Person` creations; separate them from the `int` experiment.

### Don't break indentation

Let the formatter own braces and indent. Never mix tabs and spaces.

---

## Objects and data structures

### Hide internal structure

`Queue` should not expose its `ArrayList`. Callers use `push`, `pop`, `isEmpty`.

```java
// ✗
public ArrayList<String> data;

// ✓
private final ArrayList<String> data;
```

### Prefer data structures

When you only need data, use a record or a simple DTO — not a service object with hidden behaviour.

```java
public record PersonName(String value) { }
```

### Avoid hybrid structures

Do not mix a public data bag with surprising methods that mutate distant state.

```java
// ✗ Half DTO, half service
public class Person {
    public String name;
    public void saveToDatabase() { }
}
```

### Should be small / Do one thing / Few instance variables

A class with twelve fields is usually several classes. `Person` has a name and an age. `Queue` has a list. That is enough.

### Base class should know nothing about their derivatives

```java
// ✗
public class Employee {
    public double pay() {
        if (this instanceof HourlyEmployee hourly) {
            return hourly.monthlyPay();
        }
        return 0;
    }
}

// ✓
public abstract class Employee {
    public abstract double monthlyPay();
}
```

### Many functions rather than a behaviour flag

```java
// ✗
public void apply(Person person, String action) {
    if (action.equals("print")) { }
    if (action.equals("save")) { }
}

// ✓
public void print(Person person) { }
public void save(Person person) { }
```

### Prefer non-static methods to static methods

Statics are hard to replace in tests. Use them for pure utilities (`Math.max`), not for business rules.

```java
// ✗
public static double monthlyPay(Employee employee) { }

// ✓
public double monthlyPay() { }
```

---

## Tests

JUnit example for `Queue`:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueTest {

    @Test
    void popReturnsTheFirstPushedElement() {
        Queue queue = new Queue();
        queue.push("Ada");

        assertEquals("Ada", queue.pop());
    }

    @Test
    void newQueueIsEmpty() {
        Queue queue = new Queue();

        assertTrue(queue.isEmpty());
    }
}
```

### One assert per test

One test, one reason to fail. Split “returns the head” and “leaves the queue empty” if both matter.

### Readable

The test name says what is expected. Arrange–Act–Assert stays obvious.

### Fast

No sleeps, no real network, no real database. A `Queue` test should finish in milliseconds.

### Independent

Tests must not share a mutable `static Queue`. Each test creates its own instance.

### Repeatable

Same result on every machine, every run. Do not depend on wall-clock time or directory listing order unless you control them.

---

## Code smells

### Rigidity

The software is difficult to change. A small change causes a cascade of subsequent changes.

*Example:* renaming `Person` forces edits in GUI, file I/O, reports, and tests because every layer depends on the concrete class instead of a small interface or record.

### Fragility

The software breaks in many places due to a single change.

*Example:* you change `Queue.pop()` to throw on empty instead of returning `null`, and forgotten call sites in the calculator and the people demo all crash.

### Immobility

You cannot reuse parts of the code in other projects because of involved risks and high effort.

*Example:* `Queue` writes to `System.out` and reads `Scanner` internally, so you cannot drop it into a GUI or a test without bringing the console along.

### Needless complexity

An `AbstractFactory` around a list that stores three names.

### Needless repetition

The empty-queue `if` copied in `top()` and `pop()` (and the next method you add).

### Opacity

The code is hard to understand.

*Example:* `d`, `f(boolean b)`, and a 120-line `main` that mixes queue, people, and calculator logic.

---

## Quick checklist

| Area        | Ask yourself                                              |
| ----------- | --------------------------------------------------------- |
| Names       | Can a teammate guess the meaning without opening the body? |
| Functions   | Does this method do one thing, with few arguments?        |
| Comments    | Would a better name remove this comment?                  |
| Structure   | Are related lines close, and concepts separated?          |
| Objects     | Is the internal list/array hidden?                        |
| Tests       | Fast, independent, one reason to fail?                    |
| Smell       | Did a small change ripple or break distant code?          |

Leave the file cleaner than you found it.
