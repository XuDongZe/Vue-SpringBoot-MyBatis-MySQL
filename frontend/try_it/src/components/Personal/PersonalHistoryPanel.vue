<template>
<a-card
  style="width:100%"
  :tabList="tabList"
  :activeTabKey="key"
  @tabChange="key => onTabChange(key, 'key')"
>
<div slot="title" style="text-align: right">
  我的上传
</div>
  <div class="detail-content">
    <keep-alive>
		  <router-view></router-view>
    </keep-alive>
  </div>
</a-card>
</template>

<script>


const tabList = [
  {
    key: 'errors',
    tab: '我的错题'
  }, 
  {
    key: 'activities',
    tab: '我的活动',
  }
]

export default {
  name: "PersonalHistoryPanel",

  data () {
    return {
    	tabList: tabList,
    	key: 'errors',
    }
  },
  methods: {
    onTabChange (key, type) {
      this[type] = key;
      switch(key) {
        case 'errors':
          this.$router.push('/personal/history/errors');
          break;
        case 'activities':
          this.$router.push('/personal/history/activities');
          break;
      }
    }
  }
}
</script>

<style scoped>

</style>