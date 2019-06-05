<template>
<a-card 
  title="热门文章"
  style="padding: 0"
  class="hot-articles"
  >
  <a-list
    itemLayout="vertical"
    size="large"
    :dataSource="articleList"
    style="margin: 0;"
  >
    <div slot="footer" style="text-align: right">
      <a href="#">更多文章 >>></a>
    </div>
    <a-list-item slot="renderItem" slot-scope="item, index" key="item.title" >
      <!-- metas -->
      <a-list-item-meta>
        <a slot="title" href="#">{{ item.title }}</a>
        <span slot="description" >{{ item.description }}</span>
        <a-avatar slot="avatar" :src="item.avatar" />
      </a-list-item-meta>
      <div style="margin-left: 48px">{{ item.content }}</div>
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
    let url = this.$path.HotArticles
    this.$ajax.get(url, {
      params: {
        hotNums: 3
      }
    })
    .then((response) => {
      this.articleList = response.data
    })
    .catch((error) => {
      console.log(error)
    })
  },
  data () {
    return {
      articleList: []
    }
  }
}
</script>

<style scoped>
</style>
