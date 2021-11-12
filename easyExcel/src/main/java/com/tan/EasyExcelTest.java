package com.tan;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tan.
 * @create: 2020-10-14 15:54
 **/
public class EasyExcelTest {

    @Test
    public void templateWrite() {


        List<AreaTestVO> areaList = new ArrayList<AreaTestVO>();
        AreaTestVO areaTestVO = new AreaTestVO();
        areaTestVO.setAreaName("广西片区");
        areaTestVO.setNumber(123);
        areaTestVO.setManageName("徐月");
        areaList.add(areaTestVO);
        String path = "F:\\tan\\java\\";
        String fileName = path + "templateWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(areaList);
        System.out.println("执行完成");
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("片区");
        List<String> head1 = new ArrayList<String>();
        head1.add("计数项:备注");
        List<String> head2 = new ArrayList<String>();
        head2.add("省区对接人");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

}
