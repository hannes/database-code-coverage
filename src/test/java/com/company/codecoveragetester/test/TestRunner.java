package com.company.codecoveragetester.test;

import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import wrappers.ResultWrapper;

public class TestRunner {

  public static void main(String[] args) {

    JUnitCore jUnitCore = new JUnitCore();
    Computer computer = new Computer();

    Result res = jUnitCore.run(computer,
        MultipleTableDatabaseExampleTest.class);
    // failing result set run

    System.err.println("Baseline run, expect no tests failing,  "
        + res.getFailureCount() + " did.");

    ResultWrapper.NEXT_THROW_EXCEPTION = true;
    res = jUnitCore.run(computer, MultipleTableDatabaseExampleTest.class);

    System.err
        .println("Exception on next() run, expect all " + res.getRunCount()
            + " tests failing,  " + res.getFailureCount() + " did.");
    System.err.println(res.getFailureCount());

  }

}
