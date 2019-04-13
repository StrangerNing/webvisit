<template>
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
        <el-col :span="24">
          <div style="text-align: center;margin-top: 20px">
            <span>选择节假日日期：</span>
            <el-date-picker
              v-model="addHoliday.date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            />
          </div>
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
</template>

<script>
import { cancelHoliday, getHolidayList, setHoliday } from '../../api/attence'
import { Message } from 'element-ui'

export default {
  name: 'Holiday',
  data() {
    return {
      searchDate: {
        beginDate: this.getYearFirstDay(new Date(), 0),
        endDate: this.getYearFirstDay(new Date(), 1)
      },
      addHolidayVisible: false,
      holidayList: [],
      addHoliday: {
        date: ''
      }
    }
  },
  created() {
    this.getHolidayList()
  },
  methods: {
    getYearFirstDay(year, manyYear) {
      year = year.setFullYear(year.getFullYear() + manyYear, 0, 1)
      console.log('第一天', year)
      return year
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
