package com.company.codecoveragetester.test;

import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import wrappers.ResultWrapper;

public class TestRunner {

  public static void main(String[] args) {

    JUnitCore jUnitCore = new JUnitCore();
    Computer computer = new Computer();

    Result res = jUnitCore.run(computer,
        MultipleTableDatabaseExampleTest.class);

    System.err.println("Baseline run, expect no tests failing,  "
        + res.getFailureCount() + " did.");

    ResultWrapper.NEXT_THROW_EXCEPTION = true;
    res = jUnitCore.run(computer, MultipleTableDatabaseExampleTest.class);
    System.err
        .println("Exception on next() run, expect 5 tests failing,  " + res.getFailureCount() + " did.");
    ResultWrapper.NEXT_THROW_EXCEPTION = false;
    
//     for (Failure f : res.getFailures()) {
//     f.getException().printStackTrace();
//     }
   //  System.exit(0);

    ResultWrapper.PRETEND_UNKNOWN_FIELD = true;
    res = jUnitCore.run(computer, MultipleTableDatabaseExampleTest.class);
    // for (Failure f : res.getFailures()) {
    // f.getException().printStackTrace();
    // }
    System.err
        .println("Checking column renames fail tests, expect 3 tests failing,  "
            + res.getFailureCount() + " failed.");
    ResultWrapper.PRETEND_UNKNOWN_FIELD = false;

    ResultWrapper.CHECK_ALL_FETCHED = true; 
    res = jUnitCore.run(computer, MultipleTableDatabaseExampleTest.class);
//    for (Failure f : res.getFailures()) {
//      f.getException().printStackTrace();
//    }
    System.err
        .println("Checking fetching all rows, expect no failing,  "
            + res.getFailureCount() + " did.");
    ResultWrapper.CHECK_ALL_FETCHED = true;
//  for (Failure f : res.getFailures()) {
//  f.getException().printStackTrace();
//}
    // TODO: find out which test cases actually call those methods, should be
    // possible by manually iterating through test cases and keep method call counts

  }

}
