package com.company.codecoveragetester;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hsqldb.DatabaseURL;
import org.hsqldb.persist.HsqlProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.codecoveragetester.model.AccountData;
import com.company.codecoveragetester.model.AccountTypeData;
import com.company.codecoveragetester.model.ContractData;

import wrappers.ResultModificationConnectionWrapper;

/**
 * Unittests for a class using a database connection, having multiple tables
 *
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class MultipleTableDatabaseExample {

  private static final Logger LOG = LoggerFactory
      .getLogger(MultipleTableDatabaseExample.class);
  private static Connection databaseConnection;

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
      "yyyy-MM-dd");

  private static final String TABLE_CONTRACT = "contract";
  private static final String TABLE_ACCOUNT = "account";
  private static final String TABLE_ACCOUNT_WITHIN_CONTRACT = "account_within_contract";
  private static boolean haveTablesBeenInitiated = false;

  public MultipleTableDatabaseExample() throws Exception {
    
    
    String url = "jdbc:hsqldb:file:target/testdatabasemultiple";
    LOG.info("Connecting to: {}", url);

    Properties p = new Properties();
    p.setProperty("user", "sa");
    p.setProperty("password", "");

    HsqlProperties props = DatabaseURL.parseURL(url, true, false);
    props.addProperties(p);

    databaseConnection = new ResultModificationConnectionWrapper(props);
    
    
  }

  public Connection getConn() {
    return databaseConnection;
  }

  private void initiateDatabaseWhenNecessary() throws Exception {
    LOG.info("Initiating database");
    if (haveTablesBeenInitiated) {
      LOG.info("Table has been initialized");
      return;
    }

    // Check whether a table is present
    final String SQL = "select count(*) from " + TABLE_CONTRACT;

    try {
      try (ResultSet set = databaseConnection.createStatement()
          .executeQuery(SQL)) {
        set.next();
        LOG.info("Number of records present: {}", set.getInt(1));
      }
    } catch (SQLException ex) {
      LOG.debug("Error encountered, creating the table now: {}",
          ex.getLocalizedMessage());
      ex.printStackTrace();
      createTables();
    }
    haveTablesBeenInitiated = true;
  }

  private void createTables() throws Exception {
    LOG.info("Creating table: {}", TABLE_CONTRACT);
    final String SQL_CONTRACT = "" + "create table " + TABLE_CONTRACT + " "
        + "( " + " contract_nr int not null, "
        + " customer_name varchar(100) not null, "
        + " is_blocked boolean not null, " + " type varchar(1) not null, " + " "
        + " primary key (contract_nr) " + ")";

    databaseConnection.createStatement().execute(SQL_CONTRACT);

    LOG.info("Creating table: {}", TABLE_ACCOUNT);
    final String SQL_ACCOUNT = "" + "create table " + TABLE_ACCOUNT + " " + "( "
        + " account_nr varchar(34) not null, "
        + " balance decimal(13,2) not null, " + " balance_date date null, "
        + " type char(1) not null, " + " " + " primary key (account_nr) " + ")";

    databaseConnection.createStatement().execute(SQL_ACCOUNT);

    LOG.info("Creating table: {}", TABLE_ACCOUNT_WITHIN_CONTRACT);
    final String SQL_ACCOUNT_WITHIN_CONTRACT = "" + "create table "
        + TABLE_ACCOUNT_WITHIN_CONTRACT + " " + "( "
        + " account_nr varchar(34) not null, " + " contract_nr int not null, "
        + " permissions varchar(2) not null, " + " "
        + " primary key (account_nr, contract_nr) " + ")";

    databaseConnection.createStatement().execute(SQL_ACCOUNT_WITHIN_CONTRACT);
  }

  public void insertAccountInTheDatabase(final String accountNr,
      final BigDecimal balance, final Date balanceDate, final String type)
      throws Exception {
    LOG.info("Inserting account record in the database: {}, {}, {}, {}",
        accountNr, balance, balanceDate, type);

    initiateDatabaseWhenNecessary();

    String dateString;
    if (null == balanceDate) {
      dateString = "null";
    } else {
      dateString = "DATE '" + DATE_FORMAT.format(balanceDate) + "'";
    }

    // Note: to be protected against SQL injection
    final String SQL = "" + "insert into " + TABLE_ACCOUNT + " "
        + "(account_nr, balance, balance_date, type) " + "values ('" + accountNr
        + "', " + balance.toPlainString() + ", " + dateString + ", '" + type
        + "')";
    LOG.info("SQL: {}", SQL);
    databaseConnection.createStatement().execute(SQL);
  }

  public void insertContractInTheDatabase(final int contractNr,
      final String customerName, final boolean isBlocked, final String type)
      throws Exception {
    LOG.info("Inserting contract record in the database: {}, {}, {}, {}",
        contractNr, customerName, isBlocked, type);

    initiateDatabaseWhenNecessary();

    // Note: to be protected against SQL injection
    // TODO: check boolean type
    final String SQL = "" + "insert into " + TABLE_CONTRACT + " "
        + "(contract_nr, customer_name, is_blocked, type) " + "values ("
        + contractNr + ", '" + customerName + "', " + isBlocked + ", '" + type
        + "')";
    LOG.info("SQL: {}", SQL);
    databaseConnection.createStatement().execute(SQL);
  }

  public void linkAccountToContractInTheDatabase(final int contractNr,
      final String accountNr, final String permissions) throws Exception {
    LOG.info("Linking account to contract in the database: {}, {}, {}",
        contractNr, accountNr, permissions);

    initiateDatabaseWhenNecessary();

    // Note: to be protected against SQL injection
    final String SQL = "" + "insert into " + TABLE_ACCOUNT_WITHIN_CONTRACT + " "
        + "(contract_nr, account_nr, permissions) " + "values (" + contractNr
        + ", '" + accountNr + "', '" + permissions + "')";
    LOG.info("SQL: {}", SQL);
    databaseConnection.createStatement().execute(SQL);
  }

  public void deleteAllRecords() throws Exception {
    LOG.info("Deleting all records");

    initiateDatabaseWhenNecessary();

    final String SQL_ACCOUNT = "delete from " + TABLE_ACCOUNT;
    databaseConnection.createStatement().execute(SQL_ACCOUNT);

    final String SQL_CONTRACT = "delete from " + TABLE_CONTRACT;
    databaseConnection.createStatement().execute(SQL_CONTRACT);

    final String SQL_ACCOUNT_WITHIN_CONTRACT = "delete from "
        + TABLE_ACCOUNT_WITHIN_CONTRACT;
    databaseConnection.createStatement().execute(SQL_ACCOUNT_WITHIN_CONTRACT);
  }

  public ContractData getContractData(final int contractNr) throws Exception {
    LOG.info("Fetching customer data: {}", contractNr);

    final String SQL = "" + "select   * " + "from     " + TABLE_CONTRACT + " "
        + "where    contract_nr = " + contractNr + " and "
        + "         is_blocked = false";
    LOG.info("SQL: {}", SQL);

    try (ResultSet set = databaseConnection.createStatement()
        .executeQuery(SQL)) {
      if (!set.next()) {
        throw new Exception(
            "Unable to find the contract data for contract: " + contractNr);
      }

      ContractData data = new ContractData();
      data.contractNr = set.getInt("contract_nr");
      data.customerName = set.getString("customer_name");
      data.isBlocked = set.getBoolean("is_blocked");
      data.type = set.getString("type");
      return data;
    }
  }

  public List<AccountData> getAccountData(final int contractNr)
      throws Exception {
    LOG.info("Fetching account data: {}", contractNr);

    final String SQL = "" + "select       acc.* " + "from         "
        + TABLE_ACCOUNT + " acc " + "inner join   "
        + TABLE_ACCOUNT_WITHIN_CONTRACT + " awc "
        + "on           acc.account_nr = awc.account_nr " + "inner join   "
        + TABLE_CONTRACT + " con "
        + "on           con.contract_nr = awc.contract_nr "
        + "where        con.contract_nr = ? and "
        + "             con.is_blocked = ?";
    LOG.info("SQL: {}", SQL);

    PreparedStatement ps = databaseConnection.prepareStatement(SQL);
    ps.setInt(1, contractNr);
    ps.setBoolean(2, false);

    List<AccountData> output = new ArrayList<>();

    try (ResultSet set = ps.executeQuery()) {
      while (set.next()) {
        AccountData data = new AccountData();
        data.accountNr = set.getString("account_nr");
        data.balance = set.getBigDecimal("balance");
        data.balanceDate = set.getDate("balance_date");
        data.type = set.getString("type");
        output.add(data);
      }
    }

    return output;
  }

  public List<AccountData> getPaymentAccountData(final int contractNr)
      throws Exception {
    LOG.info("Fetching account data: {}", contractNr);

    final String SQL = "" + "select       acc.* " + "from         "
        + TABLE_ACCOUNT + " acc " + "inner join   "
        + TABLE_ACCOUNT_WITHIN_CONTRACT + " awc "
        + "on           acc.account_nr = awc.account_nr " + "inner join   "
        + TABLE_CONTRACT + " con "
        + "on           con.contract_nr = awc.contract_nr "
        + "where        con.contract_nr = ? and "
        + "             con.is_blocked = ? and "
        + "             substr(awc.permissions, 2, 1) = 'Y' "
        + "order by     acc.account_nr";
    LOG.info("SQL: {}", SQL);

    PreparedStatement ps = databaseConnection.prepareStatement(SQL);
    ps.setInt(1, contractNr);
    ps.setBoolean(2, false);

    List<AccountData> output = new ArrayList<>();

    try (ResultSet set = ps.executeQuery()) {
      while (set.next()) {
        AccountData data = new AccountData();
        data.accountNr = set.getString("account_nr");
        data.balance = set.getBigDecimal("balance");
        data.balanceDate = set.getDate("balance_date");
        data.type = set.getString("type");
        output.add(data);
      }
    }

    return output;
  }

  public List<AccountTypeData> getBalancePerType(final int contractNr)
      throws Exception {
    LOG.info("Fetching balance per type: {}", contractNr);

    final String SQL = "" + "select       acc.type, "
        + "             sum(acc.balance) balanceSum " + "from         "
        + TABLE_ACCOUNT + " acc " + "inner join   "
        + TABLE_ACCOUNT_WITHIN_CONTRACT + " awc "
        + "on           acc.account_nr = awc.account_nr " + "inner join   "
        + TABLE_CONTRACT + " con "
        + "on           con.contract_nr = awc.contract_nr "
        + "where        con.contract_nr = ? and "
        + "             con.is_blocked = ? " + "group by     acc.type "
        + "order by     acc.type";
    LOG.info("SQL: {}", SQL);

    PreparedStatement ps = databaseConnection.prepareStatement(SQL);
    ps.setInt(1, contractNr);
    ps.setBoolean(2, false);

    List<AccountTypeData> output = new ArrayList<>();

    try (ResultSet set = ps.executeQuery()) {
      while (set.next()) {
        AccountTypeData data = new AccountTypeData();
        data.accountType = set.getString("type");
        data.balanceSum = set.getBigDecimal("balanceSum");
        output.add(data);
      }
    }

    return output;
  }
}
