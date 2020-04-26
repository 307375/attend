package com.zcf.attend.service.impl;


import com.zcf.attend.dao.AttendRepository;
import com.zcf.attend.entity.Attend;
import com.zcf.attend.service.AttendService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendServiceImpl implements AttendService {

    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String upload(String classes, MultipartFile nameListExcel) {
        String result = "no";
        if (nameListExcel == null){
            return  result;
        }
        //实例化对象列表，用于存储Excel中的数据
        List<Attend> attendList = new ArrayList<Attend>();

        //读取文件对象nameListExcel 中的数据（读取Excel中每一行数据，存到对象，存到对象列表中）
        try {
            //根据路径获取这个操作excel的实例
            HSSFWorkbook wb = new HSSFWorkbook(nameListExcel.getInputStream());

            //根据页面index 获取sheet页
            HSSFSheet sheet = wb.getSheetAt(0);



            HSSFRow row = null;
            //循环sesheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //获取每一行数据
                row = sheet.getRow(i);

                Attend attend = new Attend();
                attend.setId(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
                attend.setName(row.getCell(1).getStringCellValue());
                attend.setSign(Integer.valueOf((int) row.getCell(2).getNumericCellValue()));
                attendList.add(attend);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("解析Excel中的数据："+attendList);

        //如果成功就，写入mongodb中
        attendRepository.saveAll(attendList);
        result = "ok";
        return result;
    }

    @Override
    public String sign(String name) {
        //查询对象
        Query query=Query.query(Criteria.where("name").is(name));

        //局部修改的内容
        Update update=new Update();
        update.set("sign",1);

        mongoTemplate.updateFirst(query,update,"attend");
        return "ok";
    }

    @Override
    public List<Attend> findAllBySign(Integer sign) {
        return attendRepository.findAllBySign(sign);
    }
}
