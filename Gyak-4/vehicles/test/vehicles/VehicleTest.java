package vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleTest {

    @Test
    void newVehicleHasNoRefuels() {
        Car car = new Car("AAA-111");

        assertEquals(0, car.numRefuels().intValue());
        assertEquals(0.0, car.sumRefuels(), 0.0);
    }

    @Test
    void meanOfNoRefuelsIsNotANumber() {
        Car car = new Car("AAA-111");

        assertTrue(Double.isNaN(car.meanRefuels()));
    }

    @Test
    void oneRefuelIsItsOwnSumAndMean() {
        Car car = new Car("AAA-111");
        car.addRefuel(30);

        assertEquals(1, car.numRefuels().intValue());
        assertEquals(30.0, car.sumRefuels(), 0.0);
        assertEquals(30.0, car.meanRefuels(), 0.0);
    }

    @Test
    void severalRefuelsAddUp() {
        Car car = new Car("AAA-111");
        car.addRefuel(6);
        car.addRefuel(7);
        car.addRefuel(8);
        car.addRefuel(9);

        assertEquals(4, car.numRefuels().intValue());
        assertEquals(30.0, car.sumRefuels(), 0.0);
        assertEquals(7.5, car.meanRefuels(), 0.0);
    }

    @Test
    void toStringListsPlateCategoryAndRefuels() {
        Car car = new Car("AAA-111");
        car.addRefuel(6);
        car.addRefuel(7);

        assertEquals(
                "Vehicle{plate=AAA-111, category=C, refuels=[6, 7]}",
                car.toString());
    }
}
