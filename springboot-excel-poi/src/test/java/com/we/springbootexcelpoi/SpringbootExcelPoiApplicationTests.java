package com.we.springbootexcelpoi;


import com.we.controller.ExcelController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootExcelPoiApplicationTests {

    @Autowired
    private ExcelController excelController;

    @Test
    public void contextLoads() {
        String file = "f:/test.xlsx";
        String str = excelController.importExcel(file);
        System.out.println(str);
    }

}
