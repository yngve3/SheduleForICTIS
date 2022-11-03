package com.example.sheduleforictis;

import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.network.models.RequestModel;

import java.util.List;

public class Parser {
    public static Week parse(RequestModel requestModel) {
        int numOfWeek = requestModel.getTable().getWeek();
        List<List<String>> table = requestModel.getTable().getTable();

        for (int i = 2; i < table.size(); i++) {
            String dayOfWeek = "";
            int dayOfMonth = 0;
            String month = "";
            List<Couple> couples;

            dayOfMonth = Integer.parseInt(table.get(i).get(0).split(",")[1].split(" ")[0]);
            month = table.get(i).get(0).split(",")[1].split(" ")[1];
            dayOfWeek = table.get(i).get(0).split(",")[0];

            for (int j = 2; i < table.get(i).size(); j++) {
                String timeStart;
                String timeEnd;
                int numOfCouple;
                String kindOfConducting;
                String nameOfCouple;
                String audience;
                String professor;

                numOfCouple = j;

            }
        }
    }
}
