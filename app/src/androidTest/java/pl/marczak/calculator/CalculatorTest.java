package pl.marczak.calculator;

import android.support.test.filters.FlakyTest;
import android.support.test.runner.AndroidJUnit4;

import com.udojava.evalex.Expression;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 27.12.2016.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorTest {


    @Test
    public void testAddition() {

        Expression expression = new Expression("4*12");

        assertEquals(expression.eval().intValue(), 48);
    }
 @Test
    public void testAdditio2n() {

        Expression expression = new Expression("12/3");

        assertEquals(expression.eval().intValue(), 4);
    }

}
