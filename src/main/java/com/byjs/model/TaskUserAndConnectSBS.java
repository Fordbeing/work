package com.byjs.model;

import lombok.Data;

import java.util.List;

/**
 * @author FF
 * @date 2024/9/9
 */
@Data
public class TaskUserAndConnectSBS {
    private Integer id;
    private String taskId;
    private List<Integer> SBSList;
    private Integer CPUResource;
    private Integer RAMResource;
}
