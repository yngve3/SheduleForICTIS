package com.example.sheduleforictis.utils;

import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.DaySchedule;
import com.example.sheduleforictis.models.WeekSchedule;
import com.example.sheduleforictis.network.models.RequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserModels {
    public static WeekSchedule parseFromNetwork(RequestModel requestModel) {
        int numOfWeek = requestModel.getTable().getWeek();
        String group = requestModel.getTable().getGroup();
        List<List<String>> table = requestModel.getTable().getTable();

        List<DaySchedule> daySchedules = new ArrayList<>();

        for (int i = 2; i < table.size(); i++) {
            String dayOfWeek = "";
            int dayOfMonth = 0;
            String month = "";
            List<Couple> couples = new ArrayList<>();

            dayOfMonth = Integer.parseInt(table.get(i).get(0).split(",")[1].split(" ")[0]);
            month = table.get(i).get(0).split(",")[1].split(" ")[2];
            dayOfWeek = table.get(i).get(0).split(",")[0];

            for (int j = 1; j < table.get(i).size(); j++) {
                String timeStart;
                String timeEnd;
                int numOfCouple;
                String nameOfCouple;
                StringBuilder audience = new StringBuilder();
                StringBuilder professor = new StringBuilder();

                String t = table.get(i).get(j);

                Pattern pattern = Pattern.compile("[А-Я][а-я]* [А-Я]. [A-Я].");
                Matcher matcher = pattern.matcher(t);
                while(matcher.find()) {
                    professor.append(matcher.group());
                    t = t.replace(matcher.group(), "");
                }

                Pattern pattern1 = Pattern.compile("LMS(-[0-9]| |$)|[А-Я]-[0-9]{3}");
                matcher = pattern1.matcher(t);
                while (matcher.find()) {
                    if (matcher.group().contains("LMS")) {
                        audience = new StringBuilder("LMS");
                    } else {
                        audience.append(matcher.group());
                    }
                    t = t.replace(matcher.group(), "");
                }

                nameOfCouple = t;
                numOfCouple = j;
                timeStart = table.get(1).get(j).split("-")[0];
                timeEnd = table.get(1).get(j).split("-")[1];

                Couple couple = new Couple(timeStart, timeEnd, numOfCouple, nameOfCouple, audience.toString(), professor.toString());

                couples.add(couple);
            }

            daySchedules.add(new DaySchedule(dayOfWeek, dayOfMonth, month, numOfWeek, couples));
        }

        return new WeekSchedule(numOfWeek, daySchedules, group);
    }

}
