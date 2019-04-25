package com.webvisit.service;

import com.webvisit.model.vo.LogVO;
import com.webvisit.model.vo.PunchDetailVO;
import com.webvisit.model.vo.UserInfoVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public interface ExportService {

    /**
     * 导出考勤详情报表
     *
     * @param userInfoVO    当前登录用户
     * @param punchDetailVO 查询条件
     * @param uuid          key
     */
    void exportPunchDetail(UserInfoVO userInfoVO, PunchDetailVO punchDetailVO, String uuid);

    /**
     * 导出日志
     * @param userInfoVO 当前登录用户
     * @param logVO 查询条件
     * @param uuid key
     */
    void exportLog(UserInfoVO userInfoVO, LogVO logVO, String uuid);

    /**
     * 获取下载链接
     *
     * @param uuid key
     * @return 下载链接
     */
    String getDownloadUrl(String uuid);
}
