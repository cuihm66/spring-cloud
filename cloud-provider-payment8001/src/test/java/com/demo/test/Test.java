package com.demo.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    @org.junit.Test
    public void method() {
        String str = null;
        try {
            String[] strs = str.split(",");
        } catch(Exception e) {
            //e.printStackTrace();
            //log.info(e.getMessage());
            log.error("aaa gggg", e);
        }
    }
}
