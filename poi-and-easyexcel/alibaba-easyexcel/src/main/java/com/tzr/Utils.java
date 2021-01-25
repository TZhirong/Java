package com.tzr;

import com.tzr.entity.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: Utils
 * @USER: 35293
 * @date: 2021年01月25日 22:45
 * @PROJECT_NAME: poi-and-easyexcel
 **/
//工具类，用于操作数据
public class Utils {
    public static List<Data> data(){
        List<Data> list = new ArrayList<Data>();
        for (int i = 0; i < 10; i++) {
            Data data = new Data();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
