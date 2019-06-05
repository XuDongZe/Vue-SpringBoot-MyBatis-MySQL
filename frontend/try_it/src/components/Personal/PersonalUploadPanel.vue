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
    key: 'problem',
    tab: '我的题目'
  }, 
  {
    key: 'article',
    tab: '我的文章',
  }
]

export default {
  name: "PersonalUpLoadPanel",

  data () {
    return {
    	tabList: tabList,
    	key: 'problem',
    }
  },
  methods: {
    onTabChange (key, type) {
      this[type] = key;
      switch(key) {
        case 'problem':
          this.$router.push('/personal/upload/problems');
          break;
        case 'article':
          this.$router.push('/personal/upload/articles');
          break;
      }
    }
  }
}
</script>

<style scoped>

</style>