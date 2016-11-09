package com.company.codecoveragetester.test;

import com.company.codecoveragetester.DatabaseLessExample;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unittests for the databaseless class
 *
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class DatabaseLessExampleTest {

    @Test
    public void testValidCase() {
        DatabaseLessExample example = new DatabaseLessExample();
        String output = example.conditional(true);
        Assert.assertEquals("is-valid", output);
    }

    @Test
    public void testInvalidCase() {
        DatabaseLessExample example = new DatabaseLessExample();
        String output = example.conditional(false);
        Assert.assertEquals("is-NOT-valid", output);
    }
}
