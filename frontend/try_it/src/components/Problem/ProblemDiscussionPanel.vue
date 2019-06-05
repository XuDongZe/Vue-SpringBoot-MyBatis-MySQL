<template>
  <a-list
    itemLayout="vertical"
    size="large"
    :dataSource="RewardList"
  >
    <router-link slot="footer" to="/rewards">
      <div style="text-align: right">更多题目 >>></div>
    </router-link>
    <a-list-item slot="renderItem" slot-scope="item, index" key="item.title" >
      <a-list-item-meta>
        <div slot="title">
          <router-link :to="'/problems/search?id=' + item.problem.id">
            {{ item.problem.title }}
          </router-link>
          <span class="reward-title-tags">
            <a-tag :color="getColorByDifficulty(item.problem.difficulty)" slot="title">
              {{ item.problem.difficulty }}
            </a-tag>
            <a-tag color="yellow">{{ item.rewards }}</a-tag>
          </span>
        </div>
        <span slot="description">{{ item.description }}</span>
        <a-avatar slot="avatar" :src="item.user.avatar" />
      </a-list-item-meta>
      <div style="margin-left: 48px">{{ item.problem.description }}</div>
    </a-list-item>
  </a-list>
</template>

<script>
import {getColorByDifficulty} from '../../constant/constant.js'
export default {
	name: 'RewardPanel',
  created() {
    this.$ajax.get('/api/get/rewards')
    .then((response) => {
      this.RewardList = response.data.data
    })
  },
  data () {
    return {
      RewardList: [],
      getColorByDifficulty,
    }
  }
}
</script>

<style scoped>
.reward-title-tags {
  float: right;
}
</style>
