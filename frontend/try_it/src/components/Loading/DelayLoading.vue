
<style scoped>
  .spin-content{
    border: 1px solid #91d5ff;
    background-color: #e6f7ff;
    padding: 30px;
  }
</style>
<template>
  <div>
    <a-spin :spinning="spinning" :delay="delayTime" tips="Loading...">
      <div class="spin-content"></div>
    </a-spin>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        spinning: true,
        delayTime: 500,
      }
    }
  }
</script>
