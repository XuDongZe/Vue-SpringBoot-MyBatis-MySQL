<template>
<a-layout-header class="headbar" :style="HeadBarInfo.height" >
  <a-menu
    class="header-menu"
    theme="dark"
    mode="horizontal"
    :defaultSelectedKeys="[1]"
    :style="{ lineHeight: HeadBarInfo.height }"
  >  
    <a-menu-item 
      @click="openSideBar"
      v-if="IsLoginState" >
      <a-avatar :src="user.avatar" >{{ user.username }}</a-avatar>
    </a-menu-item>
   <!--  <a-menu-item class="spance-item" style="margin-left: 3%">
      </a-menu-item> -->
    <a-menu-item 
      v-for="item in menuList.menuLeft"
      :key="item.id">
      <router-link :to="item.url"><span>{{ item.title }}</span></router-link>
    </a-menu-item>
    <!-- 拓展的插槽 -->
    <a-menu-item
      v-for="item in menuList.menuRight"
      :key="item.id"> 
      <router-link :to="item.url"><span>{{ item.title }}</span></router-link>
    </a-menu-item>
    <!-- 登录/注册按钮 绑定v-if 所以不能再v-for中 -->
    <a-menu-item 
      class="menu-item-right"
      @click="openLoginModal"
      v-if="!IsLoginState">
      <span>登录/注册</span>
    </a-menu-item>
  </a-menu>
  <HomeSideBar :SideBarInfo="sidebar" @close-side-bar="closeSideBar"></HomeSideBar>
</a-layout-header>
</template>

<script>
const _menuLeft = [ //固定不变了
  { id: 1, title: "首页", url: "/" },
  { id: 2, title: "题库", url: "/problems"},
  // { id: 3, title: "题解", url: "/solutions" },
  { id: 3, title: "文章", url: "/articles"},
  { id: 4, title: "活动", url: "/activities" },
  { id: 5, title: "排行榜", url: "/ranks" },
]
const _menuRight = []

import HomeSideBar from '@/components/HomeSideBar'
export default {
  props: [
    'HeadBarInfo' // 父节点传递高度信息, 来宏观调控fixed的顶部导航栏
  ],
  components: {
    HomeSideBar
  },
  data() {
    return {
      menuList: {
        menuLeft: _menuLeft, menuRight: _menuRight
      },
      sidebar: {
        visible: false,
        placement: "left"
      }
    }
  },
  computed: {
    IsLoginState() {
      return this.$store.getters.isLogin
    },
    user() {
      return this.$store.getters.user
    }
  },
  methods: {
    openSideBar() {
      this.sidebar.visible = true;
    },
    closeSideBar() {
      this.sidebar.visible = false;
    },
    openLoginModal() {
      this.$store.commit('openLoginModal')
    }
  }
}
</script>

<style scoped >
/*这里不知道怎么选定最后一个节点, 编译后为倒数第二个节点????*/
.menu-item-right {
  float: right;
}
.headbar {
  position: fixed;
  width: 100%;
  opacity: 0.9;
  z-index: 1; /* z-index悬浮 +opacity透明效果*/
}
.header-menu {

}
.ant-menu.ant-menu-dark .ant-menu-item-selected {
  background-color: #353b48;
}
</style>