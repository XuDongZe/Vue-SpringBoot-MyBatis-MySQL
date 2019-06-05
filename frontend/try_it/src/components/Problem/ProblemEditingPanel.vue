<template>
<a-card title="新的题目">
<a-form
	:form="form"
>
	<div>
		<a @click="goBack">返回上级</a>
	</div>
	<a-form-item
	v-bind="formItemLayout"
		label="题目名称">
		<a-input
			v-decorator="[
				'title',
				{ 
					rules: [
						{ required: true, message: '请输入题目名称!' }
					],
					initialValue: problem == null ? '' : problem.title
				}
			]"
			placeholder="题目名称"
		>
		</a-input>

		<template v-for="(tag, index) in tags">
			<a-tooltip v-if="tag.name.length > 20" :key="tag.name" :title="tag.name">
				<a-tag :key="tag.name" :closable="true" :afterClose="() => handleClose(tag)">
					{{`${tag.name.slice(0, 20)}...`}}
				</a-tag>
			</a-tooltip>
			<a-tag v-else :key="tag.name" :closable="true" :afterClose="() => handleClose(tag)">
				{{tag.name}}
			</a-tag>
		</template>
		<a-input
			v-if="inputVisible"
			ref="input"
			type="text"
			size="small"
			:style="{ width: '78px' }"
			:value="inputValue"
			@change="handleInputChange"
			@blur="handleInputConfirm"
			@keyup.enter="handleInputConfirm"
		/>
		<a-tag v-else @click="showInput" style="background: #fff; borderStyle: dashed;">
			<a-icon type="plus" /> New Tag
		</a-tag>
	</a-form-item>

	<a-form-item
	v-bind="formItemLayout"
		label="题目描述">
		<a-textarea
			v-decorator="[
				'description',
				{ 
					rules: [
						{ required: true, message: '请输入题干!' }
					],
					initialValue: problem == null ? '' : problem.description
				}
			]"
			placeholder="题干"
		>
		</a-textarea>
	</a-form-item>
		
	<a-form-item
	v-bind="formItemLayout"
		label="题目选项">
		<a-textarea
			rows="1"
			v-decorator="[
				'optionA',
				{ 
					rules: [
						{ required: true, message: '请输入选项A!' }
					],
					initialValue: problem == null ? '' : problem.optionA
				}
			]"
			placeholder="选项A"
		>
		</a-textarea>
		<a-textarea
			rows="1"
			v-decorator="[
				'optionB',
				{ 
					rules: [
						{ required: true, message: '请输入选项B!' }
					],
					initialValue: problem == null ? '' : problem.optionB
				}
			]"
			placeholder="选项B"
		>
		</a-textarea>
		<a-textarea rows="1"
			v-decorator="[
				'optionC',
				{ 
					rules: [
						{ required: true, message: '请输入选项C!' }
					],
					initialValue: problem == null ? '' : problem.optionC
				}
			]"
			placeholder="选项C"
		>
		</a-textarea>
		<a-textarea rows="1"
			v-decorator="[
				'optionD',
				{ 
					rules: [
						{ required: true, message: '请输入选项D!' }
					],
					initialValue: problem == null ? '' : problem.optionD
				}
			]"
			placeholder="选项D"
		>
		</a-textarea>
	</a-form-item>

	<a-form-item
		v-bind="formItemLayout"
		label="正确答案">
		<a-select
			v-decorator="[
				'answer',
				{ 
					rules: [
						{ required: true, message: '请输入题目答案!' }
					],
					initialValue: problem == null ? '' : problem.answer
				}
			]"
			placeholder="答案"
			>
			<a-select-option value="A">A</a-select-option>
			<a-select-option value="B">B</a-select-option>
			<a-select-option value="C">C</a-select-option>
			<a-select-option value="D">D</a-select-option>
		</a-select>
	</a-form-item>

	<a-form-item>
		<a-button
			type="primary"
			style="width: 23%; float: right; margin-left: 3%;"
			html-type="submit"
			@click="handleSubmit"
		>
			提交
		</a-button>
	</a-form-item>
</a-form>
</a-card>
</template>

<script>
export default {
	name: "ProblemEditorPanel",
	beforeCreate () {
		this.form = this.$form.createForm(this);
	},
	created() {
		this.tags = this.problem == null ? [] : this.problem.tags;
	},
	data() {
		return {
			formItemLayout: {
				labelCol: { span: 6 },
				wrapperCol: { span: 14 }
			},
			tags: [],
			inputVisible: false,
			inputValue: ''
		}
	},
	computed: {
		problem() {
			return this.$route.query.problem;
		}
	},
	methods: {
		goBack() {
			this.$router.go(-1);
		},
		handleSubmit(e) {
			 e.preventDefault();
			this.form.validateFields((err, values) => {
				if (!err) {
					if(this.tags != []) {
						let problem = {
							uid: this.$store.getters.user.id,
							tags:JSON.parse(JSON.stringify(this.tags)),
							...values
						};

						let url = this.$path.PROBLEM_ADD;
						if(this.problem != null) {
							//for update
							url = this.$path.PROBLEM_UPDATE;
							problem = {...problem, id: this.problem.id};
						}

						this.$ajax.post(url, problem)
						.then((response) => {
							if(response.status == 1) {
								this.goBack();
							}
						})
					}
				}
			});
		},


		// for tags input
		handleClose(tag) {
			const tags = this.tags;
			let idx = tags.indexOf(tag);
			if(idx > -1) {
				tags.splice(idx, 1);
			}
			Object.assign(this, {
				tags,
				inputVisible: false,
				inputValue: '',
			})
		},
		showInput () {
			this.inputVisible = true
			this.$nextTick(function () {
				this.$refs.input.focus()
			})
		},
		handleInputChange (e) {
			this.inputValue = e.target.value
		},
		handleInputConfirm () {
			const inputValue = this.inputValue
			let tags = this.tags
			if (inputValue ) {
				let res = true;
				for(let item of tags) {
					if(item.name == inputValue) {
						this.$message.warn("相同的tag");
						res = false;
						break;
					}
				}
				if(res ) {
					// 这是该问题的一个新的name: 或者是新生成的, 或者是相同id的tag的name改的
					let newTag = {};
					newTag.name = inputValue;
					if(this.problem != null) {
						newTag.pid = this.problem.id;
					}
					tags = [...tags, newTag]
				}
			}
			
			Object.assign(this, {
				tags,
				inputVisible: false,
				inputValue: '',
			})
		}
	}
}
</script>

<style scoped>

</style>