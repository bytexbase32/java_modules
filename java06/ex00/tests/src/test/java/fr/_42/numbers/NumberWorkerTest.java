package fr._42.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberWorkerTest {

    private final NumberWorker nw = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 11, 97})
    void isPrimeForPrimes(int number) {
        assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {22, 90, 202})
    void isPrimeForNotPrimes(int number) {
        assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1})
    void isPrimeForIncorrectNumbers(int number) {
        assertThrows(
                IllegalNumberException.class,
                () -> nw.isPrime(number)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void digitsSumTest(int number, int expected) {
        assertEquals(expected, nw.digitsSum(number));
    }
}
