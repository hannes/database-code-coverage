package com.company.codecoveragetester.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccountData {

    public String accountNr;
    public BigDecimal balance;
    public Date balanceDate;
    public String type;

    @Override
    public String toString() {
        return "AccountData{" + "accountNr=" + accountNr + ", balance=" + balance + ", balanceDate=" + balanceDate + ", type=" + type + '}';
    }
}
