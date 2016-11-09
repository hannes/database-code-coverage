package com.company.codecoveragetester.test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.codecoveragetester.MultipleTableDatabaseExample;
import com.company.codecoveragetester.model.AccountData;
import com.company.codecoveragetester.model.AccountTypeData;
import com.company.codecoveragetester.model.ContractData;

/**
 * Unittests for the class using a database connection having multiple tables
 *
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class MultipleTableDatabaseExampleTest {

    private static final Logger LOG = LoggerFactory.getLogger(MultipleTableDatabaseExampleTest.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final String ACCOUNT_ONE = "NL31AB3";
    private static final int CONTRACT_1 = 333;
    private static final int CONTRACT_2 = 350;
    private static final int CONTRACT_3 = 123;
    
    @Before
    public void initiateTestDataset() throws Exception {
        LOG.info("Initiating dataset");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        
        example.deleteAllRecords();
        
        example.insertAccountInTheDatabase(ACCOUNT_ONE, new BigDecimal(100), DATE_FORMAT.parse("2016-11-08"), "V");
        example.insertAccountInTheDatabase("NL32AX6", new BigDecimal(200), DATE_FORMAT.parse("2016-11-07"), "W");
        example.insertAccountInTheDatabase("DE12ZZ4", new BigDecimal(300), DATE_FORMAT.parse("2016-11-06"), "X");
        example.insertAccountInTheDatabase("PL00AB8", new BigDecimal(400), DATE_FORMAT.parse("2016-11-05"), "Y");
        example.insertAccountInTheDatabase("FR12DE9", new BigDecimal(400), DATE_FORMAT.parse("2016-11-04"), "Z");
        example.insertAccountInTheDatabase("BE34FG7", new BigDecimal(300), DATE_FORMAT.parse("2016-11-03"), "Z");
        example.insertAccountInTheDatabase("NL56HX6", new BigDecimal(500), DATE_FORMAT.parse("2016-11-02"), "V");
        example.insertAccountInTheDatabase("NL78JK5", new BigDecimal(0), null, "X");

        example.insertContractInTheDatabase(CONTRACT_1, "W. Poncin", false, "A");
        example.insertContractInTheDatabase(CONTRACT_2, "E. Bruurs", true, "B");
        example.insertContractInTheDatabase(CONTRACT_3, "H. Muhleisen", false, "C");

        example.linkAccountToContractInTheDatabase(CONTRACT_1, ACCOUNT_ONE, "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_1, "NL32AX6", "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_1, "NL56HX6", "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_2, "NL32AX6", "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_3, "DE12ZZ4", "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_3, "PL00AB8", "YN");
        example.linkAccountToContractInTheDatabase(CONTRACT_3, "FR12DE9", "YY");
        example.linkAccountToContractInTheDatabase(CONTRACT_2, "BE34FG7", "YY");
    }
    
    
    @Test
    public void deleteTest() throws Exception {
        LOG.info("Delete test");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        example.deleteAllRecords();
        Assert.assertTrue(example.getAccountData(CONTRACT_1).isEmpty());
        Assert.assertTrue(example.getPaymentAccountData(CONTRACT_1).isEmpty());
    }
    
    @Test
    public void getNonBlockedContractTest() throws Exception {
        LOG.info("Non blocked contract test");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        ContractData data = example.getContractData(CONTRACT_1);
        Assert.assertEquals(CONTRACT_1, data.contractNr);
        Assert.assertEquals("W. Poncin", data.customerName);
        Assert.assertFalse(data.isBlocked);
        Assert.assertEquals("A", data.type);
    }
    
    /**
     * This testcase is added for 'extra' coverage, but is not required for the 'old' code coverage measurement
     */
    @Test(expected = Exception.class)
    public void getBlockedContractTest() throws Exception {
        LOG.info("Non blocked contract test");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        ContractData data = example.getContractData(CONTRACT_2);
        LOG.info("Data = {}", data);
    }
    
    @Test
    public void getPaymentAccountsForAccountHavingOnlyPaymentAccountsTest() throws Exception {
        LOG.info("Testing get payment accounts for account having only payment accounts");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        List<AccountData> output = example.getPaymentAccountData(CONTRACT_1);
        Assert.assertEquals(3, output.size());
        Assert.assertEquals("NL31AB3", output.get(0).accountNr);
        Assert.assertEquals("NL32AX6", output.get(1).accountNr);
        Assert.assertEquals("NL56HX6", output.get(2).accountNr);
    }
    
    // Note: this testcase should increase the code coverage as it also checks the substring() part of the query
    @Ignore
    @Test
    public void getPaymentAccountsForAccountHavingAlsoPaymentAccountsTest() throws Exception {
        LOG.info("Testing get payment accounts for account having also payment accounts");
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        List<AccountData> output = example.getPaymentAccountData(CONTRACT_3);
        Assert.assertEquals(2, output.size());
        Assert.assertEquals("DE12ZZ4", output.get(0).accountNr);
        Assert.assertEquals("FR12DE9", output.get(1).accountNr);
        // Note: the PL00AB8 account is NOT returned
    }
    
    @Test
    public void getBalancePerTypeTest() throws Exception {
        LOG.info("Testing get balance per type test");
        
        MultipleTableDatabaseExample example = new MultipleTableDatabaseExample();
        List<AccountTypeData> data = example.getBalancePerType(CONTRACT_1);
        Assert.assertEquals(2, data.size());
        // TODO: having these asserts the order by of the query is also checked
//        Assert.assertEquals("V", data.get(0).accountType);
//        Assert.assertEquals(100 + 500, data.get(0).balanceSum.intValue());
//        Assert.assertEquals("W", data.get(1).accountType);
//        Assert.assertEquals(200, data.get(1).balanceSum.intValue());
    }
}
