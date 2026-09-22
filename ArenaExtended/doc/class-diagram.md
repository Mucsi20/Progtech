# Arena class diagram

UML class diagram of the arena sources.

Packages:

- `arena` — `Character`, `MainCharacter`, `Main`
- `arena.orc` — `Orc`, `Fighter`, `Berserker`, `Defender`
- `arena.dragon` — `Dragon`, `RedDragon`, `BlackDragon`
- `test` — JUnit tests for the concrete character types

```mermaid
classDiagram
    direction TB

    class Character {
        <<abstract>>
        -String name
        -int hp
        -int attack
        #Character(String name, int hp, int attack)
        +attack(Character target)
        +receiveDamage(int amount)
        +isAlive() boolean
        +getName() String
        +getHp() int
    }

    class MainCharacter {
        -double defense
        +MainCharacter(String name, int hp, int attack, double defense)
        +receiveDamage(int amount)
    }

    class Orc {
        <<abstract>>
        #Orc(String name, int hp, int attack)
    }

    class Dragon {
        <<abstract>>
        #Dragon(String name, int hp, int attack)
    }

    class Fighter {
        +Fighter(String name, int hp, int attack)
    }

    class Berserker {
        +Berserker(String name, int hp, int attack)
        +receiveDamage(int amount)
    }

    class Defender {
        +Defender(String name, int hp, int attack)
        +receiveDamage(int amount)
    }

    class RedDragon {
        -int MIN_ATTACK_TO_DAMAGE$
        +RedDragon(String name, int hp, int attack)
        +receiveDamage(int amount)
    }

    class BlackDragon {
        -int MIN_ATTACK_TO_DAMAGE$
        +BlackDragon(String name, int hp, int attack)
        +receiveDamage(int amount)
    }

    class Main {
        +main(String[] args)$
        -createFighters() List~Character~$
        -fightUntilOneRemains(List~Character~, Random) Character$
        -livingCharacters(List~Character~) List~Character~$
        -pickDifferent(List~Character~, Character, Random) Character$
    }

    Character <|-- MainCharacter
    Character <|-- Orc
    Character <|-- Dragon
    Orc <|-- Fighter
    Orc <|-- Berserker
    Orc <|-- Defender
    Dragon <|-- RedDragon
    Dragon <|-- BlackDragon
    Main ..> Character : creates and fights
```

## Legend

- Hollow triangle: inheritance (`extends`)
- Dashed arrow: dependency
- `<<abstract>>`: abstract class
- `$`: static member
- `-` private, `#` protected, `+` public
