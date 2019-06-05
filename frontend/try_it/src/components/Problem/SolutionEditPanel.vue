<template>
<a-card title="新的题解">
<a-form
	:form="form"
>
	<a-form-item>
		<a-input
			v-decorator="[
				'title',
				{ 
					rules: [
						{ required: true, message: '请输入题解名称!' }
					],
					initialValue: problem.title
				}
			]"
			placeholder="名称"
		>
		</a-input>

		<template v-for="(tag, index) in tags">
			<a-tooltip v-if="tag.length > 20" :key="tag" :title="tag">
				<a-tag :key="tag" :closable="true" :afterClose="() => handleClose(tag)">
					{{`${tag.slice(0, 20)}...`}}
				</a-tag>
			</a-tooltip>
			<a-tag v-else :key="tag" :closable="true" :afterClose="() => handleClose(tag)">
				{{tag}}
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
	<a-form-item>
		<EditorPanel
			ref=md
			:styleObject=editorStyle
		>
		</EditorPanel>
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
		<a-button
			type="primary"
			style="width: 23%; float: right;"
			html-type="submit"
			@click="handleSave"
		>
			保存
		</a-button>
	</a-form-item>
</a-form>
</a-card>
</template>

<script>
import EditorPanel from '@/components/EditorPanel'
export default {
	name: "SolutionEditPanel",
	components:{
		EditorPanel
	},
	beforeCreate () {
		this.form = this.$form.createForm(this);
	},
	data() {
		return {
			tags: [],
			inputVisible: false,
			inputValue: '',
			editorStyle: {
				subfield: false,
				toolbarsFlag: false,
				view: 'edit',
				placeholder: "支持MarkDown语法",
				minHeight:'80px'
			}
		}
	},
	computed: {
		problem(){
			return JSON.parse(this.$route.query.problem)
		},
		imgFile() {
			return this.$refs.md.img_file
		}
	},
	methods: {
		handleSubmit(e) {
			
		},
		handleSave(e) {

		},
		handleClose (removedTag) {
			const tags = this.tags.filter(tag => tag !== removedTag)
			this.tags = tags
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
			if (inputValue && tags.indexOf(inputValue) === -1) {
				tags = [...tags, inputValue]
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