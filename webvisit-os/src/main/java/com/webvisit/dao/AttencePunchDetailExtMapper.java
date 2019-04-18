package com.webvisit.dao;

import com.webvisit.model.vo.PunchDetailVO;

import java.util.List;

/**
 * @author Tsening Chu
 */
public interface AttencePunchDetailExtMapper {

    /**
     * 根据条件查询考勤详情
     *
     * @param punchDetailVO 查询条件
     * @return 考勤详情
     */
    List<PunchDetailVO> selectByCondition(PunchDetailVO punchDetailVO);
}
