<template>
<a-layout-content :style="{ marginTop: HeadBarInfo.height }">
	<div class="home-content">
		<div class="content-left">
			<HomeUserInfoPanel v-if="IsLoginState" class="content-left-panel" >
			</HomeUserInfoPanel>
			<HomeRewardPanel class="content-left-panel"></HomeRewardPanel >
		</div>
		<div class="content-right">
			<router-view v-if="IsLoginState" class="content-right-panel" name="MyProblemsPanel">
			</router-view>
			<router-view class="content-right-panel" name="HotProblemsPanel"></router-view>
			<router-view class="content-right-panel" name="HotArticlesPanel"></router-view>
			<router-view class="content-right-panel" style="min-height: 515px;"></router-view>
		</div>
		<div class="global-div">
			<LoginModal></LoginModal>
			<Loading></Loading>
		</div>
	</div>
</a-layout-content>
</template>

<script>
import HomeUserInfoPanel from '@/components/Personal/HomeUserInfoPanel'
import HomeRewardPanel from '@/components/HomeRewardPanel'
import LoginModal from '@/components/Login/LoginModal'
import Loading from '@/components/Loading/Loading'
export default {
	props: [
		'HeadBarInfo'
	],
	components: {
		HomeUserInfoPanel, HomeRewardPanel,
		LoginModal, Loading
	},
	data() {
		return {
			
		}
	},
	computed: {
		IsLoginState() {
      return this.$store.getters.isLogin;
		}
	}
}
</script>

<style scoped>
.home-content {
	/*控制首页的整体content布局*/
	margin: 0;
	width: 100%;
	padding: 16px;
}
.content-left {
	width: 33%;
	float: left;
	border: 1px solid #ccc;
}
.content-right {
	width: 66%;
	float: left;
	margin-left: 8px;
	border: 1px solid #ccc;
}
.content-left > .content-left-panel {
	margin-bottom: 16px;
}
.content-right > .content-right-panel {
	margin-bottom: 16px;
}
@media only screen and (max-width: 960px){
	/*.content-left, .content-right {
		width: 100%;
		float: none;
	}
	.content-right {
		margin-bottom: 16px;
		margin-left: 0;
	}
	.content-left {
	}*/
	.content-right {
		float: none;
		width: 100%;
	}
	.content-left {
		display: none;
	}
}
</style>