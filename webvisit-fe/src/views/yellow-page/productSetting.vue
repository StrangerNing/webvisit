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
              label="产品链接">
              <span slot-scope="scope">
                <span @click="see(scope.row.productUrl)" style="cursor: pointer">{{scope.row.productUrl}}</span>
              </span>
            </el-table-column>
            <el-table-column
              prop="detail"
              label="产品详情"
            />
            <el-table-column
              label="产品图片"
            >
              <span slot-scope="scope">
                <span v-for="(value,index) in scope.row.productImgList">
                  <span v-if="index<6">
                    <img :src="value.imgUrl" style="width: 50px;height: 50px;cursor: pointer" @click="showImage(value.imgUrl)">
                  </span>
              </span>
              </span>
            </el-table-column>
            <el-table-column
              label="操作"
            >
              <span slot-scope="scope">
                <el-row :gutter="8">
                  <el-col :span="6">
                    <el-button
                      type="primary"
                      size="small"
                      icon="el-icon-edit"
                      @click="editProduct(scope.$index, scope.row)"
                    >编辑</el-button>
                  </el-col>
                  <el-col :span="6">
                    <el-button
                      type="success"
                      size="small"
                      icon="el-icon-search"
                      @click="showProduct(scope.$index, scope.row)"
                    >查看</el-button>
                  </el-col>
                  <el-col :span="6">
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
              <el-input v-model="product.name" style="width: 80%;"/>
            </el-form-item>
            <el-form-item label="产品链接：">
              <el-input v-model="product.productUrl" style="width: 80%;"/>
            </el-form-item>
            <el-form-item label="产品详情：">
              <el-input v-model="product.detail" style="width: 80%;" type="textarea" rows="3"/>
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
          :title="showProductImgTitle"
          :visible.sync="editProductImgVisible"
        >
          <div v-if="editProductImg">
            <el-button type="primary" @click="addProductImg()">添加</el-button>
          </div>
          <div v-else>
            <el-button type="primary" @click="editProductImage()" v-if="isShow">编辑</el-button>
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
                  <div class="el-upload el-upload--picture-card">
                    <span v-if="scope.row.imgUrl" @click="showImage(scope.row.imgUrl)">
                      <img :src="scope.row.imgUrl" style="width: 145px;height: 145px;">
                    </span>
                    <span v-else>
                      <i class="el-icon-plus" @click="chooseProductImg(scope.$index, scope.row)"/>
                    </span>
                  </div>
                </span>
                <span v-else>
                  <img :src="scope.row.imgUrl" style="width: 145px;height: 145px;cursor: pointer"
                       @click="showImage(scope.row.imgUrl)">
                </span>
              </span>
            </el-table-column>
            <el-table-column
              prop="imgDetail"
              label="图片详情"
            >
              <span slot-scope="scope">
                <span v-if="editProductImg">
                  <el-input v-model="scope.row.imgDetail" type="textarea" rows="6"/>
                </span>
                <span v-else>
                  {{ scope.row.imgDetail }}
                </span>
              </span>
            </el-table-column>
            <el-table-column
              label="操作"
              v-if="isShow"
            >
              <span slot-scope="scope">
                <el-row>
                  <div v-if="editProductImg">
                    <el-button
                      type="primary"
                      icon="el-icon-picture"
                      @click="chooseProductImg(scope.$index, scope.row)"
                    >选择图片
                      <input :id="upload_file + scope.$index" type="file" style="display: none"
                             @change="photoChange($event)"></input>
                    </el-button>
                    <el-button
                      type="primary"
                      icon="el-icon-upload"
                      @click="submitProductImg(scope.$index, scope.row)"
                    >提交</el-button>
                  </div>
                  <div v-else>
                    <el-button
                      type="danger"
                      size="small"
                      icon="el-icon-delete"
                      @click="deleteProductImg(scope.$index, scope.row)"
                    >删除</el-button>
                  </div>
                </el-row>
              </span>
            </el-table-column>
          </el-table>
        </el-dialog>
        <el-dialog :visible.sync="dialogImgVisible">
          <img width="100%" :src="dialogImageUrl" alt="" style="max-height: fit-content;max-width: fit-content">
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {
    addCompanyProduct, addProductImg,
    deleteCompanyProduct, deleteProductImg,
    editCompanyProduct,
    getCompanyProduct, updateProductImg,
    uploadApi as yellowPage, uploadImg
  } from '../../api/yellowPage'
  import { Message } from 'element-ui'
  import { openUrl } from '../../utils/openUrl'

  export default {
    name: 'ProductSetting',
    data() {
      return {
        saveImg: yellowPage.saveImg,
        productList: [],
        editProductVisible: false,
        editProductTitle: '编辑产品信息',
        showProductImgTitle: '编辑产品图片',
        editProductImgVisible: false,
        editProductImg: false,
        dialogImgVisible: false,
        isShow: true,
        dialogImageUrl: '',
        upload_file: 'upload_file',
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
        index: null,
        row: null
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
        console.log(this.productImgList,this.productList[index].productImgList)
        this.productImgList = []
        this.productImgList = JSON.parse(JSON.stringify(this.productList[index].productImgList))
        // for (const item in this.productList[index].productImgList) {
        //   this.productImgList.push(this.productList[index].productImgList[item])
        // }
        this.editProductVisible = true
      },
      showProduct(index, row) {
        this.isShow = false
        this.productImgList = []
        this.productImgList = JSON.parse(JSON.stringify(this.productList[index].productImgList))
        this.showProductImgTitle = '查看产品图片'
        this.editProductVisible = false
        this.editProductImgVisible = true
        this.editProductImg = false
      },
      editCompanyImg() {
        this.isShow = true
        this.showProductImgTitle = '编辑产品图片'
        this.editProductVisible = false
        this.editProductImgVisible = true
        this.editProductImg = false
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
        console.log(index,row)
        if (row.id == null) {
          addProductImg(row).then(res => {
            this.checkSubmitImg(res)
          })
        } else {
          updateProductImg(row).then(res => {
            this.checkSubmitImg(res)
          })
        }
      },
      checkSubmitImg(res) {
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
      },
      showImage(url) {
        this.dialogImageUrl = url
        this.dialogImgVisible = true
      },
      photoChange(el) {
        const file = el.target.files[0]
        const formData = new FormData()
        formData.append('file', file)
        uploadImg(formData).then(res => {
          this.row.imgUrl = res.data
          Message({
            message: '上传成功',
            type: 'success'
          })
        })
      },
      chooseProductImg(index, row) {
        this.index = index
        this.row = row
        let id = this.upload_file+index
        console.log(index, row)
        document.getElementById(id).click()
      },
      deleteProductImg(index, row) {
        let that = this
        deleteProductImg({id :row.id}).then(res => {
          if (res.data) {
            Message({
              message: '删除成功',
              type: 'success',
              duration: 10 * 1000
            })
            this.getProductList()
            this.editProductImgVisible = false
          } else {
            Message({
              message: '删除失败',
              type: 'error',
              duration: 10 * 1000
            })
          }
        })
      },
      see(e) {
        openUrl(e)
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
