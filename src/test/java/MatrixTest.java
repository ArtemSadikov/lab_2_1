import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//import sun.nio.cs.StandardCharsets;
//import sun.security.util.IOUtils;

import java.io.IOException;
import java.util.stream.Stream;

class MatrixTest {
    private static Matrix m;

    @BeforeEach
    public void init() {
        m = new Matrix();
    }
    @AfterEach
    public void tearDown() {
        m = null;
    }

    @ParameterizedTest
    @MethodSource("parametrizedMatrices")
    public void multiply(double[][] f, double[][] s) {
        try {
            double[][] res = m.multiply(f, s);
            m.print(res);
        } catch (Exception e) {
            System.out.println(e.getClass());
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @ParameterizedTest
    @MethodSource("parametrizedMatrices")
    public void sum(double[][] f, double[][] s) {
        try {
            double[][] res = m.sum(f, s);
            m.print(res);
        } catch (Exception e) {
            System.out.println(e.getClass());
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @ParameterizedTest
    @MethodSource("parametrizedMatrices")
    public void sub(double[][] f, double[][] s) {
        try {
            double[][] res = m.sub(f, s);
            m.print(res);
        } catch (Exception e) {
            System.out.println(e.getClass());
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("FileReadTest")
    public void ioTest() {
        try {
            String nameFile = "matrix.txt";
            double[][] matrix = m.getMatrixFromFile(nameFile);
            m.print(matrix);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    @Test
    @DisplayName("CannotOpenUnExistFile")
    public void ioTestFail() throws IOException {
        double[][] matrix = m.getMatrixFromFile("no_file.txt");
    }

    @Test
    @DisplayName("CanMultiplyFileMatrices")
    public void multIOMatrix() {
        double[][] matrixFirst = m.getMatrixFromFile("matrix.txt");
        double[][] matrixSecond = m.getMatrixFromFile("matrix.txt");

        double[][] expected = {
                {8.0, 8.0},
                {8.0, 8.0}
        };
        double[][] actual = m.multiply(matrixFirst, matrixSecond);

        assertEquals(true, m.checkEqual(expected, actual));
    }

    @Test
    @DisplayName("CanSumFileMatrices")
    public void sumIOMatrix() {
        double[][] matrixFirst = m.getMatrixFromFile("matrix.txt");
        double[][] matrixSecond = m.getMatrixFromFile("matrix.txt");

        double[][] expected = {
                {4.0, 4.0},
                {4.0, 4.0}
        };
        double[][] actual = m.sum(matrixFirst, matrixSecond);

        assertEquals(true, m.checkEqual(expected, actual));
    }

    @Test
    @DisplayName("CanSubFileMatrices")
    public void subIOMatrix() {
        double[][] matrixFirst = m.getMatrixFromFile("matrix.txt");
        double[][] matrixSecond = m.getMatrixFromFile("matrix.txt");

        double[][] expected = {
                {0.0, 0.0},
                {0.0, 0.0}
        };
        double[][] actual = m.sub(matrixFirst, matrixSecond);

        assertEquals(true, m.checkEqual(expected, actual));
    }

    
    static Stream<Arguments> parametrizedMatrices() {
        return Stream.of(
                arguments(m.createRandMatrix(2, 2), m.createRandMatrix(2, 2)),
                arguments(m.createRandMatrix(2, 2), m.createRandMatrix(3, 3)),
                arguments(m.createRandMatrix(10, 10), m.createRandMatrix(10, 10)),
                arguments(m.createRandMatrix(1, 10), m.createRandMatrix(40, 10)),
                arguments(m.createRandMatrix(1, 1), m.createRandMatrix(1, 1)),
                arguments(m.createRandMatrix(5, 4), m.createRandMatrix(4, 5))
        );
    }
}