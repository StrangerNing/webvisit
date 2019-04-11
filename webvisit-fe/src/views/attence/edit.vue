<template>
  <div style="margin-left: 10px;margin-top: 20px;margin-right: 10px;">
    <el-card>
      <h2>考勤设置</h2>
      <el-table :data="regulationList" border stripe fit>
        <el-table-column
          type="index"
        />
        <el-table-column
          prop="name"
          label="规则名称"
        />
        <el-table-column
          prop="punchInStart"
          label="签到开始时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.punchInStart) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="punchInEnd"
          label="签到结束时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.punchInEnd) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="allowLate"
          label="允许迟到时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.allowLate) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="punchOutStart"
          label="签退开始时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.punchOutStart) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="punchOutEnd"
          label="签退结束时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.punchOutEnd) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="allowLeaveEarly"
          label="允许早退时间"
        >
          <span slot-scope="scope">
            {{ getParsedTime(scope.row.allowLeaveEarly) }}
          </span>
        </el-table-column>
        <el-table-column
          label="签到地点"
        />
        <el-table-column
          prop="allowLocationOffset"
          label="允许偏移范围（米）"
        />
        <el-table-column
          label="设置"
        />
      </el-table>
    </el-card>
    <el-card>
      <h2>节假日设置</h2>
      <el-table />
    </el-card>
  </div>
</template>

<script>
import { getHolidayList, getRegulationList } from '../../api/attence'

export default {
  name: 'Edit',
  data() {
    return {
      searchDate: {
        beginDate: this.getYearFirstDay(new Date(), 0),
        endDate: this.getYearFirstDay(new Date(), 1)
      },
      regulationList: {},
      holidayList: {}
    }
  },
  computed: {
    getParsedTime() {
      return function(param) {
        if (param !== null) {
          const date = new Date(param)
          const hours = date.getHours()
          const minutes = date.getMinutes()
          const seconds = date.getSeconds()
          return this.fix(hours, 2) + ':' + this.fix(minutes, 2) + ':' + this.fix(seconds, 2)
        } else {
          return null
        }
      }
    }
  },
  created() {
    this.getRegulationList()
    this.getHolidayList()
  },
  methods: {
    fix(num, length) {
      return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num
    },
    getYearFirstDay(year, manyYear) {
      year = year.setFullYear(year.getFullYear() + manyYear, 1, 1)
      console.log('第一天', year)
      return year
    },
    getRegulationList() {
      getRegulationList().then(res => {
        this.regulationList = res.data
      })
    },
    getHolidayList() {
      getHolidayList(this.searchDate).then(res => {
        this.holidayList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
