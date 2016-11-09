package com.company.codecoveragetester;

/**
 * Example class without a database connection
 *
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class DatabaseLessExample {

    public String conditional(final boolean isConditionValid) {
        if (isConditionValid) {
            return "is-valid";
        } else {
            return "is-NOT-valid";
        }
    }
}
