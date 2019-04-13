<template>
  <div class="module">
    <el-card>
      <el-row class="row-title">
        <el-col :span="12">
          <h2>年假设置</h2>
        </el-col>
        <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
          <el-button type="primary" icon="el-icon-plus" @click="newAnnual()">添加</el-button>
        </el-col>
      </el-row>
      <el-table :data="annualList" border stripe fit>
        <el-table-column
          type="index"
        />
        <el-table-column
          prop="expireDate"
          label="年假到期日"
        >
          <span slot-scope="scope">
            <span>{{ scope.row.expireDate }}</span>
            <span v-if="scope.row.status === 1">
              <el-tag type="danger">
                已启用
              </el-tag>
            </span>
          </span>
        </el-table-column>
        <el-table-column
          prop="accumulateToNextYear"
          label="是否可以累积到下一年"
        >
          <span slot-scope="scope">
            {{ getAccumulateToNextYear(scope.row.accumulateToNextYear) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="probationHas"
          label="试用期是否享受"
        >
          <span slot-scope="scope">
            {{ getProbationHas(scope.row.probationHas) }}
          </span>
        </el-table-column>
        <el-table-column
          prop="graduationOneYearHas"
          label="毕业未满一年是否享受"
        >
          <span slot-scope="scope">
            {{ getGraduation(scope.row.graduationOneYearHas) }}
          </span>
        </el-table-column>
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
                  @click="delAnnual(scope.$index, scope.row)"
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
          <el-button type="primary" size="small" icon="el-icon-plus" @click="addStep()">添加</el-button>
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
                    @click="editAnnualStep(scope.$index, scope.row)"
                  >提交</el-button>
                </div>
                <div v-else>
                  <el-button
                    type="danger"
                    size="small"
                    icon="el-icon-delete"
                    @click="deleteAnnualStep(scope.$index, scope.row)"
                  >删除</el-button>
                </div>
              </el-row>
            </span>
          </el-table-column>
        </el-table>
        <el-row style="text-align: center;margin-top: 100px">
          <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelEditAnnualStep()">取消</el-button>
          <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="cancelEditAnnualStep()">确定
          </el-button>
        </el-row>
      </el-dialog>
      <el-dialog
        :title="annualTitle"
        :visible.sync="annualVisible"
      >
        <el-form :model="annual" label-width="25%" style="width: 100%;">
          <el-form-item label="年假到期日">
            <el-date-picker
              v-model="annual.expireDate"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            />
          </el-form-item>
          <el-form-item label="是否可以累积到下一年">
            <el-radio v-model="annual.accumulateToNextYear" :label="accumulateToNextYearEnum.yes">是</el-radio>
            <el-radio v-model="annual.accumulateToNextYear" :label="accumulateToNextYearEnum.no">否</el-radio>
          </el-form-item>
          <el-form-item label="试用期是否享受">
            <el-radio v-model="annual.probationHas" :label="probationHasEnum.yes">是</el-radio>
            <el-radio v-model="annual.probationHas" :label="probationHasEnum.no">否</el-radio>
          </el-form-item>
          <el-form-item label="毕业未满一年是否享受">
            <el-radio v-model="annual.graduationOneYearHas" :label="graduationOneYearHasEnum.yes">是</el-radio>
            <el-radio v-model="annual.graduationOneYearHas" :label="graduationOneYearHasEnum.no">否</el-radio>
          </el-form-item>
          <el-form-item label="是否启用">
            <el-switch v-model="status" />
          </el-form-item>
        </el-form>
        <el-row style="text-align: center;margin-top: 100px">
          <el-button icon="el-icon-close" style="margin-right: 10px" @click="cancelSetAnnual()">取消</el-button>
          <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitAnnual()">提交
          </el-button>
        </el-row>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import enums from './enums'
import {
  addAnnual,
  addAnnualStep,
  deleteAnnual, deleteAnnualStep,
  queryAnnual,
  queryAnnualStep,
  updateAnnual,
  updateAnnualStep
} from '../../api/attence'
import { Message } from 'element-ui'

export default {
  name: 'Annual',
  data() {
    return {
      annualTitle: '编辑年假规则',
      annualStepEdit: false,
      annualStepVisible: false,
      annualVisible: false,
      annualList: [],
      annualStepList: [],
      accumulateToNextYearEnum: {
        yes: enums.accumulateToNextYearEnum.yes.value,
        no: enums.accumulateToNextYearEnum.no.value
      },
      probationHasEnum: {
        yes: enums.probationHasEnum.yes.value,
        no: enums.probationHasEnum.no.value
      },
      graduationOneYearHasEnum: {
        yes: enums.graduationOneYearHasEnum.yes.value,
        no: enums.graduationOneYearHasEnum.no.value
      },
      annual: {
        id: null,
        companyId: null,
        expireDate: '',
        accumulateToNextYear: null,
        probationHas: null,
        graduationOneYearHas: null,
        status: 0
      },
      status: false,
      annualIdForStep: 0
    }
  },
  computed: {
    getAccumulateToNextYear() {
      return function(param) {
        return enums.accumulateToNextYearEnum.getLabelByValue(param)
      }
    },
    getProbationHas() {
      return function(param) {
        return enums.probationHasEnum.getLabelByValue(param)
      }
    },
    getGraduation() {
      return function(param) {
        return enums.graduationOneYearHasEnum.getLabelByValue(param)
      }
    }
  },
  watch: {
    'status': {
      handler: function() {
        this.annual.status = this.status ? 1 : 0
        console.log(this.annual.status)
      },
      deep: true
    }
  },
  created() {
    this.getAnnualList()
  },
  methods: {
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
        this.annualIdForStep = row.id
      })
    },
    startEditAnnualStep() {
      this.annualStepEdit = true
    },
    cancelEditAnnualStep() {
      this.annualStepVisible = false
      this.annualStepEdit = false
    },
    addStep() {
      const step = {
        id: null,
        annualId: this.annualIdForStep,
        moreThan: null,
        lessThan: null,
        vacationDays: null
      }
      this.annualStepList.push(step)
    },
    cancelSetAnnual() {
      this.annualVisible = false
    },
    editAnnual(index, row) {
      this.annualTitle = '编辑年假规则'
      for (const key in row) {
        this.annual[key] = row[key]
      }
      this.status = this.annual.status === 1
      this.annualVisible = true
    },
    newAnnual() {
      this.annualTitle = '新增年假规则'
      for (const key in this.annual) {
        this.annual[key] = null
      }
      this.annual.status = 0
      this.status = false
      this.annualVisible = true
    },
    delAnnual(index, row) {
      deleteAnnual({ annualId: row.id }).then(res => {
        this.checkCommitAnnual(res)
      })
    },
    commitAnnual() {
      if (this.annual.id === null) {
        addAnnual(this.annual).then(res => {
          this.checkCommitAnnual(res)
        })
      } else {
        updateAnnual(this.annual).then(res => {
          this.checkCommitAnnual(res)
        })
      }
    },
    checkCommitAnnual(res) {
      if (res.data) {
        Message({
          message: '提交成功',
          type: 'success',
          duration: 10 * 1000
        })
      } else {
        Message({
          message: '提交失败',
          type: 'error',
          duration: 10 * 1000
        })
      }
      this.getAnnualList()
      this.annualVisible = false
    },
    editAnnualStep(index, row) {
      if (row.id === null) {
        addAnnualStep(row).then(res => {
          this.checkAnnualStepCommit(res, index, row)
        })
      } else {
        updateAnnualStep(row).then(res => {
          this.checkAnnualStepCommit(res, index, row)
        })
      }
    },
    checkAnnualStepCommit(res, index, row) {
      if (res.data) {
        Message({
          message: '提交成功',
          type: 'success',
          duration: 10 * 1000
        })
      } else {
        Message({
          message: '提交失败',
          type: 'error',
          duration: 10 * 1000
        })
      }
      queryAnnualStep({ annualId: row.annualId }).then(res => {
        this.annualStepList = res.data
      })
    },
    deleteAnnualStep(index, row) {
      deleteAnnualStep({ annualStepId: row.id }).then(res => {
        this.checkAnnualStepCommit(res, index, row)
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
