package com.webvisit.service;

import com.webvisit.model.po.AttenceRegulation;
import com.webvisit.model.vo.PunchVO;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/28
 */

public interface PunchService {

    /**
     * 打卡
     * @param punchVO 打卡参数
     */
    void punch(PunchVO punchVO);

    /**
     * 上班打卡
     * @param punchVO 打卡参数
     * @param regulation 考勤规则
     * @param punchInKey redis key
     * @param lateKey redis key
     */
    void punchIn(PunchVO punchVO, AttenceRegulation regulation, String punchInKey, String lateKey);

    /**
     * 下班打卡
     * @param punchVO 打卡参数
     * @param attenceRegulation 考勤规则
     * @param punchOutKey redis key
     * @param lateKey redis key
     */
    void punchOut(PunchVO punchVO, AttenceRegulation attenceRegulation, String punchOutKey, String lateKey);
}
