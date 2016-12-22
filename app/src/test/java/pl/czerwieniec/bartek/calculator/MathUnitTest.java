package pl.czerwieniec.bartek.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MathUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(12, 10 + 2);
    }

    @Test
    public void multiplication_isCorrect() throws Exception {
        assertEquals(20, 10 * 2);
    }

    @Test
    public void division_isCorrect() throws Exception {
        assertEquals(5, 10 / 2);
    }
}