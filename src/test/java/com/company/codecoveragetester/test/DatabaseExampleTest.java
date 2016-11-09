package com.company.codecoveragetester.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.codecoveragetester.DatabaseExample;

/**
 * Unittests for the class using a database connection
 *
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class DatabaseExampleTest {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseExampleTest.class);

    @Test
    public void emptyTableTest() throws Exception {
        LOG.info("Testing empty table");

        DatabaseExample example = new DatabaseExample();
        example.deleteAllRecords();
        Assert.assertTrue(example.fetchActivatedItemsFromTheDatabase().isEmpty());
    }

    @Test
    public void onlyActivatedItemsTest() throws Exception {
        LOG.info("Testing only activated items");

        DatabaseExample example = new DatabaseExample();
        example.deleteAllRecords();

        // Insert a dummy record
        example.insertItemInTheDatabase(1, "value1", 10);

        // Check the outcome
        List<String> items = example.fetchActivatedItemsFromTheDatabase();
        Assert.assertFalse(items.isEmpty());

        // Check the item
        Assert.assertEquals("value1", items.get(0));
    }

    @Test
    public void bothActivatedAndNotActivatedItemsTest() throws Exception {
        LOG.info("Testing both activated and not activated items");

        DatabaseExample example = new DatabaseExample();
        example.deleteAllRecords();

        // Insert dummy records
        example.insertItemInTheDatabase(2, "value2", 30);
        example.insertItemInTheDatabase(3, "value3", 10);

        // Check the outcome
        List<String> items = example.fetchActivatedItemsFromTheDatabase();
        Assert.assertFalse(items.isEmpty());

        // Check the item
        Assert.assertEquals("value3", items.get(0));
    }

}
