package com.we.util;

import com.we.entity.ExcelData;
import com.we.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel导入导出工具类
 * @author we
 * @date 2021-07-16 16:18
 **/
@Slf4j
public class ExcelUtil {

    private static final String dateFormat="yyyy-MM-dd";
    private static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);

    /**
     * 导出Excel
     * @param response
     * @param data
     */
    public static void exportExcel(HttpServletResponse response, ExcelData data){
        log.info("导出解析开始,fileName:{}",data.getFileName());
        try {
            // 实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 实例化Excel表单,参数为sheet名
            HSSFSheet sheet = workbook.createSheet("sheet");
            // 设置表头
            setTitle(workbook,sheet,data.getHead());
            // 设置单元格并赋值
            setData(sheet,data.getData());
            // 设置浏览器下载
            setBrowser(response,workbook,data.getFileName());
            log.info("导出解析成功!");
        }catch (Exception e){
            log.info("导出解析失败!");
            e.printStackTrace();

        }



    }

    /**
     * 设置表头
     * @param workbook excel工作薄
     * @param sheet excel工作表
     * @param str 表头参数
     */
    private static void setTitle(HSSFWorkbook workbook,HSSFSheet sheet,String[] str){
        try {
            HSSFRow row = sheet.createRow(0);
            // 设置列宽,setColumnWidth的第二个参数要乘以256,该参数的单位为1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i,15*256);
            }
            // 设置为居中加粗，格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            // 创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        }catch (Exception e){
            log.info("导出Excel时，设置表头失败");
            e.printStackTrace();
        }
    }

    /**
     * 表格赋值
     * @param sheet
     * @param data 设置到表格的值
     */
    private static void setData(HSSFSheet sheet, List<String[]> data){
        try {
            int rouNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rouNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rouNum++;
            }
            log.info("Excel表格赋值成功!");
        }catch (Exception e){
            log.info("Excel表格赋值成功");
            e.printStackTrace();
        }
    }

    /**
     * 设置浏览器下载
     * @param response
     * @param workbook excel工作薄
     * @param fileName excel工作薄名
     */
    private static void setBrowser(HttpServletResponse response,HSSFWorkbook workbook,String fileName){
        try {
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            // 将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功!");
        }catch (Exception e){
            log.info("设置浏览器下载失败!");
            e.printStackTrace();
        }
    }

    /**
     * Excel导入解析
     * @param fileName
     * @return
     */
    public static List<Object[]> importExcel(String fileName){
        log.info("导入解析开始,fileName:{}",fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet的行数
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                // 过滤表头行
                if (i==0){
                    continue;
                }
                // 获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType()==(Cell.CELL_TYPE_NUMERIC)) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType()==(Cell.CELL_TYPE_STRING)) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType()==(Cell.CELL_TYPE_BOOLEAN)) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType()==(Cell.CELL_TYPE_ERROR)) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功!");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败!");
            e.printStackTrace();
        }
        return null;
    }

    public static void fillExcelSheetData(List<Map<Integer, Object>> dataList,Workbook wb,String[] headers,String sheetName){
        Sheet sheet=wb.createSheet(sheetName);

        // 创建sheet的第一行数据-即excel的头部信息
        Row headerRow=sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // 从第二行开始塞入真正的数据列表
        int rowIndex=1;
        Row row;
        Object obj;
        for(Map<Integer, Object> rowMap:dataList){
            try {
                row=sheet.createRow(rowIndex++);
                for(int i=0;i<headers.length;i++){
                    obj=rowMap.get(i);
                    if (obj==null) {
                        row.createCell(i).setCellValue("");
                    }else if (obj instanceof Date) {
                        String tempDate=simpleDateFormat.format((Date)obj);
                        row.createCell(i).setCellValue((tempDate==null)?"":tempDate);
                    }else {
                        row.createCell(i).setCellValue(String.valueOf(obj));
                    }
                }
            } catch (Exception e) {
                log.debug("excel sheet填充数据 发生异常： ",e.fillInStackTrace());
            }
        }

    }


    //测试导入
    public static void main(String[] args) {
        try {
            String fileName = "f:/test.xlsx";
            List<Object[]> list = importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                User user = new User();
                user.setId((Integer) list.get(i)[0]);
                user.setUsername((String) list.get(i)[1]);
                user.setPassword((String) list.get(i)[2]);
                user.setEnable((Integer) list.get(i)[3]);
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
