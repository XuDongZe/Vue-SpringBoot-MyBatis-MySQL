<template >
<div v-if="IsLoginState">
  <a-drawer
    title="我的空间"
    :placement="SideBarInfo.placement"
    :closable="true"
    @close="closeDrawer"
    :visible="SideBarInfo.visible"
  >
    <a-menu :defaultSelectedKeys="[1]">
      <a-menu-item v-for="item in menuItems" :key="item.id">
        <router-link :to="item.url">
          <a-icon :type="item.type" />
          <span>{{ item.title }}</span>
        </router-link>
      </a-menu-item>
      <a-menu-item style="background-color: inherit" @click="handleLogout">
        <a-icon type="logout" />
        <span>退出登录</span>
      </a-menu-item>
    </a-menu>
  </a-drawer>
</div>
</template>

<script>
const menuItems = [
  {id: 1, type: "home", title: "主页", url: "/"},
  {id: 2, type: "user", title: "个人信息", url: "/personal/info"},
  {is: 3, type: "hdd", title: "我的上传", url: "/personal/upload"},
  {id: 4, type: "hdd", title: "我的收藏", url: "/personal/favorite"},
  {id: 5, type: "calendar", title: "刷题历史", url: "/personal/history"},
  {id: 6, type: "team", title: "我的团队", url: "/personal/team"} //>我的关注
]

export default {
  props:[
    'SideBarInfo' // 从父节点传过来的info
  ],
	data() {
    return {
    	// type for icon-type
      menuItems: menuItems,
      // visible button->状态
      visible: false,
      buttonInfo: {
        action: this.showDrawer,
        text: "打开"
      }
    }
  },
  computed: {
    IsLoginState() {
      return this.$store.state.user !== null;
    }
  },
  methods: {
    showDrawer() {
      this.visible = true;
      this.buttonInfo.action = this.closeDrawer;
      this.buttonInfo.text = "关闭";
    },
    closeDrawer() {
      this.$emit('close-side-bar');
    },
    handleLogout() {
      this.showConfirm();
    },
    showConfirm () {
      let vm = this;
      vm.$modal.confirm({
        title: '确定要退出吗?',
        onOk() {
          vm.$store.commit('logoutMutation')
          vm.$router.push('/')
        },
        onCancel() {}
      });
    }
  }
}
</script>

<style scoped>
</style>
