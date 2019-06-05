<template>
<a-card title="新的文章">
	<div>
		<a @click="goBack">返回上级</a>
	</div>
<a-form
	:form="form"
	style="margin-top: 16px"
>
	<a-form-item>
		<a-input
			v-model="values.title"
			placeholder="文章名称"
		>
		</a-input>
	</a-form-item>
	
	<a-form-item>
		<a-input
			v-model="values.description"
			placeholder="关键的描述信息"
		>
		</a-input>
	</a-form-item>
</a-form>
	<mavon-editor
	style="z-index: 0"
		ref="editor"
		v-model="values.content"
		>
	</mavon-editor>

	<a-button
		type="primary"
		style="width: 15%; float: right; margin-top: 16px;"
		@click="handleSubmit"
	>
		提交
	</a-button>
</a-card>
</template>

<script>

export default {
	name: "ArticleEditorPanel",
	beforeCreate () {
		this.form = this.$form.createForm(this);
	},
	created() {
		let article = this.$route.query.article
		console.log('article', article)
		if(article != null ) {
			this.values.title = article.title;
			this.values.description = article.description;
			this.values.content = article.content;
		}
	},
	data() {
		return {
			values: {
				title: "",
				description: "",
				content: ""
			}
		}
	},
	computed: {
		article() {
			return this.$route.query.article;
		},
		imgFile() {
			return this.$refs.md.img_file;
		}
	},
	methods: {
		goBack() {
			this.$router.go(-1);
		},

		postArticleRemote(article) {
			let url = this.article == null ?
				this.$path.ARTICLE_ADD :
				this.$path.ARTICLE_UPDATE;
			let params = {
				uid: this.$store.getters.user.id,
				...article
			};
			if(this.article != null ) {
				params.id = this.article.id;
			}
			this.$ajax.post(url, {
				...params
			}).then(() => {
				this.goBack();
			}) 
		},
		
		handleSubmit() {
			let article = {...(this.values)};
			article.content = this.$refs.editor.d_value;
			this.postArticleRemote(article);
		}
	}
}
</script>

<style scoped>
.article-title-preview {
	display: block;
	font-weight: bold;
	word-break: break-word;
}
.article-description-preview {
	display: block;
	font-weight: bold;

}
</style>