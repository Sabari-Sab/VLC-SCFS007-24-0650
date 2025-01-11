package com.ntuc.bankbackend.utility;

import java.sql.Date;

public class AccountNumberGenerator {

    static int counter = 0;

    public Long GenerateAccountNumber() {

        // Create date object
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);
        String stringCurrentDate = currentdate.toString().replace("-", "");

        counter++;
        
        int counterString = counter;
        String counterinString = Integer.toString(counterString);

        String generatedAccountNumber = stringCurrentDate + counterinString;
        Long generatedAccountNumberlong = Long.parseLong(generatedAccountNumber);
        return generatedAccountNumberlong;
    }
}
