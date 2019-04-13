<template>
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
</template>

<script>
import { addLeave, deleteLeave, editLeave, getLeaveList } from '../../api/attence'
import { Message } from 'element-ui'

export default {
  name: 'Leave',
  data() {
    return {
      leaveTypeTile: '编辑请假类型',
      editLeaveVisible: false,
      leaveList: [],
      leaveType: {
        id: null,
        companyId: null,
        name: '',
        availableDays: null,
        salaryPercent: null
      }
    }
  },
  created() {
    this.getLeaveList()
  },
  methods: {
    getLeaveList() {
      getLeaveList().then(res => {
        this.leaveList = res.data
      })
    },
    cancelSetLeaveType() {
      this.editLeaveVisible = false
    },
    commitLeaveType() {
      if (this.leaveType.id === null) {
        addLeave(this.leaveType).then(res => {
          this.leaveStatusConfirm(res)
        })
      } else {
        editLeave(this.leaveType).then(res => {
          this.leaveStatusConfirm(res)
        })
      }
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
      for (const key in row) {
        this.leaveType[key] = row[key]
      }
      this.editLeaveVisible = true
    },
    deleteLeaveType(index, row) {
      deleteLeave({ leaveId: row.id }).then(res => {
        this.leaveStatusConfirm(res)
      })
    }
  }
}
</script>

<style scoped>

</style>
