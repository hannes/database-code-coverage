package com.company.codecoveragetester.model;

import java.math.BigDecimal;

public class AccountTypeData {

    public String accountType;
    public BigDecimal balanceSum;

    @Override
    public String toString() {
        return "AccountTypeData{" + "accountType=" + accountType + ", balanceSum=" + balanceSum + '}';
    }
    
}
