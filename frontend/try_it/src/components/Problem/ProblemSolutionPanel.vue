<template>
<div>
  <a-list-item-meta>
    <div slot="title">
      <router-link :to="'/edit/solution?problem=' + problemJsonStr" class="button-right">
        <a-button type="primary" >写题解<a-icon type="edit" /></a-button>
      </router-link>
      <a-tag :color=getColorByDifficulty(problem.difficulty)>
        {{ problem.difficulty }}
      </a-tag>
    </div>
    <router-link slot="avatar" :to="'/user?id=' + problem.author.id">
      <a-avatar  src="problem.author.avatar" />
    </router-link>
    <div slot="description">
      <span>{{ solution.description }}</span>
      <span >
        发表于: {{ solution.time }}
      </span>
      <span>
        阅读: {{ solution.reads }}
      </span>
    </div>
  </a-list-item-meta>
  <div class="content-panel">
    <div class="siderbar">
      <ul class="menu">
        <li class="menu-item" @click="onSupport">
          <a-icon class="menu-icon" type="like" :theme="support ? 'filled' : 'outlined'" />
          <div>{{supportAmount}}</div>
        </li>
        <li class="menu-item" @click="onFavorite">
          <a-icon class="menu-icon" type="heart" :theme="favorite ? 'filled' : 'outlined'"/>
          <div>{{favoriteAmount}}</div>
        </li>
      </ul>
    </div>
    <a-card class="content">
      <span slot="title">{{ solution.title }}</span>
      <span slot="description">{{ solution.description }}</span>
      <div>{{ solution.content }}</div>
    </a-card>
  </div>
</div>
</template>

<script>
import {getColorByDifficulty} from '../../constant/constant.js'
export default {
	name: 'SolutionPanel',
  props: ['problem'],
  created() {
    this.$ajax.get('/api/get/solution?problemId=' + this.problem.id)
    .then((response) => {
      let solution = response.data.data
      this.solution = solution
      this.supportAmount = solution.supports
      this.favoriteAmount = solution.favorites
    })
    .catch((error) => {
      console.log(error)
    })
  },
  data () {
    return {
      solution: {},
      getColorByDifficulty,
      support: false,
      supportAmount: 0,
      favorite: false,
      favoriteAmount: 0
    }
  },
  computed: {
    problemJsonStr() {
      const problem = {id: this.problem.id, title: this.problem.title}
      return JSON.stringify(problem)
    }
  },
  methods: {
    onFavorite() {
      let favorite = this.favorite
      let favoriteAmount = this.favoriteAmount
      // 更改icon样式
      if(favorite) {
        // 在喜爱状态下点击 -> 取消赞
        favoriteAmount = favoriteAmount === 0 ? 0 : favoriteAmount - 1
      }else {
        favoriteAmount ++
      }
      favorite = !favorite
      // 远端修改数据表
      // success->
      this.favorite = favorite
      this.favoriteAmount = favoriteAmount
    },
    onSupport() {
      let support = this.support
      let supportAmount = this.supportAmount
      // 更改icon样式
      if(support) {
        // 在喜爱状态下点击 -> 取消赞
        supportAmount = supportAmount === 0 ? 0 : supportAmount - 1  
      } else {
        supportAmount ++
      }
      support = !support
      // 远端修改数据表
      // success->
      this.support = support
      this.supportAmount = supportAmount
    }
  }
}
</script>

<style scoped>
.content-panel {
  margin-top: 16px;
}
.siderbar {
  width: 7%;
  float: left;
}
.content {
  width: 93%;
  float: right;
}
.button-right {
  float: right;
}
.menu {
  padding: 0;
  list-style-type: none;
}
.menu-item {
  text-align: center;
  transform: translateX(-33%);
  margin-bottom: 16px;
  font-size: 1.5em;
}
.menu-icon {
}
.menu-icon:hover {
  cursor: pointer;
}
</style>
