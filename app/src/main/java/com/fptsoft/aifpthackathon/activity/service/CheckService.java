package com.fptsoft.aifpthackathon.activity.service;

import com.fptsoft.aifpthackathon.activity.model.Result;
import com.fptsoft.aifpthackathon.activity.model.Sick;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckService {
    public static Result getResult(String imageNameWillCheck){

        List<Sick> sicks = new ArrayList<>();
        Sick sick = new Sick("Lang ben", "default1618112130187.png", 0, null,
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.");
        Sick sick2 = new Sick("Ghe", "default1618112130187.png", 0, null,
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.");
        Sick sick3 = new Sick("Mun", "default1618112130187.png", 0, null,
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.");

        sicks.add(sick);
        sicks.add(sick2);
        sicks.add(sick3);

        Result result = new Result(imageNameWillCheck, 80, new Date(), sicks);

        return result;
    }
}
