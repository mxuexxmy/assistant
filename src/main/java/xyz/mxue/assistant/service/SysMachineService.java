package xyz.mxue.assistant.service;

import xyz.mxue.assistant.model.vo.SysMachineVO;

/**
 * @author mxuexxmy
 * @date 4/7/2021$ 12:28 AM$
 */
public interface SysMachineService {

    /**
     * 系统属性监控
     * @return 系统属性结果集
     */
    SysMachineVO query();
}
