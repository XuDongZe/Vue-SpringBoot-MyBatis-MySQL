<template>
  <a-layout class="home-layout">
    <!-- 顶部导航栏 -->
    <HomeHeadBar :HeadBarInfo="HeadBarInfo"></HomeHeadBar>
    <!-- 中间content部分 -->
    <div class="home-content-left-right">
      <HomeContent :HeadBarInfo="HeadBarInfo"></HomeContent>
    </div>
    <!-- 底部信息 -->
    <HomeFooter></HomeFooter>
  </a-layout>
</template>

<script>
// import HomeSideBar from '@/components/HomeSideBar'
import HomeHeadBar from '@/components/HomeHeadBar'
import HomeFooter from '@/components/HomeFooter'
import HomeContent from '@/components/HomeContent'
export default {
  name: "HomePage",
  components: {
    HomeHeadBar, HomeFooter, HomeContent
  },
  data() {
    return {
      HeadBarHeight: 64
    }
  },
  computed: {
    HeadBarInfo() {
      return {
        height: this.HeadBarHeight + 'px'
      }
    }
  }
}
</script>

<style scoped>
.home-layout {
  background: #ECECEC;
}
.home-content-left-right {
  margin: 0 4%;
}
</style>