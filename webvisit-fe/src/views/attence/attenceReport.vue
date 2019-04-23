<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>考勤报告</h2>
          </el-col>
        </el-row>
        <el-table :data="allPunchReport" stripe fit border>
          <el-table-column
            type="index"
          />
          <el-table-column
            prop="empName"
            label="职员姓名"
          />
          <el-table-column
            prop="deptName"
            label="所属部门"
          />
          <el-table-column
            prop="punchInCount"
            label="签到次数"
          />
          <el-table-column
            prop="punchInLateCount"
            label="迟到次数"
          />
          <el-table-column
            prop="punchInMissCount"
            label="未签到次数"
          />
          <el-table-column
            prop="punchOutCount"
            label="签退次数"
          />
          <el-table-column
            prop="punchOutEarlyCount"
            label="早退次数"
          />
          <el-table-column
            prop="punchOutMissCount"
            label="未签退次数"
          />
          <el-table-column
            prop="askLeaveCount"
            label="请假次数"
          />
          <el-table-column
            prop="workOutsideCount"
            label="外勤次数"
          />
          <el-table-column
            label="操作"
          >
            <span slot-scope="scope">
              <el-button
                type="success"
                size="small"
                icon="el-icon-search"
                @click="getPunchDetail(scope.row.empId)"
              >查看</el-button>
            </span>
          </el-table-column>
        </el-table>
        <el-dialog
          title="考勤详情查看"
          :visible.sync="showPunchDetail"
        >
          <el-table :data="punchDetail" stripe fit border max-height="800">
            <el-table-column
              type="index"
            />
            <el-table-column
              prop="nickname"
              label="职员姓名"
            />
            <el-table-column
              prop="punchTime"
              label="考勤日期"
            />
            <el-table-column
              prop="punchType"
              label="考勤类型"
            >
              <span slot-scope="scope">
                {{ getAttenceType(scope.row.punchType) }}
              </span>
            </el-table-column>
            <el-table-column
              prop="punchInTime"
              label="签到时间"
            />
            <el-table-column
              prop="punchInStatus"
              label="签到状态"
            >
              <span slot-scope="scope">
                {{ getPunchInType(scope.row.punchInStatus) }}
              </span>
            </el-table-column>
            <el-table-column
              prop="punchOutTime"
              label="签退时间"
            />
            <el-table-column
              prop="punchOutStatus"
              label="签退状态"
            >
              <span slot-scope="scope">
                {{ getPunchOutType(scope.row.punchOutStatus) }}
              </span>
            </el-table-column>
            <el-table-column
              label="签到地点"
            />
            <el-table-column
              label="签退地点"
            />
          </el-table>
          <el-row style="text-align: center;margin-top: 50px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="showPunchDetail=false">关闭</el-button>
          </el-row>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import { getAllPunchReport, getPunchDetailReport } from '../../api/attence'
import enums from './enums'

export default {
  name: 'AttenceReport',
  data() {
    return {
      allPunchReport: [],
      punchDetail: [],
      showPunchDetail: false
    }
  },
  computed: {
    getAttenceType() {
      return function(param) {
        return enums.attenceTypeEnum.getLabelByValue(param)
      }
    },
    getPunchInType() {
      return function(param) {
        return enums.punchInStatus.getLabelByValue(param)
      }
    },
    getPunchOutType() {
      return function(param) {
        return enums.punchOutStatus.getLabelByValue(param)
      }
    }
  },
  created() {
    this.getPunchReport()
  },
  methods: {
    getPunchReport() {
      getAllPunchReport().then(res => {
        this.allPunchReport = res.data
      })
    },
    getPunchDetail(empId) {
      getPunchDetailReport({ empId: empId }).then(res => {
        this.punchDetail = res.data
      })
      this.showPunchDetail = true
    }
  }
}
</script>

<style scoped>
  .module {
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 20px;
  }
  .row-title {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    border-bottom: 1px solid #ddd;
    margin-bottom: 10px
  }
</style>
