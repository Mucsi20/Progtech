package calc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsTest {

    @Test
    void allContainsFiveOperations() {
        List<Operation> operations = Operations.all();

        assertEquals(5, operations.size());
    }

    @Test
    void allListsOperationsInButtonOrder() {
        List<String> symbols = new ArrayList<String>();
        for (Operation operation : Operations.all()) {
            symbols.add(operation.getSymbol());
        }

        assertEquals(Arrays.asList("+", "-", "*", "/", "^"), symbols);
    }

    @Test
    void allReturnsAnUnmodifiableList() {
        final List<Operation> operations = Operations.all();

        assertThrows(UnsupportedOperationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                operations.clear();
            }
        });
    }

    @Test
    void allContainsOnlyKnownOperationTypes() {
        for (Operation operation : Operations.all()) {
            assertTrue(isKnownOperation(operation));
        }
    }

    private boolean isKnownOperation(Operation operation) {
        return operation instanceof Addition
                || operation instanceof Subtraction
                || operation instanceof Multiplication
                || operation instanceof Division
                || operation instanceof Power;
    }
}
