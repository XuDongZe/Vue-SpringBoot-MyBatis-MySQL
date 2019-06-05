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
 
    <a-list-item slot="renderItem" slot-scope="item, index" :key="item.problem.id" >
      <!-- actions -->
      
      <!-- metas -->
      <a-list-item-meta>
        <div slot="title">
          <router-link
            :to="'/problems/about?id=' + item.problem.id">
            <span>{{ item.problem.title }}</span>
          </router-link>

          <a style="margin-left: 8px; float: right" >
            <a-tooltip title="取消收藏" placement="bottom">
              <a-icon type="delete" @click="handleDelete(item.problem.id)"></a-icon>
            </a-tooltip>
          </a>
        </div>
        
        <router-link slot="description"
          v-for="tag in item.problem.tags"
          :key="tag.id"
          :to="'/problems/tag/' + tag.id">
          <a-tag color="#87d068">{{ tag.name }}</a-tag>
        </router-link>
        <router-link slot="avatar"
          :to="'/user/id/' + item.problem.author.id">
          <a-tooltip title="个人空间" placement="bottom">
          	<a-avatar :src="item.problem.author.avatar" >{{item.problem.author.username}}</a-avatar>
        	</a-tooltip>
        </router-link>
      </a-list-item-meta>
      <div style="margin-left: 48px">{{ item.problem.description }}</div>
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
      let url = this.$path.FavoriteProblems
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

    handleDelete(id) {
      this.$ajax.post(this.$path.FAVORITE_PROBLEM_DELETE, {
        uid: this.$store.getters.user.id,
        pid: id
      }).then(() => {
        this.getListRemote();
      })
    }
  }
}
</script>

<style scoped>
</style>
