<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>考勤报告</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-form>
            <el-col :span="8">
              <el-form-item label="职工姓名：">
                <el-input v-model="queryParam.empName" style="max-width: 80%" placeholder="支持模糊搜索"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="部门：">
                <el-select v-model="queryParam.deptId" placeholder="选择部门" clearable>
                  <el-option
                    v-for="item in deptList"
                    :key="item.id"
                    :label = "item.name"
                    :value = "item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="getPunchReport">搜索</el-button>
            </el-col>
          </el-form>
        </el-row>
        <el-table :data="allPunchReport" stripe fit border>
          <el-table-column
            type="index"
            :index="handelAllIndex"
          />
          <el-table-column
            prop="empName"
            label="职员姓名"
          />
          <el-table-column
            prop="deptName"
            label="所属部门"
          />
          <el-table-column
            prop="punchInCount"
            label="签到次数"
          />
          <el-table-column
            prop="punchInLateCount"
            label="迟到次数"
          />
          <el-table-column
            prop="punchInMissCount"
            label="未签到次数"
          />
          <el-table-column
            prop="punchOutCount"
            label="签退次数"
          />
          <el-table-column
            prop="punchOutEarlyCount"
            label="早退次数"
          />
          <el-table-column
            prop="punchOutMissCount"
            label="未签退次数"
          />
          <el-table-column
            prop="askLeaveCount"
            label="请假次数"
          />
          <el-table-column
            prop="workOutsideCount"
            label="外勤次数"
          />
          <el-table-column
            label="操作"
          >
            <span slot-scope="scope">
              <el-button
                type="success"
                size="small"
                icon="el-icon-search"
                @click="getPunchDetail(scope.row.empId)"
              >查看</el-button>
            </span>
          </el-table-column>
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
        <el-dialog
          title="考勤详情查看"
          :visible.sync="showPunchDetail"
        >
          <el-row>
            <el-form>
              <el-col :span="16">
                <el-form-item
                  label="选择考勤时间段">
                  <el-date-picker style="width: 80%"
                                  v-model.trim="queryTime"
                                  value-format="yyyy-MM-dd"
                                  format="yyyy-MM-dd"
                                  type="daterange"
                                  range-separator="至"
                                  start-placeholder="开始日期"
                                  end-placeholder="结束日期"
                                  @change="setQueryPunchTime" />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-button type="primary" @click="getPunchDetail(queryDetailParam.empId)">搜索</el-button>
                <el-button type="success" @click="exportPunchDetail(queryDetailParam.empId)">导出</el-button>
              </el-col>
            </el-form>
          </el-row>
          <el-table :data="punchDetail" stripe fit border max-height="500">
            <el-table-column
              type="index"
              :index="handelIndex"
            />
            <el-table-column
              prop="nickname"
              label="职员姓名"
            />
            <el-table-column
              prop="punchTime"
              label="考勤日期"
            />
            <el-table-column
              prop="punchType"
              label="考勤类型"
            >
              <span slot-scope="scope">
                {{ getAttenceType(scope.row.punchType) }}
              </span>
            </el-table-column>
            <el-table-column
              prop="punchInTime"
              label="签到时间"
            />
            <el-table-column
              prop="punchInStatus"
              label="签到状态"
            >
              <span slot-scope="scope">
                {{ getPunchInType(scope.row.punchInStatus) }}
              </span>
            </el-table-column>
            <el-table-column
              prop="punchOutTime"
              label="签退时间"
            />
            <el-table-column
              prop="punchOutStatus"
              label="签退状态"
            >
              <span slot-scope="scope">
                {{ getPunchOutType(scope.row.punchOutStatus) }}
              </span>
            </el-table-column>
            <el-table-column
              label="签到地点"
            />
            <el-table-column
              label="签退地点"
            />
          </el-table>
          <el-pagination
            style="margin-top: 20px;text-align: center"
            @size-change="handleDetailSizeChange"
            @current-change="handleDetailCurrentChange"
            :current-page="queryDetailParam.pageNum"
            :page-sizes="[10,20,50,100]"
            :page-size="queryDetailParam.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="detailTotal">
          </el-pagination>
          <el-row style="text-align: center;margin-top: 50px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="showPunchDetail=false">关闭</el-button>
          </el-row>
        </el-dialog>
        <el-dialog
          title="文件下载"
          :visible.sync="showDownload">
          <div style="min-height: 200px">
            <el-row>
              <el-col>
                <div style="text-align: center;">
                  <el-progress type="circle" :percentage="exportPercentage" v-if="exportOnProgress"></el-progress>
                  <el-progress type="circle" :percentage="exportPercentage" v-else status="success"></el-progress>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <div style="text-align: center;margin-top: 20px">
                  <el-button type="primary" v-if="!exportOnProgress" @click="downloadFile">下载</el-button>
                </div>
                <div style="text-align: center;margin-top: 20px">
                  <el-button type="primary" v-if="retry" @click="setTimeInterval">重试</el-button>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {
    exportPunchDetail,
    getAllPunchReport,
    getDeptList,
    getDownloadUrl,
    getPunchDetailReport
  } from '../../api/attence'
import enums from './enums'
  import { Message } from 'element-ui'
  import { openUrl } from '../../utils/openUrl'

export default {
  name: 'AttenceReport',
  data() {
    return {
      allPunchReport: [],
      punchDetail: [],
      deptList: [],
      showPunchDetail: false,
      queryTime: null,
      queryParam: {
        companyId: null,
        deptId: null,
        deptName: null,
        empName: null,
        pageNum: 1,
        pageSize: 10
      },
      total: null,
      detailTotal: null,
      queryDetailParam: {
        empId: null,
        pageNum: 1,
        pageSize: 10,
        beginTime: null,
        endTime: null
      },
      exportUUID: null,
      showDownload: false,
      exportPercentage: 0,
      exportOnProgress: true,
      retry: false
    }
  },
  computed: {
    getAttenceType() {
      return function(param) {
        return enums.attenceTypeEnum.getLabelByValue(param)
      }
    },
    getPunchInType() {
      return function(param) {
        return enums.punchInStatus.getLabelByValue(param)
      }
    },
    getPunchOutType() {
      return function(param) {
        return enums.punchOutStatus.getLabelByValue(param)
      }
    }
  },
  created() {
    this.getPunchReport()
    this.getDeptList()
  },
  methods: {
    getPunchReport() {
      for (let item in this.queryParam) {
        if (this.queryParam[item] == '') {
          this.queryParam[item] = null
        }
      }
      getAllPunchReport(this.queryParam).then(res => {
        this.allPunchReport = res.data.list
        this.queryParam.pageSize = res.data.pageSize
        this.total = res.data.total
      })
    },
    getDeptList() {
      getDeptList().then(res => {
        this.deptList = res.data
      })
    },
    getPunchDetail(empId) {
      this.queryDetailParam.empId = empId
      getPunchDetailReport(this.queryDetailParam).then(res => {
        this.punchDetail = res.data.list
        this.queryDetailParam.pageSize = res.data.pageSize
        this.detailTotal = res.data.total
      })
      this.showPunchDetail = true
    },
    exportPunchDetail(empId) {
      this.showPunchDetail =false
      this.queryDetailParam.empId = empId
      exportPunchDetail(this.queryDetailParam).then(res => {
        this.exportUUID = res.data
        this.setTimeInterval()
      })
    },
    setTimeInterval() {
      this.exportPercentage = 0
      this.exportOnProgress = true
      this.showDownload = true
      this.retry = false
      let clock = window.setInterval(() => {
        this.exportPercentage ++
        if (this.exportPercentage === 100) {
          window.clearInterval(clock)
          this.exportOnProgress = false
        }
      },50)
    },
    downloadFile() {
      getDownloadUrl({uuid : this.exportUUID}).then(res => {
        if (res.success) {
          openUrl(res.data)
        } else {
          Message({
            message: res.message,
            type: 'error',
            duration: 5 * 1000
          })
          this.retry = true
          this.exportPercentage = 0
          this.exportOnProgress = true
          this.showDownload = true
        }
      })
    },
    setQueryPunchTime() {
      this.queryDetailParam.beginTime = null
      this.queryDetailParam.endTime = null
      if (this.queryTime) {
        this.queryDetailParam.beginTime = this.queryTime[0]
        this.queryDetailParam.endTime = this.queryTime[1]
      }
    },
    handleSizeChange(val) {
      this.queryParam.pageSize = val
      this.queryParam.pageNum = 1
      this.getPunchReport()
    },
    handleCurrentChange(val) {
      this.queryParam.pageNum = val
      this.getPunchReport()
    },
    handleDetailSizeChange(val) {
      this.queryDetailParam.pageSize = val
      this.queryDetailParam.pageNum = 1
      this.getPunchDetail(this.queryDetailParam.empId)
    },
    handleDetailCurrentChange(val) {
      this.queryDetailParam.pageNum = val
      this.getPunchDetail(this.queryDetailParam.empId)
    },
    handelIndex(index) {
      return index + 1 + (this.queryDetailParam.pageNum - 1) * this.queryDetailParam.pageSize
    },
    handelAllIndex(index) {
      return index + 1 + (this.queryParam.pageNum -1) * this.queryDetailParam.pageSize
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
