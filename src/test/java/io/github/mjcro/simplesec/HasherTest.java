package io.github.mjcro.simplesec;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HasherTest {
    @DataProvider
    public Object[][] equalsDataProvider() {
        return new Object[][]{
                {false, null, null},
                {false, "null", null},
                {false, null, "null"},
                {false, "foo", "bar"},
                {true, "foo", "foo"},
                {true, "FOO", "foo"}
        };
    }

    @Test(dataProvider = "equalsDataProvider")
    public void testHashEquals(boolean expected, String a, String b) {
        Assert.assertEquals(Hasher.hashEquals(a, b), expected);
    }
}