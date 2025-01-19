package com.we.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author we
 * @date 2021-07-16 17:40
 **/
public interface ExcelService {

    public Boolean exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize);

    public Boolean importExcel(String fileName);
}
