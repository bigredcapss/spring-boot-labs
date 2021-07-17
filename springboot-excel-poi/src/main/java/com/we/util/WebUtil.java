package com.we.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author we
 * @date 2021-07-17 12:08
 **/
public class WebUtil {

    public static void downloadExcel(HttpServletResponse response, Workbook wb, String fileName) throws Exception{
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out=response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
