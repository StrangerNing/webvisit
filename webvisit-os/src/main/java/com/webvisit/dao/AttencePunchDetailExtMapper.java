package com.webvisit.dao;

import com.webvisit.model.po.AttencePunchDetail;
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

    /**
     * 插入考勤记录并返回主键
     * @param attencePunchDetail 考勤记录
     * @return 影响条数
     */
    int insertPunchDetailSelectiveReturnId(AttencePunchDetail attencePunchDetail);
}
