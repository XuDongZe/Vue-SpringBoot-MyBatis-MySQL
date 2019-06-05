<template>
<a-card title="我的题目">
	<div>
	  <a-card-grid 
	  	class="response-grid"
			v-for="item in cardLists" 
			:key="item.id">
			<MyProblemsClassCard :card="item"></MyProblemsClassCard>
	  </a-card-grid>
  </div>
</a-card>
</template>

<script>
// 这是固定好的
const cardLists = [
	{
		id: 1, type: "book", title: "专项练习", 
		description: "这是专项练习",
		url:"#"
	},
	{
		id: 2, type: "exception", title: "错题练习", 
		description: "这是错题练习",
		url:"#"
	},
	{
		id: 3, type: "calendar", title: "刷题历史", 
		description: "这是历史练习",
		url:"#",
	},
	{
		id: 4, type: "cloud-upload", title: "新题上传", 
		description: "这是新题上传",
		url:"#"
	}
]
import MyProblemsClassCard from '@/components/Problem/MyProblemsClassCard'
export default {
	name: "MyProblemPanel",
	props: [
		'panelInfo'
	],
	components: {
		MyProblemsClassCard
	},
	data() {
		return {
			cardLists: cardLists
		}
	}
}
</script>

<style scoped>
.response-grid {
	padding: 0;
	width: 24.9%;
}
@media only screen and (min-width:600px) and (max-width:800px){
	.response-grid {
		margin: 0 6px 6px 1.5%;
		width: 46.5%;
	}
}
@media only screen and (min-width:320px) and (max-width:600px){
	.response-grid {
		margin: 6px;
		width: 95%;
	}
}
</style>