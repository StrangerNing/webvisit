<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>假期明细设置</h2>
          </el-col>
        </el-row>
        <el-table :data="annualReportList" stripe fit border>
          <el-table-column
            prop="username"
            label="用户名"
          />
          <el-table-column
            prop="nickname"
            label="姓名"
          />
          <el-table-column
            prop="deptName"
            label="部门名"
          />
          <el-table-column
            prop="employmentDate"
            label="入职日期"
          />
          <el-table-column
            label="操作"
          >
            <span slot-scope="scope">
              <el-button
                type="success"
                size="small"
                icon="el-icon-search"
                @click="getWorkdayList(scope.$index, scope.row)"
              >查看</el-button>
            </span>
          </el-table-column>
        </el-table>
        <el-dialog
          title="假期明细设置"
          :visible.sync="holidayDetailListVisible"
        >
          <el-table :data="holidayDetailList" stripe fit border>
            <el-table-column
              type="index"
            />
            <el-table-column
              prop="leaveId"
              label="请假类型名"
            />
            <el-table-column
              prop="thisYearLeaveLeft"
              label="今年剩余"
            >
              <span slot-scope="scope">
                <span v-if="editHolidayDetail" />
                {{ scope.row.thisYearLeaveLeft }} 天
              </span>
            </el-table-column>
            <el-table-column
              prop="lastYearLeaveLeft"
              label="去年剩余"
            />
            <el-table-column
              prop="vacationNumber"
              label="已休天数"
            />
            <el-table-column
              label="操作"
            >
              <span slot-scope="scope">
                <el-button
                  type="primary"
                  size="small"
                  icon="el-icon-edit"
                  @click="editLeaveType(scope.$index, scope.row)"
                >编辑</el-button>
              </span>
            </el-table-column>
          </el-table>
          <el-row style="text-align: center;margin-top: 100px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="holidayDetailListVisible=false">取消</el-button>
            <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="holidayDetailListVisible=false">提交
            </el-button>
          </el-row>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import { queryAnnualReport } from '../../api/attence'

export default {
  name: 'AttenceReport',
  data() {
    return {
      annualReportList: [],
      holidayDetailList: [],
      holidayDetailListVisible: false,
      editHolidayDetail: false
    }
  },
  created() {
    this.getAnnualReport()
  },
  methods: {
    getAnnualReport() {
      queryAnnualReport().then(res => {
        this.annualReportList = res.data
      })
    },
    getWorkdayList(index, row) {
      this.holidayDetailList = row.holidayDetailList
      this.holidayDetailListVisible = true
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
