<template>
  <el-dialog
    :title="downloadTitle"
    :visible.sync="showDownload"
  >
    <div style="min-height: 200px">
      <el-row>
        <el-col>
          <div style="text-align: center;">
            <el-progress v-if="exportOnProgress" type="circle" :percentage="exportPercentage" />
            <el-progress v-else type="circle" :percentage="exportPercentage" status="success" />
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div style="text-align: center;margin-top: 20px">
            <el-button v-if="!exportOnProgress" type="primary" @click="downloadFile">下载</el-button>
          </div>
          <div style="text-align: center;margin-top: 20px">
            <el-button v-if="retry" type="primary" @click="setTimeInterval">重试</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-dialog>
</template>

<script>
import { getDownloadUrl } from '../../api/attence'
import { openUrl } from '../../utils/openUrl'
import { Message } from 'element-ui'

export default {
  name: 'Export',
  props: {
    exportUUID: {
      type: String,
      default() {
        return null
      }
    },
    showDownload: {
      type: Boolean,
      default() {
        return false
      }
    }
  },
  data() {
    return {
      exportPercentage: 0,
      exportOnProgress: true,
      downloadTitle: '生成下载文件',
      retry: false
    }
  },
  watch: {
    showDownload(val) {
      if (val) {
        this.setTimeInterval()
      } else {
        this.$emit('showDownload', this.showDownload)
      }
    }
  },
  methods: {
    setTimeInterval() {
      this.exportPercentage = 0
      this.exportOnProgress = true
      this.downloadTitle = '生成下载文件'
      this.retry = false
      const clock = window.setInterval(() => {
        this.exportPercentage++
        if (this.exportPercentage === 100) {
          window.clearInterval(clock)
          this.exportOnProgress = false
          this.downloadTitle = '文件下载'
        }
      }, 50)
    },
    downloadFile() {
      getDownloadUrl({ uuid: this.exportUUID }).then(res => {
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
    }
  }
}
</script>

<style scoped>

</style>
