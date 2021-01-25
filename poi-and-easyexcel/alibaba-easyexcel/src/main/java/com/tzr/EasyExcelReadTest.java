package com.tzr;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.tzr.entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: EasyExcelReadTest
 * @USER: 35293
 * @date: 2021年01月25日 23:07
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class EasyExcelReadTest {
    public static void main(String[] args) {

        String path = "E:\\ideaProject\\poi-and-easyexcel\\alibaba-easyexcel\\";
        String fileName = path + "EasyWrite.xlsx";
        EasyExcel.read(fileName, Data.class, new EasyExcelReadListener()).sheet().doRead();
    }
}

//首先设置一个监听器
class EasyExcelReadListener extends AnalysisEventListener<Data> {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(EasyExcelReadListener.class);
    //设置每几次存储数据到数据库，然后清理list，方便内存回收
    private static final int BATCH_COUNT = 5;

    List<Data> list = new ArrayList<>();

    private DemoDAO demoDao;

    //构造函数1
    public EasyExcelReadListener(){
        demoDao = new DemoDAO();
    }

    //构造函数2: 如果使用了Spring的IOC，那么直接使用这个
//    public EasyExcelReadListener(DemoDAO demoDAO){
//        this.demoDao = demoDAO;
//    }

    //每一条数据调用都会解析
    @Override
    public void invoke(Data data, AnalysisContext analysisContext) {
        logger.info("解析到一条数据：{}", JSON.toJSONString(data));
        list.add(data);
        //达到上面指定的BATCH_COUNT后，需要去存储一次数据库，防止几万条数据都在内存中，容易OOM
        if(list.size() >= BATCH_COUNT) {
            saveData();
            //存储完成清理
            list.clear();
            System.out.println("数据被清理");
        }
    }

    //所有的数据解析完成之后都会调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    //存储数据到数据库
    private void saveData(){
        logger.info("{}条数据，开始存储数据库！", list.size());
        demoDao.save(list);
    }
}

/**模拟一个业务逻辑：
 * 这里的saveData是为了给读取前台的表格之后可以执行这个然后通过下面的方法持久化到数据库，而且这里默认是5条持久一次
 */
class DemoDAO{
    public void save(List<Data> list){
        //这里可以写一段保存数据到数据库的操作
        System.out.println("存储完成");
    }
}
