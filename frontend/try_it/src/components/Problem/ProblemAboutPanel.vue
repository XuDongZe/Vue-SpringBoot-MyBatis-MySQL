<template>
<a-card
  style="width:100%"
  :tabList="tabList"
  :activeTabKey="key"
  @tabChange="key => onTabChange(key, 'key')"
>
  <div class="detail-content">
    <router-view @changePid="changePid"></router-view>
  </div>
</a-card>
</template>

<script>
const tabList = [
  {
    key: 'details',
    tab: '题目'
  }, 
  {
    key: 'submissions',
    tab: '提交记录',
  },
  {
  	key: 'discussions',
  	tab: '讨论'
  },
  {
  	key: 'sulotions',
  	tab: '题解'
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
    changePid(pid) {
      this.id = pid;
    },
    onTabChange (key, type) {
      this[type] = key;
      switch(key) {
        case 'details':
          this.$router.push({
            path: '/problems/about/details',
            query: {
              id: this.id
            }
          });
          break;
        case 'submissions':
          this.$router.push({
            path: '/problems/about/submissions',
            query: {
              id: this.id
            }
          });
          break;
        case 'discussions':
          this.$router.push({
            path: '/problems/about/discussions',
            query: {
              id: this.id
            }
          });
        break;
        case 'sulotions':
          this.$router.push({
            path: '/problems/about/sulotions',
            query: {
              id: this.id
            }
          } );
        break;
      }
    }
  }
}
</script>

<style scoped>

</style>