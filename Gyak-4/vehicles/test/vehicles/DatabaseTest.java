package vehicles;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {

    private static final String SAMPLE_REPORT =
            "Vehicles in the database:\n"
                    + "Vehicle{plate=AAA-111, category=C, refuels=[6, 7, 8, 9]}\n"
                    + "Vehicle{plate=AAA-112, category=C, refuels=[6, 7, 8, 9, 10, 11, 12]}\n"
                    + "Vehicle{plate=AAA-113, category=C, refuels=[6, 7, 8, 9, 1000]}\n"
                    + "Vehicle{plate=ABC-123, category=B, refuels=[3, 13]}\n"
                    + "Vehicle{plate=ABC-124, category=B, refuels=[3, 17]}\n"
                    + "Vehicle{plate=ABC-125, category=B, refuels=[30]}\n"
                    + "Vehicle{plate=FFF-888, category=T, refuels=[1, 2, 3, 4, 5, 6, 7]}\n"
                    + "Mean refuels: \n"
                    + "AAA-111: 7.5\n"
                    + "AAA-112: 9.0\n"
                    + "AAA-113: 206.0\n"
                    + "ABC-123: 8.0\n"
                    + "ABC-124: 10.0\n"
                    + "ABC-125: 30.0\n"
                    + "FFF-888: 4.0\n"
                    + "Refuels in category C:\n"
                    + "Most fuel refueled: Vehicle{plate=AAA-113, category=C, refuels=[6, 7, 8, 9, 1000]}\n"
                    + "Least fuel refueled: Vehicle{plate=AAA-111, category=C, refuels=[6, 7, 8, 9]}\n"
                    + "Most times refueled: Vehicle{plate=AAA-112, category=C, refuels=[6, 7, 8, 9, 10, 11, 12]}\n"
                    + "Least times refueled: Vehicle{plate=AAA-111, category=C, refuels=[6, 7, 8, 9]}\n"
                    + "Refuels in category B:\n"
                    + "Most fuel refueled: Vehicle{plate=ABC-125, category=B, refuels=[30]}\n"
                    + "Least fuel refueled: Vehicle{plate=ABC-123, category=B, refuels=[3, 13]}\n"
                    + "Most times refueled: Vehicle{plate=ABC-123, category=B, refuels=[3, 13]}\n"
                    + "Least times refueled: Vehicle{plate=ABC-125, category=B, refuels=[30]}\n"
                    + "Refuels in category T:\n"
                    + "Most fuel refueled: Vehicle{plate=FFF-888, category=T, refuels=[1, 2, 3, 4, 5, 6, 7]}\n"
                    + "Least fuel refueled: Vehicle{plate=FFF-888, category=T, refuels=[1, 2, 3, 4, 5, 6, 7]}\n"
                    + "Most times refueled: Vehicle{plate=FFF-888, category=T, refuels=[1, 2, 3, 4, 5, 6, 7]}\n"
                    + "Least times refueled: Vehicle{plate=FFF-888, category=T, refuels=[1, 2, 3, 4, 5, 6, 7]}\n";

    @Test
    void readLoadsVehiclesInFileOrder() throws Exception {
        Database database = readSample();

        assertEquals(
                Arrays.asList("AAA-111", "AAA-112", "AAA-113"),
                plates(database.collectCategory("C")));
        assertEquals(
                Arrays.asList("ABC-123", "ABC-124", "ABC-125"),
                plates(database.collectCategory("B")));
        assertEquals(
                Arrays.asList("FFF-888"),
                plates(database.collectCategory("T")));
    }

    @Test
    void readStoresRefuelTotalsFromTheSample() throws Exception {
        Database database = readSample();
        List<Vehicle> cars = database.collectCategory("C");
        List<Vehicle> buses = database.collectCategory("B");
        Vehicle truck = database.collectCategory("T").get(0);

        assertRefuels(cars.get(0), 30.0, 4, 7.5);
        assertRefuels(cars.get(1), 63.0, 7, 9.0);
        assertRefuels(cars.get(2), 1030.0, 5, 206.0);
        assertRefuels(buses.get(0), 16.0, 2, 8.0);
        assertRefuels(buses.get(1), 20.0, 2, 10.0);
        assertRefuels(buses.get(2), 30.0, 1, 30.0);
        assertRefuels(truck, 28.0, 7, 4.0);
    }

    @Test
    void collectCategoryIgnoresUnknownCodes() throws Exception {
        Database database = readSample();

        assertTrue(database.collectCategory("X").isEmpty());
    }

    @Test
    void clearDropsEveryVehicle() throws Exception {
        Database database = readSample();

        database.clear();

        assertTrue(database.collectCategory("C").isEmpty());
        assertTrue(database.collectCategory("B").isEmpty());
        assertTrue(database.collectCategory("T").isEmpty());
    }

    @Test
    void readAppendsToVehiclesAlreadyStored() throws Exception {
        Database database = readSample();

        database.read(sampleFile());

        assertEquals(6, database.collectCategory("C").size());
        assertEquals(6, database.collectCategory("B").size());
        assertEquals(2, database.collectCategory("T").size());
    }

    @Test
    void readAllowsAVehicleWithNoRefuels() throws Exception {
        Database database = new Database();
        database.read(fixture("no-refuels.txt", "1\nC AAA-000 0\n"));
        Vehicle car = database.collectCategory("C").get(0);

        assertEquals("AAA-000", car.plate);
        assertEquals(0, car.numRefuels().intValue());
        assertEquals(0.0, car.sumRefuels(), 0.0);
        assertTrue(Double.isNaN(car.meanRefuels()));
    }

    @Test
    void unknownCategoryIsRejected() throws Exception {
        Database database = new Database();
        String path = fixture("unknown-category.txt", "1\nX BAD-999 1 4\n");

        assertThrows(InvalidInputException.class, () -> database.read(path));
        assertTrue(database.collectCategory("C").isEmpty());
    }

    @Test
    void unknownCategoryKeepsVehiclesReadBeforeIt() throws Exception {
        Database database = new Database();
        String path = fixture(
                "unknown-after-car.txt",
                "2\nC AAA-111 1 5\nQ BAD-1 1 9\n");

        assertThrows(InvalidInputException.class, () -> database.read(path));
        ArrayList<Vehicle> cars = database.collectCategory("C");
        assertEquals(1, cars.size());
        assertEquals("AAA-111", cars.get(0).plate);
        assertEquals(5.0, cars.get(0).sumRefuels(), 0.0);
    }

    @Test
    void missingFileIsRejected() {
        Database database = new Database();

        assertThrows(
                FileNotFoundException.class,
                () -> database.read("build/test-fixtures/no-such-file.txt"));
    }

    @Test
    void reportPrintsMeansAndCategoryExtremes() throws Exception {
        Database database = readSample();

        assertEquals(SAMPLE_REPORT, captureReport(database));
    }

    @Test
    void reportFailsWhenACategoryHasNoVehicles() throws Exception {
        Database database = new Database();
        database.read(fixture("car-only.txt", "1\nC AAA-111 2 4 6\n"));
        PrintStream original = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream(), true, "UTF-8"));
        try {
            assertThrows(NoSuchElementException.class, database::report);
        } finally {
            System.setOut(original);
        }
    }

    private static void assertRefuels(Vehicle vehicle, double sum, int count, double mean) {
        assertEquals(sum, vehicle.sumRefuels(), 0.0);
        assertEquals(count, vehicle.numRefuels().intValue());
        assertEquals(mean, vehicle.meanRefuels(), 0.0);
    }

    private static List<String> plates(List<Vehicle> vehicles) {
        List<String> plates = new ArrayList<String>();
        for (Vehicle vehicle : vehicles) {
            plates.add(vehicle.plate);
        }
        return plates;
    }

    private static Database readSample() throws Exception {
        Database database = new Database();
        database.read(sampleFile());
        return database;
    }

    private static String sampleFile() {
        Path direct = Paths.get("data.txt");
        if (Files.isRegularFile(direct)) {
            return direct.toString();
        }
        Path fromRepo = Paths.get("ENG", "04", "vehicles", "data.txt");
        if (Files.isRegularFile(fromRepo)) {
            return fromRepo.toString();
        }
        throw new IllegalStateException("Could not find data.txt");
    }

    private static String fixture(String name, String contents) throws IOException {
        Path dir = Paths.get("build", "test-fixtures");
        Files.createDirectories(dir);
        Path file = dir.resolve(name);
        Files.write(file, contents.getBytes(StandardCharsets.UTF_8));
        return file.toString();
    }

    private static String captureReport(Database database) throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, "UTF-8"));
        try {
            database.report();
        } finally {
            System.setOut(original);
        }
        return buffer.toString("UTF-8").replace("\r\n", "\n");
    }
}
