package com.byjs.dataInit;

import com.byjs.model.GlobalBSTable;
import com.byjs.model.PreferencesList;
import com.byjs.utils.MysqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FF
 * @date 2024/8/25
 * 偏好列表初始化
 */
public class PreferencesListInit {
    // 主要做的就是将SBS数据放到GlobalBSTable中，然后返回排序后的偏好列表
    Connection connection = null;
    List<PreferencesList> list = new ArrayList<>();
    GlobalBSTable globalBSTable = new GlobalBSTable();

    public List<PreferencesList> init() {
        connection = MysqlConnection.CreateConnection();
        try {
            String sql = "select * from sbsmodel";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double TransmitPower = resultSet.getDouble("TransmitPower");
                double Bandwidth = resultSet.getDouble("Bandwidth");
                double CPUResource = resultSet.getDouble("CPUResource");
                double MemoryResource = resultSet.getDouble("MemoryResource");
                int ChannelNumber = resultSet.getInt("ChannelNumber");
                double MemorySize = resultSet.getDouble("MemorySize");
                PreferencesList preferencesList = new PreferencesList();
                preferencesList.setSbsID(id);
                preferencesList.setName(name);
                preferencesList.setTransmitPower(TransmitPower);
                preferencesList.setBandwidth(Bandwidth);
                preferencesList.setCPUResource(CPUResource);
                preferencesList.setMemoryResource(MemoryResource);
                preferencesList.setChannelNumber(ChannelNumber);
                preferencesList.setMemorySize(MemorySize);
                list.add(preferencesList);
            }
            globalBSTable.setPreferencesList(list);
            List<PreferencesList> preferencesLists = globalBSTable.sortPreferencesListByWeightedAverage(0.3, 0.3, 0.2, 0.1, 0.1);
            return preferencesLists; // 返回排序后的偏好列表
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
