package calc.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Operations {

    private Operations() {
    }

    public static List<Operation> all() {
        return Collections.unmodifiableList(Arrays.asList(
                new Addition(),
                new Subtraction(),
                new Multiplication(),
                new Division(),
                new Power()));
    }
}
