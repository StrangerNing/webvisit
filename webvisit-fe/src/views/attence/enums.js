import { Enum } from '../../utils/Enum'

export default {

  attenceEnum: new Enum().add('Monday', '星期一', 1).add('Tuesday', '星期二', 2).add('Wednesday', '星期三', 3).add('Thursday', '星期四', 4).add('Friday', '星期五', 5).add('Saturday', '星期六', 6).add('Sunday', '星期日', 7),
  accumulateToNextYearEnum: new Enum().add('yes', '是', 1).add('no', '否', 0),
  probationHasEnum: new Enum().add('yes', '是', 1).add('no', '否', 0),
  graduationOneYearHasEnum: new Enum().add('yes', '是', 1).add('no', '否', 0),
  attenceTypeEnum: new Enum().add('normal', '正常', 0).add('annual', '年假', 1).add('leave', '请假', 2),
  punchInStatus: new Enum().add('normal', '正常签到', 0).add('miss', '未签到', 1).add('late', '迟到', 2),
  punchOutStatus: new Enum().add('normal', '正常签退', 0).add('miss', '未签退', 1).add('early', '早退', 2)
}
