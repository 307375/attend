package com.zcf.attend.controller;

import com.zcf.attend.entity.Attend;
import com.zcf.attend.service.AttendService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AttendController {

    @Autowired
    private AttendService attendService;

    @GetMapping("/sign")
    public String sign(String name){
        //将名字传给服务层，mongodb修改登录状态
        attendService.sign(name);
        return "ok";
    }

    @PostMapping("/upload")
    public String upload(String classes, MultipartFile nameListExcel){
        //接收到前台传过来的文件对象，交给service层或者Excel工具类来解析数据
        //System.out.println("接收前台表单提交数据："+classes+nameListExcel);
        String result = attendService.upload(classes,nameListExcel);
        return result;
    }

    @GetMapping("/list")
    public Map list(){
        Map result = new HashMap<String,Object>();
        //已签到
        List<Attend> complete = attendService.findAllBySign(1);
        result.put("complete",complete);
        //未签到
        List<Attend> incomplete = attendService.findAllBySign(0);
        result.put("incomplete",incomplete);
        return result;
    }
}
