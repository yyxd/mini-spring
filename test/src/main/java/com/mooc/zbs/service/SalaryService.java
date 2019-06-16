package com.mooc.zbs.service;

import com.mooc.zbs.beans.Bean;

/**
 * Created by HinTi on 2019/6/13.
 * Goal:
 */
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience*5000;
    }
}
