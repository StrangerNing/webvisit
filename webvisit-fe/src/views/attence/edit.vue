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
            label="工作日设置"
            min-width="60"
          >
            <span slot-scope="scope">
              <el-button
                type="success"
                size="small"
                icon="el-icon-search"
                @click="getWorkdayList(scope.row.id)"
              >查看</el-button>
            </span>
          </el-table-column>
          <el-table-column
            label="操作"
            min-width="110"
          >
            <span slot-scope="scope">
              <el-row>
                <el-col :span="12">
                  <el-button
                    type="primary"
                    size="small"
                    icon="el-icon-edit"
                    @click="editRegulation(scope.$index, scope.row)"
                  >编辑</el-button>
                </el-col>
                <el-col :span="12">
                  <el-button
                    type="danger"
                    size="small"
                    icon="el-icon-delete"
                    @click="deleteRegulation(scope.$index, scope.row)"
                  >删除</el-button>
                </el-col>
              </el-row>
            </span>
          </el-table-column>
        </el-table>
        <el-dialog
          title="工作日设置"
          :visible.sync="workdayListVisible"
        >
          <el-checkbox-group v-model="workdaySet.workdayList" style="text-align: center;margin-top: 80px">
            <el-checkbox v-for="weekDay in weekDays" :key="weekDay" :label="weekDay" style="margin-left: 20px">{{
              getWorkdayLabel(weekDay) }}
            </el-checkbox>
          </el-checkbox-group>
          <el-row style="text-align: center;margin-top: 100px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelSetWorkday()">取消</el-button>
            <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitWorkday()">提交
            </el-button>
          </el-row>
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
                  <el-time-picker
                    v-model="regulation.punchInStart"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="签到开始"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="签到结束时间">
                  <el-time-picker
                    v-model="regulation.punchInEnd"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="签到结束"
                  />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="签退开始时间">
                  <el-time-picker
                    v-model="regulation.punchOutStart"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="签退开始"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="签退结束时间">
                  <el-time-picker
                    v-model="regulation.punchOutEnd"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="签退结束"
                  />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="允许迟到时间">
                  <el-time-picker
                    v-model="regulation.allowLate"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="允许迟到时间"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="允许早退时间">
                  <el-time-picker
                    v-model="regulation.allowLeaveEarly"
                    :picker-options="{
                      selectableRange: '00:00:00 - 23:59:59'
                    }"
                    value-format="HH:mm:ss"
                    placeholder="允许早退时间"
                  />
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
              <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitRegulation()">提交
              </el-button>
            </el-row>
          </el-form>
        </el-dialog>
      </el-card>
    </div>
    <div class="module">
      <el-row :gutter="8">
        <el-col :span="12">
          <el-card>
            <el-row class="row-title">
              <el-col :span="12">
                <h2>节假日设置</h2>
              </el-col>
              <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
                <el-button type="primary" icon="el-icon-plus" @click="addHolidayVisible=true">添加</el-button>
              </el-col>
            </el-row>
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
            <el-dialog
              title="添加节假日"
              :visible.sync="addHolidayVisible"
            >
              <el-row>
                <el-col :span="12">
                  <el-date-picker
                    v-model="addHoliday.date"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  />
                </el-col>
              </el-row>
              <el-row style="text-align: center;margin-top: 100px">
                <el-button icon="el-icon-close" style="margin-right: 10px" @click="addHolidayVisible=false">取消
                </el-button>
                <el-button
                  type="primary"
                  icon="el-icon-upload"
                  style="margin-left: 10px"
                  @click="newHoliday(addHoliday.date)"
                >提交
                </el-button>
              </el-row>
            </el-dialog>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <el-row class="row-title">
              <el-col :span="12">
                <h2>请假类型设置</h2>
              </el-col>
              <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
                <el-button type="primary" icon="el-icon-plus" @click="addSetLeaveType()">添加</el-button>
              </el-col>
            </el-row>
            <el-table
              :data="leaveList"
              style="width: 100%"
              border
              stripe
              fit
              height="450"
            >
              <el-table-column
                type="index"
              />
              <el-table-column
                prop="name"
                label="类型名称"
              />
              <el-table-column
                prop="availableDays"
                label="可请假天数（天）"
              />
              <el-table-column
                prop="salaryPercent"
                label="薪资比例"
              />
              <el-table-column
                label="操作"
                min-width="110"
              >
                <span slot-scope="scope">
                  <el-row>
                    <el-col :span="12">
                      <el-button
                        type="primary"
                        size="small"
                        icon="el-icon-edit"
                        @click="editLeaveType(scope.$index, scope.row)"
                      >编辑</el-button>
                    </el-col>
                    <el-col :span="12">
                      <el-button
                        type="danger"
                        size="small"
                        icon="el-icon-delete"
                        @click="deleteLeaveType(scope.$index, scope.row)"
                      >删除</el-button>
                    </el-col>
                  </el-row>
                </span>
              </el-table-column>
            </el-table>
            <el-dialog
              :title="leaveTypeTile"
              :visible.sync="editLeaveVisible"
            >
              <el-form :model="leaveType" label-width="25%" style="width: 100%;">
                <el-form-item label="类型名称：">
                  <el-input v-model="leaveType.name" style="width: 90%;" />
                </el-form-item>
                <el-form-item label="可请假天数：">
                  <el-input v-model="leaveType.availableDays" type="number" style="width: 90%;" />
                </el-form-item>
                <el-form-item label="薪资比例：">
                  <el-input v-model="leaveType.salaryPercent" type="number" style="width: 90%;" />
                </el-form-item>
              </el-form>
              <el-row style="text-align: center;margin-top: 100px">
                <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelSetLeaveType()">取消</el-button>
                <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitLeaveType()">
                  提交
                </el-button>
              </el-row>
            </el-dialog>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>年假设置</h2>
          </el-col>
          <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
            <el-button type="primary" icon="el-icon-plus" @click="addRegulation()">添加</el-button>
          </el-col>
        </el-row>
        <el-table :data="annualList" border stripe fit>
          <el-table-column
            type="index"
          />
          <el-table-column
            prop="expireDate"
            label="年假到期日"
          />
          <el-table-column
            prop="accumulateToNextYear"
            label="是否可以累积到下一年"
          />
          <el-table-column
            prop="probationHas"
            label="试用期是否享受"
          />
          <el-table-column
            prop="graduationOneYearHas"
            label="毕业未满一年是否享受"
          />
          <el-table-column
            label="年假阶梯设置"
          >
            <span slot-scope="scope">
              <el-button
                type="success"
                icon="el-icon-search"
                size="small"
                @click="getAnnualStepList(scope.$index, scope.row)"
              >查看</el-button>
            </span>
          </el-table-column>
          <el-table-column
            label="操作"
          >
            <span slot-scope="scope">
              <el-row>
                <el-col :span="12">
                  <el-button
                    type="primary"
                    size="small"
                    icon="el-icon-edit"
                    @click="editAnnual(scope.$index, scope.row)"
                  >编辑</el-button>
                </el-col>
                <el-col :span="12">
                  <el-button
                    type="danger"
                    size="small"
                    icon="el-icon-delete"
                    @click="deleteAnnual(scope.$index, scope.row)"
                  >删除</el-button>
                </el-col>
              </el-row>
            </span>
          </el-table-column>
        </el-table>
        <el-dialog
          title="年假阶梯设置"
          :visible.sync="annualStepVisible"
        >
          <div v-if="annualStepEdit">
            <el-button type="primary" size="small" icon="el-icon-plus" @click="addAnnualStep()">添加</el-button>
          </div>
          <div v-else>
            <el-button type="primary" size="small" icon="el-icon-plus" @click="startEditAnnualStep()">编辑</el-button>
          </div>
          <el-table :data="annualStepList" border stripe fit style="margin-top: 20px">
            <el-table-column
              type="index"
            />
            <el-table-column
              prop="moreThan"
              label="年满"
            >
              <span slot-scope="scope">
                <span v-if="annualStepEdit">
                  <el-input v-model="annualStepList[scope.$index].moreThan" type="number" />
                </span>
                <span v-else>
                  {{ annualStepList[scope.$index].moreThan }}
                </span>
              </span>
            </el-table-column>
            <el-table-column
              prop="lessThan"
              label="低于"
            >
              <span slot-scope="scope">
                <span v-if="annualStepEdit">
                  <el-input v-model="annualStepList[scope.$index].lessThan" type="number" />
                </span>
                <span v-else>
                  {{ annualStepList[scope.$index].lessThan }}
                </span>
              </span>
            </el-table-column>
            <el-table-column
              prop="vacationDays"
              label="享有假期（天）"
            >
              <span slot-scope="scope">
                <span v-if="annualStepEdit">
                  <el-input v-model="annualStepList[scope.$index].vacationDays" type="number" />
                </span>
                <span v-else>
                  {{ annualStepList[scope.$index].vacationDays }}
                </span>
              </span>
            </el-table-column>
            <el-table-column
              label="操作"
            >
              <span slot-scope="scope">
                <el-row>
                  <div v-if="annualStepEdit">
                    <el-button
                      type="primary"
                      size="small"
                      icon="el-icon-edit"
                      @click="editAnnual(scope.$index, scope.row)"
                    >提交</el-button>
                  </div>
                  <div v-else>
                    <el-button
                      type="danger"
                      size="small"
                      icon="el-icon-delete"
                      @click="deleteAnnual(scope.$index, scope.row)"
                    >删除</el-button>
                  </div>
                </el-row>
              </span>
            </el-table-column>
          </el-table>
          <el-row style="text-align: center;margin-top: 100px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelEditAnnualStep()">取消</el-button>
            <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitLeaveType()">提交
            </el-button>
          </el-row>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import {
  addLeave,
  addRegulation,
  cancelHoliday, deleteLeave,
  deleteRegulation,
  editRegulation,
  getHolidayList, getLeaveList,
  getRegulationList, getWorkdayList, queryAnnual, queryAnnualStep,
  setHoliday, setWorkday
} from '../../api/attence'
import { Message } from 'element-ui'
import enums from './enums'

export default {
  name: 'Edit',
  data() {
    return {
      searchDate: {
        beginDate: this.getYearFirstDay(new Date(), 0),
        endDate: this.getYearFirstDay(new Date(), 1)
      },
      editTitle: '编辑考勤规则',
      leaveTypeTile: '编辑请假类型',
      annualStepEdit: false,
      deleteConfirmVisible: false,
      editRegulationVisible: false,
      workdayListVisible: false,
      addHolidayVisible: false,
      editLeaveVisible: false,
      annualStepVisible: false,
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
      leaveList: [],
      annualList: [],
      annualStepList: [],
      workdaySet: {
        workdayList: [],
        regulationId: 0
      },
      addHoliday: {
        date: ''
      },
      leaveType: {
        name: '',
        availableDays: 0,
        salaryPercent: 0
      }
    }
  },
  computed: {
    getWorkdayLabel() {
      return function(param) {
        return enums.attenceEnum.getLabelByValue(param)
      }
    }
  },
  created() {
    this.getRegulationList()
    this.getHolidayList()
    this.getLeaveList()
    this.getAnnualList()
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
      this.workdaySet.workdayList = []
      this.workdaySet.regulationId = id
      getWorkdayList({ regulationId: id }).then(res => {
        for (const workday in res.data) {
          this.workdaySet.workdayList.push(res.data[workday].weekDay)
        }
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
    cancelSetWorkday() {
      this.workdayListVisible = false
      this.workdaySet.workdayList = []
      this.workdaySet.regulationId = 0
    },
    commitWorkday() {
      console.log(this.workdaySet)
      setWorkday(this.workdaySet).then(res => {
        this.statusConfirm(res)
        this.workdayListVisible = false
        this.workdaySet.workdayList = []
        this.workdaySet.regulationId = 0
      })
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
          if (this.addHolidayVisible) {
            this.addHolidayVisible = false
            this.addHoliday.date = ''
          }
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
    },
    getLeaveList() {
      getLeaveList().then(res => {
        this.leaveList = res.data
      })
    },
    cancelSetLeaveType() {
      this.editLeaveVisible = false
    },
    commitLeaveType() {
      addLeave(this.leaveType).then(res => {
        this.leaveStatusConfirm(res)
      })
    },
    leaveStatusConfirm(res) {
      if (res.data) {
        Message({
          message: '提交成功',
          type: 'success',
          duration: 10 * 1000
        })
        this.editLeaveVisible = false
        this.getLeaveList()
      } else {
        Message({
          message: '提交失败',
          type: 'error',
          duration: 10 * 1000
        })
      }
    },
    addSetLeaveType() {
      this.leaveTypeTile = '新增请假类型'
      for (const key in this.leaveType) {
        this.leaveType[key] = null
      }
      this.editLeaveVisible = true
    },
    editLeaveType(index, row) {
      this.leaveTypeTile = '编辑请假类型'
      this.leaveType = row
      this.editLeaveVisible = true
    },
    deleteLeaveType(index, row) {
      deleteLeave({ leaveId: row.id }).then(res => {
        this.leaveStatusConfirm(res)
      })
    },
    getAnnualList() {
      queryAnnual().then(res => {
        this.annualList = res.data
      })
    },
    getAnnualStepList(index, row) {
      queryAnnualStep({ annualId: row.id }).then(res => {
        this.annualStepList = res.data
        this.annualStepVisible = true
        this.annualStepEdit = false
      })
    },
    startEditAnnualStep() {
      this.annualStepEdit = true
    },
    cancelEditAnnualStep() {
      this.annualStepVisible = false
      this.annualStepEdit = false
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
