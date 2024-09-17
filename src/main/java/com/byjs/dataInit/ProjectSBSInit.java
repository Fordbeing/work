package com.byjs.dataInit;

import com.alibaba.fastjson2.JSON;
import com.byjs.model.MBSModel;
import com.byjs.model.SBSModel;
import com.byjs.utils.LocationUtil;
import com.byjs.utils.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author FF
 * @date 2024/7/10
 */
public class ProjectSBSInit {


    // SBS初始化
    public void init() throws SQLException {
        long startTime = System.currentTimeMillis();
        Connection session  = MysqlConnection.CreateConnection();
        System.out.println("初始化项目");
        MBSModel mbsModel = new MBSModel();

        String sql = "insert into sbsmodel(name,type,coverrange,transmitpower,bandwidth,cpuresource,memoryresource,channelnumber,memorysize,locationX,locationY) " + "values('" + mbsModel.getName() + "','" + mbsModel.getType() + "','" + mbsModel.getCoverRange() + "','" + mbsModel.getTransmitPower() + "'," + "'" + mbsModel.getBandwidth() + "','" + mbsModel.getCPUResource() + "','" + mbsModel.getMemoryResource() + "','" + mbsModel.getChannelNumber() + "'," + "'" + mbsModel.getMemorySize() + "','" + mbsModel.getLocationX() + "','" + mbsModel.getLocationY() + "')";
        assert session != null;
        session.createStatement().execute(sql);
        System.out.println("MBS初始化完成");
        System.out.println(JSON.toJSONString(mbsModel));

        // 用于存放SBS信息，存放一个列表数据（ID,坐标x，坐标y）

        for (int i = 1; i <= 10; i++) {
            SBSModel sbsModel = new SBSModel();
            sbsModel.setID(i);
            sbsModel.setName("SBS" + i);
            sbsModel.setType("SBS");
            sbsModel.setCoverRange(70);
            sbsModel.setTransmitPower(23);
            sbsModel.setBandwidth(10);
            sbsModel.setCPUResource(100);
            sbsModel.setMemoryResource(0.5);
            sbsModel.setChannelNumber(5);
            sbsModel.setMemorySize(500);
            sbsModel.setLocationX(LocationUtil.getSBSRandomLocation());
            sbsModel.setLocationY(LocationUtil.getSBSRandomLocation());
            sql = "insert into sbsmodel(name,type,coverrange,transmitpower,bandwidth,cpuresource,memoryresource,channelnumber,memorysize,locationX,locationY) " + "values('" + sbsModel.getName() + "','" + sbsModel.getType() + "','" + sbsModel.getCoverRange() + "','" + sbsModel.getTransmitPower() + "'," + "'" + sbsModel.getBandwidth() + "','" + sbsModel.getCPUResource() + "','" + sbsModel.getMemoryResource() + "','" + sbsModel.getChannelNumber() + "'," + "'" + sbsModel.getMemorySize() + "','" + sbsModel.getLocationX() + "','" + sbsModel.getLocationY() + "')";
            assert session != null;
            session.createStatement().execute(sql);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("SBS初始化完成，耗时：" + (endTime - startTime) + "ms");


    }
}
