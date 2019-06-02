<template>
  <div style="margin-bottom: 100px">
    <div class="module">
      <el-card>
        <el-row class="row-title">
          <el-col :span="12">
            <h2>访客管理</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-form>
            <el-col :span="8">
              <el-form-item
                label="访客名：">
                <el-input v-model="queryParam.name" style="max-width: 60%" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="邀请人：">
                <el-input v-model="queryParam.nickname" style="max-width: 60%" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="queryVisitor()">搜索</el-button>
            </el-col>
          </el-form>
        </el-row>
        <el-row>
          <el-table :data="visitors" border stripe fit>
            <el-table-column
              type="index"
            />
            <el-table-column
              prop="name"
              label="访客名称"
            />
            <el-table-column
              prop="nickname"
              label="邀请人">
            </el-table-column>
            <el-table-column
              label="人脸图片"
            >
              <span slot-scope="scope">
                <div class="el-upload el-upload--picture-card">
                  <span v-if="scope.row.faceInfo" @click="showImage(scope.row.faceInfo)">
                    <img :src="scope.row.faceInfo" style="width: 145px;height: 145px;">
                  </span>
                  <span v-else>
                    暂无信息
                  </span>
                </div>
              </span>
            </el-table-column>
            <el-table-column
              label="名片"
            >
              <span slot-scope="scope">
                <div class="el-upload el-upload--picture-card">
                  <span v-if="scope.row.businessCard" @click="showImage(scope.row.businessCard)">
                    <img :src="scope.row.businessCard" style="width: 145px;height: 145px;">
                  </span>
                  <span v-else>
                    暂无信息
                  </span>
                </div>
              </span>
            </el-table-column>
            <el-table-column
              label="身份证图片"
            >
              <span slot-scope="scope">
                <div class="el-upload el-upload--picture-card">
                  <span v-if="scope.row.idCard" @click="showImage(scope.row.idCard)">
                    <img :src="scope.row.idCard" style="width: 145px;height: 145px;">
                  </span>
                  <span v-else>
                    暂无信息
                  </span>
                </div>
              </span>
            </el-table-column>
            <el-table-column
              label="操作"
              min-width="100px"
            >
              <span slot-scope="scope">
                <el-row :gutter="8">
                  <el-col>
                    <el-button
                      type="danger"
                      size="small"
                      icon="el-icon-delete"
                      @click="deleteVisitor(scope.$index, scope.row)"
                    >删除</el-button>
                  </el-col>
                </el-row>
              </span>
            </el-table-column>
          </el-table>
        </el-row>
        <el-dialog :visible.sync="dialogImgVisible">
          <img width="100%" :src="dialogImageUrl" alt="" style="max-height: fit-content;max-width: fit-content">
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
  import { deleteVisitor, queryVisitors } from '../../api/visitor'
  import { Message } from 'element-ui'

  export default {
    name: 'visitor',
    data() {
      return {
        visitors: [],
        queryParam: {
          name: null,
          nickname: null
        },
        dialogImageUrl: null,
        dialogImgVisible: false
      }
    },
    created() {
      this.queryVisitor()
    },
    computed: {
      getVisitorName() {
        return this.queryParam.name
      },
      getNickName() {
        return this.queryParam.nickname
      }
    },
    watch: {
      getVisitorName(val) {
        if (val === '') {
          this.queryParam.name = null
        }
      },
      getNickName(val) {
        if (val === '') {
          this.queryParam.nickname = null
        }
      }
    },
    methods: {
      queryVisitor() {
        queryVisitors(this.queryParam).then(res => {
          this.visitors = res.data
        })
      },
      showImage(url) {
        this.dialogImageUrl = url
        this.dialogImgVisible = true
      },
      deleteVisitor(index,row) {
        deleteVisitor({ id: row.id}).then(res => {
          if (res.data) {
            Message({
              message: '删除成功',
              type: 'success',
              duration: 10 * 1000
            })
            this.queryVisitor()
          } else {
            Message({
              message: '删除失败',
              type: 'error',
              duration: 10 * 1000
            })
          }
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
