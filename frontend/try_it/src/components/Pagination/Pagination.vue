<template>
  <a-pagination
    :pageSizeOptions="pageSizeOptions"
    :total="total"
    showSizeChanger
    :pageSize="pageSize"
    v-model="current"
    @showSizeChange="onShowSizeChange"
  >
      <template slot='buildOptionText' slot-scope='props'>
        <span v-if="props.value!=='30'">{{props.value}}条/页</span>
        <span v-if="props.value==='30'">全部</span>
      </template>
    </a-pagination>
</template>
<script>
  export default {
    data() {
      return {
        pageSizeOptions: ['10', '20', '30'],
        current: 1,
        pageSize: 10,
        total: 30,
      }
    },
    methods: {
      onShowSizeChange(current, pageSize) {
        this.pageSize = pageSize
      },
    }
  }
</script>
