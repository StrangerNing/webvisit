<template>
  <div style="margin-left: 10px;margin-top: 20px;margin-right: 10px;">
    <el-card>
      <h2>考勤设置</h2>
      <el-table :data="regulationList">
        <el-table-column
          type="index"
        />
        <el-table-column
          prop="name"
          label="规则名称"
          width="100px"
        />
        <el-table-column
          prop="punchInStart"
          label="签到开始时间"
          width="150px"
        />
        <el-table-column
          prop="punchInEnd"
          label="签到结束时间"
          width="150px"
        />
        <el-table-column
          prop="allowLate"
          label="允许迟到时间"
          width="120px"
        />
        <el-table-column
          prop="punchOutStart"
          label="签退开始时间"
          width="150px"
        />
        <el-table-column
          prop="punchOutEnd"
          label="签退结束时间"
          width="150px"
        />
        <el-table-column
          prop="allowLeaveEarly"
          label="允许早退时间"
          width="120px"
        />
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { getRegulationList } from '../../api/attence'

export default {
  name: 'Edit',
  data() {
    return {
      regulationList: {}
    }
  },
  created() {
    this.getRegulationList()
  },
  methods: {
    getRegulationList() {
      getRegulationList().then(res => {
        this.regulationList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
