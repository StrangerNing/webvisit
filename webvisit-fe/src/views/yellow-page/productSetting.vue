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
            />
            <el-table-column
              label="操作"
            >
              <span slot-scope="scope">
                <el-row>
                  <el-col :span="12">
                    <el-button type="primary" icon="el-icon-edit" @click="editProduct(scope.$index, scope.row)">编辑</el-button>
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
        </el-row><el-dialog
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
            <el-form-item label="编辑产品图片：">
              <el-button icon="el-icon-edit" @click="editCompanyImg()">编辑</el-button>
            </el-form-item>
          </el-form>
          <el-row style="text-align: center;margin-top: 100px">
            <el-button icon="el-icon-close" style="margin-right: 10px" @click="editProductVisible=false">取消</el-button>
            <el-button type="primary" icon="el-icon-upload" style="margin-left: 10px" @click="commitProduct()">提交
            </el-button>
          </el-row>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import { addCompanyProduct, deleteCompanyProduct, editCompanyProduct, getCompanyProduct } from '../../api/yellowPage'
import { Message } from 'element-ui'

export default {
  name: 'ProductSetting',
  data() {
    return {
      productList: [],
      editProductVisible: false,
      editProductTitle: '编辑产品信息',
      product: {
        id: '',
        companyId: '',
        name: '',
        detail: '',
        productUrl: '',
        productImgList: []
      }
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
