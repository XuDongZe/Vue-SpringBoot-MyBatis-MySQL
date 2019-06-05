<template>
<a-table :dataSource="submissionList" :columns="columns" :rowKey="(record) => record.id">
	<span slot="submission_status" slot-scope="text">
		<a-tag color="#87d068"> {{ text }} </a-tag>
	</span>
	<span slot="submission_answer" slot-scope="text">
		{{ text }}
	</span>
	<span slot="submission_time" slot-scope="text">
		{{ text }}
	</span>
</a-table>
</template>

<script>
export default {
	name: 'ProblemSubmissonPanel',
	created() {
		this.$ajax
		.get(this.$path.Submissions, {
			params: {
				uid: this.$store.getters.user.id,
				pid: this.$route.query.id,
				pageNum: 1,
				pageSize: 100
			}
		})
		.then((response) => {
			// 返回一个list
			this.submissionList = response.data.list
		})
	},
	data() {
		return {
			submissionList: [],
			columns: [
				{
					title: "提交时间",
					dataIndex: "time",
					key: "time",
					scopedSlots: {
						customRender: 'submission_time',
					}
				},
				{
					title: "状态",
					dataIndex: "status",
					key: "status",
					scopedSlots: {
						customRender: 'submission_status',
					}
				},
				{
					title: "答案",
					dataIndex: "userAnswer",
					key: "userAnswer",
					scopedSlots: {
						customRender: 'submission_answer',
					}
				}
			]
		}
	}

}
</script>