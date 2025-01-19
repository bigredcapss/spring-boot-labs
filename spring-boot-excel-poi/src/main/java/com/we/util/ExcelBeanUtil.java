package com.we.util;

import com.we.entity.Merchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author we
 * @date 2021-07-17 11:57
 **/
public class ExcelBeanUtil {
    public static List<Map<Integer, Object>> manageObjectList(final List<Merchant> list){

        List<Map<Integer, Object>> dataList=new ArrayList<>();
        if(list!=null && list.size()>0){
            int length = list.size();
            Map<Integer,Object> dataMap;
            Merchant bean;
            for (int i = 0; i < length; i++) {
                bean = list.get(i);
                dataMap = new HashMap<>();
                dataMap.put(0,bean.getId());
                dataMap.put(1,bean.getAccountName());
                dataMap.put(2,bean.getAddress());
                dataMap.put(3,bean.getAccountNo());
                dataMap.put(4,bean.getAccountName());
                dataMap.put(5,bean.getStateStr());
                dataList.add(dataMap);
            }
        }
        return dataList;
    }
}
