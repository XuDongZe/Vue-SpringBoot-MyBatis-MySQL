<template >
<a-layout-content class="user-info-panel">
	<UserMetaPanel
		:UserMeta="user.UserMeta"
		:IncentiveMeta="user.IncentiveMeta">
	</UserMetaPanel>
	<SkillPanel
		:panelInfo="{title: '我的技能'}"
		:skill="user.skill">
	</SkillPanel>
</a-layout-content>
</template>

<script>
import UserMetaPanel from '@/components/Personal/UserMetaPanel'
// import IncentivePanel from '@/components/IncentivePanel'
import SkillPanel from '@/components/Personal/SkillPanel'
export default {
	name: "UserInfoPanel",
	components: {
		UserMetaPanel, SkillPanel
	},
	data() {
		return {
			user: this.$store.getters.user
		}
	}
}
</script>

<style scoped>

</style>