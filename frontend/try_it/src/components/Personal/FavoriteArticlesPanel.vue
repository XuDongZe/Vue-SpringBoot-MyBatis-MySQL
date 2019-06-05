<template>
<a-card 
  style="padding: 0"
  >
  <a-list
    itemLayout="vertical"
    size="small"
    :dataSource="problemList"
    style="margin: 0;"
  >
 
    <a-list-item slot="renderItem" slot-scope="item, index" :key="item.article.id" >
      <!-- actions -->
      
      <!-- metas -->
      <a-list-item-meta>
        <div slot="title">
          <router-link
            :to="'/articles/about?id=' + item.article.id">
            <span>{{ item.article.title }}</span>
          </router-link>
          <a style="margin-left: 8px; float: right">
            <a-tooltip title="取消收藏" placement="bottom">
              <a-icon type="delete" @click="handleDelete(item.article.id)"></a-icon>
            </a-tooltip>
          </a>
        </div>
        
        <router-link slot="avatar"
          :to="'/user/id/' + item.article.author.id">
          <a-tooltip title="个人空间" placement="bottom">
          	<a-avatar :src="item.article.author.avatar" >{{item.article.author.username}}</a-avatar>
        	</a-tooltip>
        </router-link>

      </a-list-item-meta>
      <div style="margin-left: 48px">{{ item.article.description }}</div>
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
    this.getListRemote();
  },
  data () {
    return {
      problemList: []
    }
  },
  methods: { 
    getListRemote() {
      let url = this.$path.FavoriteArticles
      this.$ajax.get(url, {
        params: {
          uid: this.$store.getters.user.id,
          pageNum: 1,
          pageSize: 1000
        }
      })
      .then((response) => {
        this.problemList = response.data.list;
      })
    },

    handleDelete(aid) {
      this.$ajax.post(this.$path.FAVORITE_ARTICLE_DELETE, {
        uid: this.$store.getters.user.id,
        aid: aid
      }).then(() => {
        this.getListRemote();
      })
    }
  }
}
</script>

<style scoped>
</style>
