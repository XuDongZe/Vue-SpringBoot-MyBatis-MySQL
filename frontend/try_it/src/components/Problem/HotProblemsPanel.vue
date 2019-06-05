<template>
<a-card 
  title="热点题目"
  style="padding: 0"
  >
  <a-list
    itemLayout="vertical"
    size="large"
    :dataSource="problemList"
    style="margin: 0;"
  >
    <div slot="footer" style="text-align: right">
      <a href="#">更多题目 >>></a>
    </div>
    <a-list-item slot="renderItem" slot-scope="item, index" :key="item.title" >
      <!-- actions -->
      
      <!-- metas -->
      <a-list-item-meta>
        <router-link slot="title"
          :to="'/problems/search?id=' + item.id">
          <span>{{ item.title }}</span>
        </router-link>
        <router-link slot="description"
          v-for="tagName in item.tags"
          :key="tagName.id"
          :to="'/problems/tag/' + tagName.id">
          <a-tag color="#87d068">{{ tagName.name }}</a-tag>
        </router-link>
        <router-link slot="avatar"
          :to="'/user/id/' + item.author.id">
          <a-avatar :src="item.author.avatar" />
        </router-link>
      </a-list-item-meta>
      <div style="margin-left: 48px">{{ item.description }}</div>
    </a-list-item>
  </a-list>
</a-card>
</template>

<script>

export default {
  props: [
    'panelInfo'
  ],
  created() {
    let url = this.$path.HotProblems
    this.$ajax.get(url, {
      params: {
        hotNums: 3
      }
    })
    .then((response) => {
      this.problemList = response.data;
    })
  },
  data () {
    return {
      problemList: []
    }
  },
  methods: {
    jsonProblem(ob) {
      console.log('item:', ob)
      return JSON.stringify(ob)
    }
  }
}
</script>

<style scoped>
</style>
