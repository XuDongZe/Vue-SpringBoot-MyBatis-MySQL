<template>
<a-card >
	<span slot="title">{{ panelInfo.title }}</span>
		<a-select 
			:defaultValue="skillType"
			@change="handerChange"
			slot="title"
			class="skill-selector"
		>
		  <a-select-option 
				v-for="meta in skillList"
				:key="meta.type"
				:value="meta.type"
				>
		  	{{ meta.type }}
		 </a-select-option>
		</a-select>
	<div class=".radar-container">
		<ve-radar :data="chartData.data" judge-width height="250px"></ve-radar>
	</div>
</a-card>
</template>

<script>


export default {
	name: "SkillPanel",
	props: [
		'panelInfo',
		'skill'
	],
	data() {
		return {
			skillType: "C++工程师",// from selector
			skillList: [
				{
					data: {
						rows: [{
							"cpp": this.skill.cpp,
							"数据结构": this.skill.dataStruct,
							"数据库": this.skill.database,
							"操作系统": this.skill.os,
							"网络基础": this.skill.network
						}]
					},
					type: "C++工程师"
				},
				{
					data: {
						rows: [{
							"java": this.skill.java,
							"HTML/CSS": this.skill.htmlCss,
							"数据库": this.skill.database,
							"JS": this.skill.js,
							"网络基础": this.skill.network
						}]
					},
					type: "网络工程师"
				}
			]
		}
	},
	computed: {
		chartData() {
			return this.skillList.find( item => {
				return item.type === this.skillType
			});
		}
	},
	methods: {
		handerChange(select) {
			this.skillType = select;
		}
	}
}
</script>

<style scoped>
.radar-container {
	overflow: auto;
}
.skill-selector {
	/* 因为selector的左面是一个类似于label的东西, 而这个东西是title定位, 所以可以使用这样布局: */
	margin-left: 16px;
}
</style>