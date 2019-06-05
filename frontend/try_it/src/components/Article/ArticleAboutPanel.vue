<template>
<a-card
  style="width:100%"
  :tabList="tabList"
  :activeTabKey="key"
  @tabChange="key => onTabChange(key, 'key')"
>
  <div class="detail-content">
    <keep-alive>
      <router-view @changeAid="changeAid"></router-view>
    </keep-alive>
  </div>
</a-card>
</template>

<script>
const tabList = [
  {
    key: 'details',
    tab: '文章'
  }, 
  {
  	key: 'discussions',
  	tab: '讨论'
  }
]

export default {
  data () {
    return {
    	tabList: tabList,
    	key: 'details',
    	value: '',
      id: "",
    }
  },
  created() {
    this.id = this.$route.query.id;
  },
  
  methods: {
    changeAid(aid) {
      this.id = aid;
    },
    onTabChange (key, type) {
      this[type] = key;
      switch(key) {
        case 'details':
          this.$router.push({
            path: '/articles/about/details',
            query: {
              id: this.id
            }
          });
          break;
        case 'discussions':
          this.$router.push({
            path: '/articles/about/discussions',
            query: {
              id: this.id
            }
          });
        break;
      }
    }
  }
}
</script>

<style scoped>

</style>