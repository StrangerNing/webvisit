<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>考勤设置</h2>
          </el-col>
          <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
            <el-button type="primary" icon="el-icon-plus" @click="addRegulation()">添加</el-button>
          </el-col>
        </el-row>
        <el-table :data="regulationList" border stripe fit>
          <el-table-column
            type="index"
          />
          <el-table-column
            prop="name"
            label="规则名称"
          >
            <span slot-scope="scope">
              <span v-if="scope.row.type === 0">
                <el-tag type="danger">法定</el-tag>
              </span>
              <span>{{ scope.row.name }}</span>
            </span>
          </el-table-column>
          <el-table-column
            prop="punchInStart"
            label="签到开始时间"
          />
          <el-table-column
            prop="punchInEnd"
            label="签到结束时间"
          />
          <el-table-column
            prop="allowLate"
            label="允许迟到时间"
          />
          <el-table-column
            prop="punchOutStart"
            label="签退开始时间"
          />
          <el-table-column
            prop="punchOutEnd"
            label="签退结束时间"
          />
          <el-table-column
            prop="allowLeaveEarly"
            label="允许早退时间"
          />
          <el-table-column
            label="签到地点"
          />
          <el-table-column
            prop="allowLocationOffset"
            label="允许偏移范围（米）"
          />
          <el-table-column
            label="查看工作日"
            min-width="60"
          >
            <span slot-scope="scope">
              <el-button type="success" size="small" icon="el-icon-search" @click="getWorkdayList(scope.row.id)">查看</el-button>
            </span>
          </el-table-column>
          <el-table-column
            label="操作"
            min-width="110"
          >
            <span slot-scope="scope">
              <el-button type="primary" size="small" icon="el-icon-edit" @click="editRegulation(scope.$index, scope.row)">编辑</el-button>
              <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteRegulation(scope.$index, scope.row)">删除</el-button>
            </span>
          </el-table-column>
        </el-table>
        <el-dialog
          title="工作日设置"
          :visible.sync="workdayListVisible"
        >
          <el-checkbox-group v-model="workdayList">
            <el-checkbox v-for="weekDay in weekDays" :key="weekDay" :label="weekDay">{{ weekDay }}</el-checkbox>
          </el-checkbox-group>
        </el-dialog>
        <el-dialog
          :title="editTitle"
          :visible.sync="editRegulationVisible"
        >
          <el-form :model="regulation" label-width="25%" style="width: 100%;">
            <el-row>
              <el-col :span="12">
                <el-form-item label="规则名称">
                  <el-input v-model="regulation.name" style="width: 90%;" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="签到开始时间">
                  <el-input v-model="regulation.punchInStart" style="width: 90%;" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="签到结束时间">
                  <el-input v-model="regulation.punchInEnd" style="width: 90%;" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="签退开始时间">
                  <el-input v-model="regulation.punchOutStart" style="width: 90%;" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="签退结束时间">
                  <el-input v-model="regulation.punchOutEnd" style="width: 90%;" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="允许迟到时间">
                  <el-input v-model="regulation.allowLate" style="width: 90%;" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="允许早退时间">
                  <el-input v-model="regulation.allowLeaveEarly" style="width: 90%;" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="允许偏差范围">
                  <el-input v-model="regulation.allowLocationOffset" style="width: 90%;" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row style="text-align: center">
              <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelRegulation()">取消</el-button>
              <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitRegulation()">提交</el-button>
            </el-row>
          </el-form>
        </el-dialog>
      </el-card>
    </div>
    <div class="module">
      <el-card>
        <h2>节假日设置</h2>
        <el-table :data="holidayList" border stripe fit height="450">
          <el-table-column
            type="index"
          />
          <el-table-column
            prop="holidayDate"
            label="日期"
          />
          <el-table-column
            label="类型"
          >
            <span slot-scope="scope">
              <span v-if="scope.row.defaultType === 0">
                <span v-if="scope.row.customType === null ">
                  <el-tag>法定节假日</el-tag>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-tag type="danger">公司取消放假</el-tag>
                </span>
              </span>
              <span v-if="scope.row.defaultType === 1">
                <span v-if="scope.row.customType === null ">
                  <el-tag type="warning">法定调班日</el-tag>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-tag type="success">公司取消调班</el-tag>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
              </span>
              <span v-if="scope.row.defaultType === null">
                <span v-if="scope.row.customType === null ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-tag type="success">公司自订放假</el-tag>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-tag type="danger">公司自订调班</el-tag>
                </span>
              </span>
            </span>
          </el-table-column>
          <el-table-column
            label="操作"
          >
            <span slot-scope="scope">
              <span v-if="scope.row.defaultType === 0">
                <span v-if="scope.row.customType === null ">
                  <el-button type="danger" size="small" @click="setHoliday(scope.$index, scope.row)">取消假期</el-button>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-button type="primary" size="small" @click="setHoliday(scope.$index, scope.row)">恢复假期</el-button>
                </span>
              </span>
              <span v-if="scope.row.defaultType === 1">
                <span v-if="scope.row.customType === null ">
                  <el-button type="success" size="small" @click="setHoliday(scope.$index, scope.row)">公司放假</el-button>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-button type="warning" size="small" @click="setHoliday(scope.$index, scope.row)">取消假期</el-button>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
              </span>
              <span v-if="scope.row.defaultType === null">
                <span v-if="scope.row.customType === null ">
                  <el-tag type="info">数据出错</el-tag>
                </span>
                <span v-if="scope.row.customType === 0 ">
                  <el-button type="danger" size="small" @click="setHoliday(scope.$index, scope.row)">取消假期</el-button>
                </span>
                <span v-if="scope.row.customType === 1 ">
                  <el-button type="success" size="small" @click="setHoliday(scope.$index, scope.row)">取消调班</el-button>
                </span>
              </span>
            </span>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
import {
  addRegulation,
  cancelHoliday,
  deleteRegulation,
  editRegulation,
  getHolidayList,
  getRegulationList, getWorkdayList,
  setHoliday
} from '../../api/attence'
import { Message } from 'element-ui'

export default {
  name: 'Edit',
  data() {
    return {
      searchDate: {
        beginDate: this.getYearFirstDay(new Date(), 0),
        endDate: this.getYearFirstDay(new Date(), 1)
      },
      editTitle: '编辑考勤规则',
      deleteConfirmVisible: false,
      editRegulationVisible: false,
      workdayListVisible: false,
      regulation: {
        id: 0,
        name: '',
        companyId: 0,
        punchInStart: '',
        punchInEnd: '',
        allowLate: '',
        punchOutStart: '',
        punchOutEnd: '',
        allowLeaveEarly: '',
        checkLocationLat: 0.00,
        checkLocationLon: 0.00,
        allowLocationOffset: 0.00,
        type: 0
      },
      weekDays: [1, 2, 3, 4, 5, 6, 7],
      regulationList: [],
      holidayList: [],
      workdayList: []
    }
  },
  created() {
    this.getRegulationList()
    this.getHolidayList()
  },
  methods: {
    getYearFirstDay(year, manyYear) {
      year = year.setFullYear(year.getFullYear() + manyYear, 0, 1)
      console.log('第一天', year)
      return year
    },
    getRegulationList() {
      getRegulationList().then(res => {
        this.regulationList = res.data
        console.log(res.data)
      })
    },
    getWorkdayList(id) {
      this.workdayList = []
      getWorkdayList({ regulationId: id }).then(res => {
        for (const workday in res.data) {
          this.workdayList.push(res.data[workday].weekDay)
        }
        console.log(this.workdayList)
      })
      this.workdayListVisible = true
    },
    addRegulation() {
      this.editTitle = '新增考勤规则'
      for (const key in this.regulation) {
        this.regulation[key] = null
      }
      this.editRegulationVisible = true
    },
    editRegulation(index, row) {
      this.editTitle = '编辑考勤规则'
      this.regulation = row
      this.editRegulationVisible = true
    },
    cancelRegulation() {
      this.editRegulationVisible = false
    },
    commitRegulation() {
      if (this.editTitle === '新增考勤规则') {
        addRegulation(this.regulation).then(res => {
          this.statusConfirm(res)
        })
      } else {
        editRegulation(this.regulation).then(res => {
          this.statusConfirm(res)
        })
      }
    },
    statusConfirm(res) {
      if (res.data) {
        Message({
          message: '提交成功',
          type: 'success',
          duration: 10 * 1000
        })
        this.editRegulationVisible = false
        this.getRegulationList()
      } else {
        Message({
          message: '提交失败',
          type: 'error',
          duration: 10 * 1000
        })
      }
    },
    deleteRegulation(index, row) {
      this.$confirm('被删除的考勤规则无法恢复, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteRegulation({ regulationId: row.id }).then(res => {
          if (res.data) {
            Message({
              message: '删除成功',
              type: 'success',
              duration: 10 * 1000
            })
            this.getRegulationList()
          } else {
            Message({
              message: '删除失败',
              type: 'error',
              duration: 10 * 1000
            })
            this.getRegulationList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    getHolidayList() {
      getHolidayList(this.searchDate).then(res => {
        this.holidayList = res.data
      })
    },
    cancelHoliday(date) {
      cancelHoliday({ date: date }).then(res => {
        if (res.data) {
          Message({
            message: '取消成功',
            type: 'success',
            duration: 10 * 1000
          })
          this.getHolidayList()
        } else {
          Message({
            message: '取消失败',
            type: 'error',
            duration: 10 * 1000
          })
        }
      })
    },
    newHoliday(date) {
      setHoliday({ date: date }).then(res => {
        if (res.data) {
          Message({
            message: '设置成功',
            type: 'success',
            duration: 10 * 1000
          })
          this.getHolidayList()
        } else {
          Message({
            message: '设置失败',
            type: 'error',
            duration: 10 * 1000
          })
        }
      })
    },
    setHoliday(index, row) {
      if (row.defaultType === 0) {
        if (row.customType === null) {
          this.cancelHoliday(row.holidayDate)
        } else if (row.customType === 1) {
          this.newHoliday(row.holidayDate)
        } else {
          Message({
            message: '数据出错',
            type: 'error'
          })
        }
      } else if (row.defaultType === 1) {
        if (row.customType === null) {
          this.newHoliday(row.holidayDate)
        } else if (row.customType === 0) {
          this.cancelHoliday(row.holidayDate)
        } else {
          Message({
            message: '数据出错',
            type: 'error'
          })
        }
      } else if (row.defaultType === null) {
        if (row.customType === 0) {
          this.cancelHoliday(row.holidayDate)
        } else if (row.customType === 1) {
          this.setHoliday(row.holidayDate)
        } else {
          Message({
            message: '数据出错',
            type: 'error'
          })
        }
      } else {
        Message({
          message: '数据出错',
          type: 'error'
        })
      }
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
