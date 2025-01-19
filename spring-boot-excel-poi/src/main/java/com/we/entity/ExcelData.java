package com.we.entity;

import lombok.Data;

import java.util.List;

/**
 * 导入导出实体
 * @author we
 * @date 2021-07-16 16:21
 **/
@Data
public class ExcelData {
    // 文件名称
    private String fileName;
    // 表头
    private String[] head;
    // 数据
    private List<String[]> data;
}
