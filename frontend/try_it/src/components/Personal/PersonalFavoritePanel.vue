<template>
<a-card
  style="width:100%"
  :tabList="tabList"
  :activeTabKey="key"
  @tabChange="key => onTabChange(key, 'key')"
>
<div slot="title" style="text-align: right">
  我的收藏
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
    tab: '收藏的题目'
  }, 
  {
    key: 'article',
    tab: '收藏的文章',
  }
]

export default {
  name: "PersonalFavoritePanel",
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
          this.$router.push('/personal/favorite/problems');
          break;
        case 'article':
          this.$router.push('/personal/favorite/articles');
          break;
      }
    }
  }
}
</script>

<style scoped>

</style>