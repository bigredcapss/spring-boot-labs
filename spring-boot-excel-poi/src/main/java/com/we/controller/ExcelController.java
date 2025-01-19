package com.we.controller;

import com.we.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author we
 * @date 2021-07-16 17:52
 **/
@Slf4j
@RestController
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    /**
     * 导出Excel
     * @param response
     * @param fileName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/export")
    public String exportExcel(HttpServletResponse response,String fileName,Integer pageNum,Integer pageSize){
        if(fileName == null || "".equals(fileName)){
            return "文件名不能为空";
        }else {
            if(fileName.endsWith("xls")||fileName.endsWith("xlsx")){
                Boolean isOk = excelService.exportExcel(response, fileName, 1, 10);
                if(isOk){
                    return "导出成功!";
                }else {
                    return "导出失败!";
                }
            }
            return "文件格式有误!";
        }
    }

    @GetMapping("/import")
    public String importExcel(String fileName) {
        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                Boolean isOk = excelService.importExcel(fileName);
                if (isOk) {
                    return "导入成功!";
                } else {
                    return "导入失败!";
                }
            }
            return "文件格式错误!";
        }
    }

}
