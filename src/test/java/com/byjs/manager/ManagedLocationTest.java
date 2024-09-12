package com.byjs.manager;

import com.alibaba.fastjson2.JSON;
import com.byjs.model.SBSModel;
import com.byjs.utils.LocationUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author FF
 * @date 2024/8/7
 */
@SpringBootTest
public class ManagedLocationTest {

    @Test
    public void test1() {
//        for (int i = 0; i < 100; i++) {
//            double locationX = LocationUtil.getLocation();
//            double locationY = LocationUtil.getLocation();
//            System.out.println("locationX = " + locationX + " locationY = " + locationY);
//        }
    }

    // JSON数据测试
    @Test
    public void test2() {
//        SBSModel sbsModel = new SBSModel();
//        sbsModel.setName("SBS");
//        sbsModel.setLocationX(LocationUtil.getLocation());
//        sbsModel.setLocationY(LocationUtil.getLocation());
//        String jsonString = JSON.toJSONString(sbsModel);
//        System.out.println("jsonString = " + jsonString);
//        SBSModel sbsModel1 = JSON.parseObject(jsonString, SBSModel.class);
//        System.out.println("sbsModel1 = " + sbsModel1);
    }

    @Test
    public void test3() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        String sbsListString = String.join(",", list.toString()); // 将List转换为CSV格式
        System.out.println("sbsListString = " + sbsListString);
        List<Integer> integers = JSON.parseArray(sbsListString, Integer.class);
        System.out.println("integers = " + integers);
    }
}
