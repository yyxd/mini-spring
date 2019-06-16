package com.mooc.zbs.controllers;

import com.mooc.zbs.beans.AutoWired;
import com.mooc.zbs.service.SalaryService;
import com.mooc.zbs.web.mvc.Controller;
import com.mooc.zbs.web.mvc.RequestMapping;
import com.mooc.zbs.web.mvc.RequestParam;

/**
 * Created by HinTi on 2019/6/12.
 * Goal: test annotation
 */
@Controller
public class SalaryController {
    @AutoWired
    private SalaryService salaryService;
    @RequestMapping("/get_salary.json")
    public int getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
