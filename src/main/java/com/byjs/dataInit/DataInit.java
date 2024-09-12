package com.byjs.dataInit;

import com.byjs.model.PreferencesList;
import lombok.Data;

import java.sql.SQLException;
import java.util.List;

/**
 * @author FF
 * @date 2024/8/25
 */
public class DataInit {
    private static ProjectSBSInit projectSBSInit = new ProjectSBSInit();
    private static ProjectTaskInit projectTaskInit = new ProjectTaskInit();
    private static ProjectUserDeviceInit projectUserDeviceInit = new ProjectUserDeviceInit();
    private static PreferencesListInit preferencesListInit = new PreferencesListInit();
    public static List<PreferencesList> init() throws Exception {
        // 初始化SBS
//        projectSBSInit.init();
//        // 初始化任务模型
//        projectTaskInit.init();
//        // 初始化用户设备
//        projectUserDeviceInit.init();
        // 全局BS设备初始化
        List<PreferencesList> preferencesLists = preferencesListInit.init();
        return preferencesLists; // 返回初始化后的偏好列表
    }

}
