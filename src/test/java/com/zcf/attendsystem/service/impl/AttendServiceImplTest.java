package com.zcf.attendsystem.service.impl;


import com.zcf.attend.service.AttendService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendServiceImplTest {

    @Autowired
    private AttendService attendService;

    @Test
    public void findAllBySign() {
        System.out.println(attendService.findAllBySign(1));
    }
}