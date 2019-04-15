<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <h2>操作日志查看</h2>
        </el-row>
        <el-table :data="logList" border stripe fit>
          <el-table-column
            type="index"
          />
          <el-table-column
            prop="username"
            label="操作人"
          />
          <el-table-column
            prop="operation"
            label="操作信息"
          />
          <el-table-column
            prop="method"
            label="调用方法"
          />
          <el-table-column
            prop="params"
            label="入参"
          />
          <el-table-column
            prop="ip"
            label="操作人IP"
          />
          <el-table-column
            prop="createTime"
            label="操作时间"
          />
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
import { getLogList } from '../../api/security'

export default {
  name: 'Log',
  data() {
    return {
      logList: []
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    getLogList() {
      getLogList().then(res => {
        this.logList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
