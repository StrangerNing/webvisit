<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>企业产品设置</h2>
          </el-col>
          <el-col :span="11" style="text-align: right;margin-bottom:0.3%;">
            <el-button type="primary" icon="el-icon-plus" @click="newProduct()">添加</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-table :data="productList" border stripe fit>
            <el-table-column
              type="index"
            />
            <el-table-column
              prop="name"
              label="产品名称"
            />
            <el-table-column
              prop="productUrl"
              label="产品链接"
            />
            <el-table-column
              prop="detail"
              label="产品详情"
            />
            <el-table-column
              label="产品图片"
            >
              <span v-for="(value,key,index) in productList.productImgList">
                <span v-if="index < 6">
                  <img :src="value.imgUrl">
                </span>
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
                      icon="el-icon-edit"
                      @click="editProduct(scope.$index, scope.row)"
                    >编辑</el-button>
                  </el-col>
                  <el-col :span="12">
                    <el-button
                      type="danger"
                      size="small"
                      icon="el-icon-delete"
                      @click="deleteProduct(scope.$index, scope.row)"
                    >删除</el-button>
                  </el-col>
                </el-row>
              </span>
            </el-table-column>
          </el-table>
        </el-row>
        <el-dialog
          :title="editProductTitle"
          :visible.sync="editProductVisible"
        >
          <el-form :model="product" label-width="25%" style="width: 100%;">
            <el-form-item label="产品名称：">
              <el-input v-model="product.name" style="width: 80%;" />
            </el-form-item>
            <el-form-item label="产品链接：">
              <el-input v-model="product.productUrl" style="width: 80%;" />
            </el-form-item>
            <el-form-item label="产品详情：">
              <el-input v-model="product.detail" style="width: 80%;" type="textarea" rows="3" />
            </el-form-item>
            <el-form-item v-if="product.id" label="编辑产品图片：">
              <el-button icon="el-icon-edit" @click="editCompanyImg()">编辑</el-button>
            </el-form-item>
          </el-form>
          <el-row style="text-align: center;margin-top: 100px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="editProductVisible=false">取消</el-button>
            <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitProduct()">提交
            </el-button>
          </el-row>
        </el-dialog>
        <el-dialog
          title="编辑产品图片"
          :visible.sync="editProductImgVisible"
        >
          <div v-if="editProductImg">
            <el-button type="primary" @click="addProductImg()">添加</el-button>
          </div>
          <div v-else>
            <el-button type="primary" @click="editProductImage()">编辑</el-button>
          </div>
          <el-table :data="productImgList" border stripe fit style="margin-top: 20px">
            <el-table-column
              type="index"
            />
            <el-table-column
              label="产品图片"
            >
              <span slot-scope="scope">
                <span v-if="editProductImg">
                  <div class="el-upload el-upload--picture-card" @click="showImage(scope.row.imgUrl)">
                    <span v-if="scope.row.imgUrl">
                      <img :src="scope.row.imgUrl" style="width: 145px;height: 145px;">
                    </span>
                    <span v-else>
                      <i class="el-icon-plus" />
                    </span>
                  </div>
                </span>
                <span v-else>
                  <img :src="scope.row.imgUrl" style="width: 145px;height: 145px;cursor: pointer" @click="showImage(scope.row.imgUrl)">
                </span>
              </span>
            </el-table-column>
            <el-table-column
              prop="imgDetail"
              label="图片详情"
            >
              <span slot-scope="scope">
                <span v-if="editProductImg">
                  <el-input v-model="scope.row.imgDetail" type="textarea" rows="6" />
                </span>
                <span v-else>
                  {{ scope.row.imgDetail }}
                </span>
              </span>
            </el-table-column>
            <el-table-column
              label="操作"
            >
              <span slot-scope="scope">
                <span v-if="editProductImg">
                  <el-button
                    type="primary"
                    icon="el-icon-picture"
                    @click="chooseProductImg(scope.$index, scope.row)"
                  >选择图片
                    <input id="upload_file" type="file" style="display: none" @change="photoChange($event)"></input></el-button>
                  <el-button
                    type="primary"
                    icon="el-icon-upload"
                    @click="submitProductImg(scope.$index, scope.row)"
                  >提交</el-button>
                </span>
              </span>
            </el-table-column>
          </el-table>
        </el-dialog>
        <el-dialog :visible.sync="dialogImgVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import {
  addCompanyProduct, addProductImg,
  deleteCompanyProduct,
  editCompanyProduct,
  getCompanyProduct,
  uploadApi as yellowPage, uploadImg
} from '../../api/yellowPage'
import { Message } from 'element-ui'

export default {
  name: 'ProductSetting',
  data() {
    return {
      saveImg: yellowPage.saveImg,
      productList: [],
      editProductVisible: false,
      editProductTitle: '编辑产品信息',
      editProductImgVisible: false,
      editProductImg: false,
      dialogImgVisible: false,
      dialogImageUrl: '',
      product: {
        id: null,
        companyId: null,
        name: '',
        detail: '',
        productUrl: '',
        productImgList: []
      },
      productImgList: [],
      productImg: {
        id: null,
        productId: null,
        imgUrl: '',
        imgDetail: ''
      },
      custom: ''
    }
  },
  created() {
    this.getProductList()
  },
  methods: {
    getProductList() {
      getCompanyProduct().then(res => {
        this.productList = res.data
      })
    },
    newProduct() {
      this.editProductTitle = '新增产品'
      for (const key in this.product) {
        this.product[key] = null
      }
      this.editProductVisible = true
    },
    commitProduct() {
      if (this.product.id == null) {
        addCompanyProduct(this.product).then(res => {
          this.checkCommitProduct(res)
        })
      } else {
        editCompanyProduct(this.product).then(res => {
          this.checkCommitProduct(res)
        })
      }
    },
    deleteProduct(index, row) {
      this.$confirm('被删除的产品无法恢复, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCompanyProduct({ productId: row.id }).then(res => {
          if (res.data) {
            Message({
              message: '删除成功',
              type: 'success',
              duration: 10 * 1000
            })
            this.getProductList()
          } else {
            Message({
              message: '删除失败',
              type: 'error',
              duration: 10 * 1000
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    checkCommitProduct(res) {
      if (res.data) {
        Message({
          message: '提交成功',
          type: 'success',
          duration: 10 * 1000
        })
        this.editProductVisible = false
        this.getProductList()
      } else {
        Message({
          message: '提交失败',
          type: 'error',
          duration: 10 * 1000
        })
      }
    },
    editProduct(index, row) {
      this.editProductTitle = '编辑产品信息'
      for (const key in row) {
        this.product[key] = row[key]
      }
      this.editProductVisible = true
    },
    editCompanyImg() {
      this.editProductVisible = false
      this.editProductImgVisible = true
      this.productImgList = []
      this.editProductImg = false
      for (const item in this.product.productImgList) {
        this.productImgList[item] = this.product.productImgList[item]
      }
    },
    addProductImg() {
      const productImg = {
        id: null,
        productId: this.product.id,
        imgUrl: null,
        imgDetail: null
      }
      this.productImgList.push(productImg)
    },
    editProductImage() {
      this.editProductImg = true
    },
    submitProductImg(index, row) {
      if (row.id == null) {
        addProductImg(row).then(res => {
          if (res.data) {
            Message({
              message: '提交成功',
              type: 'success',
              duration: 10 * 1000
            })
            this.getProductList()
          } else {
            Message({
              message: '提交失败',
              type: 'error',
              duration: 10 * 1000
            })
          }
        })
      }
    },
    saveImage: function(param) {
      const formData = new FormData()
      formData.append('file', param.file)
      formData.append('type', 'SKU')
      console.log(param)
      uploadImg(formData).then(res => {
        this.productImg.imgUrl = res.data
      })
    },
    showImage(url) {
      this.dialogImageUrl = url
      this.dialogImgVisible = true
    },
    photoChange(el) {
      const file = el.target.files[0]
      const type = file.type.split('/')[0]
      if (type === 'image') {
        const reader = new FileReader()
        reader.readAsDataURL(file)
        const that = this
        reader.onloadend = function() {
          const dataURL = reader.result
          const blob = that.dataURItoBlob(dataURL)
          that.upload(blob)
        }
      } else {
        Message({
          message: '请重新选择图片',
          type: 'error',
          duration: 10 * 1000
        })
      }
    },
    chooseProductImg() {
      document.getElementById('upload_file').click()
    },
    dataURItoBlob(dataURI) {
      const byteString = window.atob(dataURI.split(',')[1])
      const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
      const T = mimeString.split('/')[1]
      const ab = new ArrayBuffer(byteString.length)
      const ia = new Uint8Array(ab)
      for (let i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i)
      }
      return new Blob([ab], { type: mimeString })
    },
    upload(imgUrl) {
      const formData = new FormData()
      formData.append('file', imgUrl)
      uploadImg(formData).then(res => {
        Message({
          message: '上传成功',
          type: 'success'
        })
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
