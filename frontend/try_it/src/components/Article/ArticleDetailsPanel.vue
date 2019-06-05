<template>
<div>
	<a-card class="content-container">
		<template class="detail-title" slot="title">
			{{ article.id + '.' + article.title }}
		</template>
		<a-card-meta>
		  <router-link slot="avatar"
		    :to="'/personal?id=' + article.author.id">
		    <a-tooltip :title="article.author.username" placement="bottom">
		    	<a-avatar :src="article.author.avatar" >{{ article.author.username }}</a-avatar>
		  	</a-tooltip>
		  </router-link>
		  <div slot="description">
		  	{{ article.description }}
		  </div>
		</a-card-meta>
	  
		<mavon-editor
			class= "article-content"
			style="z-index: 0"
			ref="editor"
			v-model="article.content"
			:toolbarsFlag="false"
			:subfield="false"
			defaultOpen="preview"
			>
		</mavon-editor>	

	  <div style="float: right;">
	  	<div >
	  		发表于: <span style="margin-left: 16px;">{{ article.time }}</span>
	  	</div>
	  	<div >
	  		阅读数: <span style="margin-left: 16px;">{{ article.readers }}</span>
	  	</div>
	  </div>
		<template class="ant-card-actions" slot="actions">
			<span>
				<a-tooltip :title="favorite === true ? '取消收藏' : '加入收藏'"
				placement="bottom" >
					<a-icon 
						class="action-button" type="heart"
						style="fontSize: 1.8em"
						:theme="favorite === true ? 'filled' : 'outlined'"
						@click="onFavorite" />
						<span>{{ favoriteAmount }} </span>
				</a-tooltip>
			</span>
	    <a-icon 
	    	@click="onPrev(article.id)"
	    	class="action-button" type="left-circle" style="fontSize: 1.8em" />
	    <a-icon @click="onNext(article.id)" class="action-button" type="right-circle" style="fontSize: 1.8em" />
	 	</template>
	</a-card>
	<CommentsPanel 
	@getComments="getComments"
	class="comments" 
	:objects="{comments: comments, uid: article.author.id, uname: article.author.username, linkid: article.id, type:'article'}"></CommentsPanel>
</div>
</template>

<script>
import CommentsPanel from '@/components/Comment/CommentsPanel'

export default {
	name: "ArticleDetailsPanel",
	components: {
		CommentsPanel
	},

	created() {
		this.getArticleByIdRemote(this.$route.query.id);
	},
	data() {
		return {
			answer: "",
			article: {},
			favorite: false,
			facoriteAmount: 0,
			supportAmount: 0,
			readerAmount: 0,
			comments: {}
		}
	},
	computed: {
		id() {
			return this.$route.query.id;
		}
	},
	methods: {
//面板同步
		postPidToParentPanel(aid) {
			this.$emit('changeAid', aid);
		},
// 后端请求
// 使用新文章对界面进行刷新, 使用自定义的前进后退的功能键时
		refreshData(article) {
			this.article = article;
			this.favoriteAmount = article.favorites;
			this.supportAmount = article.supports;
			this.readerAmount = article.readers;
			this.checkIsFavoriteRemote();
			this.getComments();
			this.addOneReader();
		},

		addOneReader() {
			this.$ajax.post(this.$path.ARTICLE_UPDATE, {
				id: this.article.id,
				readers: this.article.readers + 1
			})
		},

		getComments() {
			this.$ajax.get(this.$path.CommentArticles, {
		    params: {
		      aid: this.article.id,
		      pageNum: 1,
		      pageSize: 1000
		    }
		  })
			.then((response) => {
				this.comments = response.data.list
			})
		},
		getArticleByIdRemote(id) {
			this.$ajax.get(this.$path.Article, {
				params: {
					id: id
				}
			}).then((response) => {
				this.refreshData(response.data);
			})
		},
		getPrevArticle(id) {
			this.$ajax.get(this.$path.ARTICLE_PREV, {
				params: {
					id: id
				}
			}).then((response) => {
				this.refreshData(response.data);
				this.postPidToParentPanel(this.article.id);
			})
		},
		getNextArticle(pid) {
			this.$ajax.get(this.$path.ARTICLE_NEXT, {
				params: {
					id: pid
				}
			}).then((response) => {
				this.refreshData(response.data);
				this.postPidToParentPanel(this.article.id);
			})
		},
		checkIsFavoriteRemote() {
			this.$ajax.get(this.$path.CHECK_ARTICLE_FAVORITE, {
				params: {
					uid: this.$store.getters.user.id,
					aid: this.article.id
				}
			}).then((response) => {
				this.favorite = response.data;
			})
		},

		addFavoriteRemote() {
			if(this.favorite == false) {
				this.$ajax.post(this.$path.FAVORITE_ARTICLE_ADD, {
					uid: this.$store.getters.user.id,
					aid: this.article.id
				}).then(()=> {
					this.favorite = !this.favorite
					this.favoriteAmount = this.favoriteAmount + 1
				})
			}
		},
		deleteFavoriteRemote() {
			if(this.favorite == true) {
				this.$ajax.post(this.$path.FAVORITE_ARTICLE_DELETE, {
					uid: this.$store.getters.user.id,
					aid: this.article.id
				}).then((response)=> {
					this.favorite = !this.favorite
					this.favoriteAmount = this.favoriteAmount - 1;
				})
			}
		},

// 前端行为
		onPrev(id) {
			this.getPrevArticle(id);
		},
		onNext(id) {
			this.getNextArticle(id);
		},
		onFavorite() {
			if(this.$store.getters.isLogin == false) {
				this.$message.warn("未登录,请先登录!");
				return;
			}else {
				if(this.favorite == true) {
					//取消收藏
					this.deleteFavoriteRemote();
				}else {
					this.addFavoriteRemote();
				}
			}
		}
	}
}
</script>

<style scoped>
.comments {
	margin-top: 16px;
}
.article-content {
	min-height: 320px;
	padding: 0;
	margin: 16px 0 16px 48px;
}
</style>