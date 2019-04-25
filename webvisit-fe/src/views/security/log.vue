<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>操作日志查看</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-form>
            <el-col :span="8">
              <el-form-item
                label="用户名：">
                <el-input v-model="queryParam.username" style="max-width: 60%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="操作时间">
                <el-date-picker style="width: 80%"
                                v-model.trim="operationTime"
                                value-format="yyyy-MM-dd"
                                format="yyyy-MM-dd"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                @change="setQueryOperationTime" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="search">搜索</el-button>
              <el-button type="success">导出</el-button>
            </el-col>
          </el-form>
        </el-row>
        <el-table :data="logList" border stripe fit>
          <el-table-column
            type="index"
            :index="handelIndex"
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
        <el-pagination
          style="margin-top: 20px;text-align: center"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryParam.pageNum"
          :page-sizes="[10,20,50,100]"
          :page-size="queryParam.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
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
      logList: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      operationTime: null,
      queryParam: {
        username: null,
        pageNum: 1,
        pageSize: 10,
        beginTime: null,
        endTime: null
      }
    }
  },
  computed: {
    getUsername() {
      return this.queryParam.username
    }
  },
  watch: {
    getUsername(val) {
      if (val === '') {
        this.queryParam.username = null
      }
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    getLogList() {
      getLogList(this.queryParam).then(res => {
        this.logList = res.data.list
        this.queryParam.pageSize = res.data.pageSize
        this.total = res.data.total
      })
    },
    handleSizeChange(val) {
      this.queryParam.pageSize = val
      this.queryParam.pageNum = 1
      this.getLogList()
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      this.queryParam.pageNum = val
      this.getLogList()
      console.log(`当前页: ${val}`)
    },
    handelIndex(index) {
      return index + 1 + (this.queryParam.pageNum - 1) * this.queryParam.pageSize;
    },
    setQueryOperationTime() {
      this.queryParam.beginTime = null
      this.queryParam.endTime = null
      if (this.operationTime) {
        this.queryParam.beginTime = this.operationTime[0]
        this.queryParam.endTime = this.operationTime[1]
      }
    },
    search() {
      this.getLogList()
    }
  }
}
</script>

<style scoped>
  .row-title {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    border-bottom: 1px solid #ddd;
    margin-bottom: 10px
  }
</style>
