<template>
<a-card>
	<div class="user-info-container" slot="title" >
		<a-tooltip title="个人空间" placement="right" slot="avatar">
			<div class="user-img-container" >
				<router-link :to="'/user?id=' + user.id">
					<a-avatar > {{ user.username }} </a-avatar>
				</router-link>
			</div>
			</a-tooltip>
		<div class="user-incentive-container">
			<IncentivePanel> </IncentivePanel>
		</div>
	</div>
</a-card>
</template>

<script>
import IncentivePanel from '@/components/Personal/IncentivePanel'
export default {
	name: "UserMetaPanel",
	components: {
		IncentivePanel
	},
	data() {
		return {
			user: this.$store.getters.user
		}
	}
}
</script>

<style scoped>
.user-info-container {
	text-align: center;
}
.user-meta-container {
	margin: 0.5em 0;
	font-size: 16px;
	font-weight: bold;
}
.user-incentive-container {
	margin-top: 8px;
}
.user-img-container {
	width: 80px;
	padding: 6px;
	margin-left: 50%;
	transform: translateX(-50%);
}
.user-img-container:hover {
	opacity: 0.85;
}
.user-meta-img {
	width: 100%;
}
</style>