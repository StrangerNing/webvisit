<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <h2>企业基本信息设置</h2>
        </el-row>
        <el-form style="margin-top: 50px">
          <el-form-item label="企业名称">
            <el-input v-model="companyInfo.name" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="公司地址">
            <el-input v-model="companyInfo.address" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="经营范围">
            <el-input v-model="companyInfo.scopeOfBusiness" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="公司网址">
            <el-input v-model="companyInfo.url" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="成立时间">
            <el-input v-model="companyInfo.createTime" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="注册时间">
            <el-input v-model="companyInfo.registTime" style="width: 300px;" />
          </el-form-item>
          <el-button type="primary" style="margin-top: 30px;margin-left: 50px" @click="saveCompanyInfo()">确定</el-button>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { editCompanyInfo, getCompanyInfo } from '../../api/yellowPage'
import { Message } from 'element-ui'

export default {
  name: 'Index',
  data() {
    return {
      companyInfo: {},
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  created() {
    this.getCompanyInfo()
  },
  methods: {
    getCompanyInfo() {
      getCompanyInfo().then(res => {
        this.companyInfo = res.data
      })
    },
    saveCompanyInfo() {
      editCompanyInfo(this.companyInfo).then(res => {
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
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
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
