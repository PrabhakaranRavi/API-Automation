package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {
    private final SoftAssert softAssert;

    public SoftAssertionUtil() {
        softAssert = new SoftAssert();
    }

    public void assertTrue(boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }

    public void assertEquals(Object actual, Object expected, String message) {
        softAssert.assertEquals(actual, expected, message);
    }

    public void assertNotEquals(Object actual, Object expected, String message) {
        softAssert.assertNotEquals(actual, expected, message);
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
