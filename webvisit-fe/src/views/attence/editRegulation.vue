<template>
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
</template>

<script>
import enums from './enums'
import {
  addRegulation,
  deleteRegulation,
  editRegulation,
  getRegulationList,
  getWorkdayList,
  setWorkday
} from '../../api/attence'
import { Message } from 'element-ui'

export default {
  name: 'EditRegulation',
  data() {
    return {
      editTitle: '编辑考勤规则',
      deleteConfirmVisible: false,
      editRegulationVisible: false,
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
      regulationList: [],
      workdayListVisible: false,
      weekDays: [1, 2, 3, 4, 5, 6, 7],
      workdaySet: {
        workdayList: [],
        regulationId: 0
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
  },
  methods: {
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
      for (const key in row) {
        this.regulation[key] = row[key]
      }
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
