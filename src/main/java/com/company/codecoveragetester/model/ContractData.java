package com.company.codecoveragetester.model;

public class ContractData {

    public int contractNr;
    public String customerName;
    public boolean isBlocked;
    public String type;

    @Override
    public String toString() {
        return "ContractData{" + "contractNr=" + contractNr + ", customerName=" + customerName + ", isBlocked=" + isBlocked + ", type=" + type + '}';
    }
    
}
