package com.webvisit.model.vo;

import com.webvisit.model.po.AttenceHolidayDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/8
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualDetailVO {

    private Long id;

    private String username;

    private String nickname;

    private Long companyName;

    private Long deptName;

    private Date employmentDate;

    private List<AttenceHolidayDetail> holidayDetailList;


}
