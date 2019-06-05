<template>
<div class="incentive-container">
	<div class="incentive-line" v-for="line in IncentiveLines" :key="line.id">
		<a-tooltip
			placement="bottom"
			v-for="item in line.list"
			:key="item.type"
			:title="item.tips"
			class="incentiveActions">
			<div class="incentive-cube">
		  	<a-icon :type="item.type" class="incentive-icon"/>
		  	<span class="incentive-data">{{ item.data }}</span>
		  </div>
		</a-tooltip>
	</div>
</div>
</template>

<script>
const LINENUM = 3
export default {
	name: "UserMetaPanel",
	data() {
		return {
			user: this.$store.getters.user
		}
	},
	computed: {
		IncentiveLines() {
			return [
				{
					id: 1,
					list: [
						{
							type: "trophy",
							tips: "成就值",
							data: this.user.achievement
						},
						{
							type: "ordered-list",
							tips: "排名",
							data: this.user.rankOrder
						},
						{
							type: "rise",
							tips: "本周新增",
							data: this.user.weekRise
						}
					]
				},
				{
					id: 2,
					list: [ 
						{
							type: "file-done",
							tips: "已完成题目",
							data: this.user.problems
						},
						{
							type: "hdd",
							tips: "发表文章数",
							data: this.user.articles
						},
						{
							type: "like",
							tips: "获得的赞",
							data: this.user.supports
						}
					]
				}
			]
		}
	}
}
</script>

<style scoped>
.incentive-container {
	margin-top: 8px;
	padding: 0;
}

.incentive-cube {
	display: inline-block;
	margin-bottom: 8px;
	width: 33.3%;
}
.incentive-icon {
	margin-right: 8px;
}

.incentiveActions:hover {
	cursor: pointer;
}
</style>