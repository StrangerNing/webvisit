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
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="企业登录页logo">
                <el-upload
                  :action="saveImg"
                  list-type="picture-card"
                  :show-file-list="false"
                  :on-success="handleLoginLogo"
                >
                  <img v-if="companyInfo.loginLogo" :src="companyInfo.loginLogo" style="width: 145px;height: 145px">
                  <i v-else class="el-icon-plus" />
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="6">

              <el-form-item label="主页右上角logo">
                <el-upload
                  :action="saveImg"
                  list-type="picture-card"
                  :show-file-list="false"
                  :before-upload="beforeUpload"
                  :on-success="handlePageLogo"
                >
                  <img v-if="companyInfo.pageLogo" :src="companyInfo.pageLogo" style="width: 145px;height: 145px">
                  <i v-else class="el-icon-plus" />
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="浏览器右上角logo">
                <el-upload
                  :action="saveImg"
                  list-type="picture-card"
                  :show-file-list="false"
                  :on-success="handleWebLogo"
                >
                  <img v-if="companyInfo.webLogo" :src="companyInfo.webLogo" style="width: 145px;height: 145px">
                  <i v-else class="el-icon-plus" />
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <div style="text-align: center">
                <el-button type="primary" style="margin-top: 30px;" icon="el-icon-upload" size="medium" @click="saveCompanyInfo()">确定</el-button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { editCompanyInfo, getCompanyInfo, uploadApi as yellowPage } from '../../api/yellowPage'
import { Message } from 'element-ui'

export default {
  name: 'Index',
  data() {
    return {
      saveImg: yellowPage.saveImg,
      companyInfo: {
        id: null,
        name: '',
        address: '',
        scopeOfBusiness: '',
        type: null,
        url: '',
        createTime: null,
        registTime: null,
        loginLogo: null,
        loginLogoChanged: false,
        pageLogo: null,
        pageLogoChanged: false,
        webLogo: null,
        webLogoChanged: false
      }
    }
  },
  created() {
    this.getCompanyInfo()
  },
  methods: {
    getCompanyInfo() {
      getCompanyInfo().then(res => {
        this.companyInfo = res.data
        this.companyInfo.loginLogoChanged = false
        this.companyInfo.pageLogoChanged = false
        this.companyInfo.webLogoChanged = false
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
    handleLoginLogo(response, file, fileList) {
      console.log(response)
      this.companyInfo.loginLogo = response.data
      this.companyInfo.loginLogoChanged = true
    },
    handlePageLogo(response, file, fileList) {
      console.log(response)
      this.companyInfo.pageLogo = response.data
      this.companyInfo.pageLogoChanged = true
    },
    beforeUpload(file) {
      console.log(file)
    },
    handleWebLogo(response, file, fileList) {
      console.log(response)
      this.companyInfo.webLogo = response.data
      this.companyInfo.webLogoChanged = true
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

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
