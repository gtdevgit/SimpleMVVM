package com.example.simplemvvm.data.sample;

import com.example.simplemvvm.data.model.Contract;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SampleContract {
    private static Contract[] contracts;

    static {
        try {
            contracts = new Contract[] {
                    new Contract(1, 1, 1, new SimpleDateFormat("dd/MM/yyy").parse("09/09/2021")),
                    new Contract(2, 2, 2, new SimpleDateFormat("dd/MM/yyy").parse("10/09/2021")),
                    new Contract(3, 3, 3, new SimpleDateFormat("dd/MM/yyy").parse("10/09/2021")),
                    new Contract(4, 4, 4, new SimpleDateFormat("dd/MM/yyy").parse("11/09/2021")),
                    new Contract(5, 5, 5, new SimpleDateFormat("dd/MM/yyy").parse("11/09/2021")),
                    new Contract(6, 6, 6, new SimpleDateFormat("dd/MM/yyy").parse("11/09/2021")),
                    new Contract(7, 7, 7, new SimpleDateFormat("dd/MM/yyy").parse("14/09/2021")),
                    new Contract(8, 8, 8, new SimpleDateFormat("dd/MM/yyy").parse("15/09/2021")),
                    new Contract(9, 9, 9, new SimpleDateFormat("dd/MM/yyy").parse("16/09/2021")),
                    new Contract(10, 10, 10, new SimpleDateFormat("dd/MM/yyy").parse("16/09/2021")),
                    new Contract(11, 11, 11, new SimpleDateFormat("dd/MM/yyy").parse("17/09/2021"))
            };
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Contract[] getContracts(){
        return contracts;
    }
}
