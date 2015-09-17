package com.realdolmen.course.persistence;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import java.time.LocalTime;


@Singleton
public class ClockEJB  {
    private int counter = 0;

    //@Schedule(second = "0/10",minute = "*",hour = "*", persistent = false)
    public void printDate(Timer timer){
        System.out.println(LocalTime.now());
        if(counter++ == 10){
            timer.cancel();
        }
    }
}
