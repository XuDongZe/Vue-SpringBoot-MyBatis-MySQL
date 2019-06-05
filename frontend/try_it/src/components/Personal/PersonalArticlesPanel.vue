<template>
<a-card 
  style="padding: 0"
  >
  <div slot="title" style="float: right">
    <router-link to="/edit/article">
      <a-button type="primary" >文章上传<a-icon type="edit" /></a-button>
    </router-link>
  </div>
  <a-list
    itemLayout="vertical"
    size="small"
    :dataSource="problemList"
    style="margin: 0;"
  >
 
    <a-list-item slot="renderItem" slot-scope="item, index" :key="item.id" >
      <!-- actions -->
      
      <!-- metas -->
      <a-list-item-meta>
        <div slot="title">
          <router-link 
            :to="'/articles/about?id=' + item.id">
            <span>{{ item.title }}</span>
          </router-link>
          <router-link :to="{path: '/edit/article', query: {article: item} }"
            style="margin-left: 16px">
            <a-tooltip title="修改文章" placement="bottom">
              <a-icon type="edit" ></a-icon>
            </a-tooltip>
          </router-link>
         
          <a style="float: right;">
            <a-tooltip title="删除文章" placement="bottom">
              <a-icon type="delete" @click="handleDelete(item.id)"></a-icon>
            </a-tooltip>
          </a>

        </div>
        <router-link slot="avatar"
          :to="'/user/id/' + item.author.id">
          <a-tooltip title="个人空间" placement="bottom">
          	<a-avatar :src="item.author.avatar" >{{item.author.username}}</a-avatar>
        	</a-tooltip>
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
    this.getListRemote();
  },
  data () {
    return {
      problemList: []
    }
  },

  methods: {
    getListRemote() {
      let url = this.$path.CustomerArticles
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

    deleteRemote(id) {
      this.$ajax.post(this.$path.ARTICLE_DELETE, {
        id
      }).then(() => {
        this.getListRemote();
      })
    },

    handleDelete(aid) {
      let vm = this;
      this.$modal.confirm({
        title: '将删除题库中所有关于该文章的信息, 确定?',
        onOk() {
          vm.deleteRemote(aid);
        },
        onCancel() {},
    })
    
    },
  }
}
</script>

<style scoped>
</style>
