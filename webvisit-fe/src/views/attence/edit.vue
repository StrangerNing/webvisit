<template>
  <div style="margin-bottom: 100px">
    <edit-regulation />
    <Annual />
    <div class="module">
      <el-row :gutter="8">
        <el-col :span="12">
          <holiday />
        </el-col>
        <el-col :span="12">
          <leave />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Annual from '../attence/annual'
import EditRegulation from './editRegulation'
import Leave from './leave'
import Holiday from './holiday'

export default {
  name: 'Edit',
  components: {
    Holiday,
    Leave,
    EditRegulation,
    Annual
  }
}
</script>

<style scoped>
  .module {
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 20px;
  }
</style>
