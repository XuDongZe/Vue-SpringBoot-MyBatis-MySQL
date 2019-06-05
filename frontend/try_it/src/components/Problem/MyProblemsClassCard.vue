<template>
	<!-- 这是单独的一张卡片 -->
<a-card 
	hoverable 
	class="single-card"
	>
	<a-icon :type="card.type" />
  <a-card-meta
    :title="card.title"
    :description="card.description">
  </a-card-meta>
</a-card>
</template>

<script>
export default {
	name: 'SingleCard',
	props: [
		'card'
	],
	data() {
		return {

		}
	}
}
</script>

<style scoped>
.single-card {
	margin: 0;
	width: 100%;
}
.single-card >>> .anticon {
  font-size: 48px;
  /*margin: 10px 45%;*/
  margin-left: 50%;
  transform: translateX(-50%);
}
.single-card >>> .ant-card-meta-title {
	text-align: center;
	margin-top: 16px;
}
.single-card >>> .ant-card-meta-description {
	text-align: center;
}
</style>
