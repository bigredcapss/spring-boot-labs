package com.we.service.impl;

import com.we.entity.ExcelData;
import com.we.entity.User;
import com.we.mapper.UserMapper;
import com.we.service.ExcelService;
import com.we.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author we
 * @date 2021-07-16 17:40
 **/
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
        log.info("开始导出数据...");
        // 查询数据并赋值给ExcelData
        List<User> userList = userMapper.list();
        List<String[]> list = new ArrayList<>();
        for (User user : userList) {
            String[] arrs = new String[userList.size()];
            arrs[0] = String.valueOf(user.getId());
            arrs[1] = String.valueOf(user.getUsername());
            arrs[2] = String.valueOf(user.getPassword());
            arrs[3] = String.valueOf(user.getEnable());
            list.add(arrs);
        }
        // 表头赋值
        String[] head = {"序列","用户名","密码","状态"};
        ExcelData data = new ExcelData();
        data.setHead(head);
        data.setData(list);
        data.setFileName(fileName);
        // 实现导出
        try{
            ExcelUtil.exportExcel(response,data);
            log.info("导出数据结束!");
            return true;
        }catch (Exception e){
            log.info("导出数据失败!");
            return false;
        }
    }

    @Override
    public Boolean importExcel(String fileName) {
        log.info("开始导入数据");
        try {
            List<Object[]> list = ExcelUtil.importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                User user = new User();
                user.setId((Integer) list.get(i)[0]);
                user.setUsername((String) list.get(i)[1]);
                user.setPassword((String) list.get(i)[2]);
                user.setEnable((Integer) list.get(i)[3]);
                userMapper.insert(user);
            }
            log.info("导入数据结束!");
            return true;
        } catch (Exception e) {
            log.info("导入数据失败!");
            e.printStackTrace();
        }
        return false;
    }
}
