package com.darknights.devigation.roadmap.command.application.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/")
public class RoadmapCategoryController {

    @PostMapping(value = "/roadmap")
    public String createRoadmap(@RequestBody HashMap<String,String> param){
        System.out.println("param = " + param);
        System.out.println(param.get("roadmap").getClass().getName());



        return "all good";
    }

    @GetMapping("/roadmap")
    public String ReadRoadmap(){
//        for (String key : param.keySet()){
//            System.out.println("key = " + key + "value = " + param.get(key));
//        }

        return "all good";
    }

}
