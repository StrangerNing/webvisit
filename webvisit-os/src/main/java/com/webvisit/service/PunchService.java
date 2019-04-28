package com.webvisit.service;

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
     * @return 打卡结果
     */
    Boolean punch(PunchVO punchVO);
}
