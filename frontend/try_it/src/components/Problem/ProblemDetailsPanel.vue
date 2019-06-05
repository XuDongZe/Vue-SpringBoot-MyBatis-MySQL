<template>
<div>
	<a-card class="content-container">
		<template class="detail-title" slot="title">
			{{ problem.id + '.' + problem.title }}
		</template>
		<a-card-meta style="min-height: 240px">
		  <router-link slot="avatar"
		    :to="'/personal?id=' + problem.author.id">
		    <a-tooltip :title="problem.author.username" placement="bottom">
		    	<a-avatar :src="problem.author.avatar" >{{ problem.author.username }}</a-avatar>
		  	</a-tooltip>
		  </router-link>
		  <template slot="description">
		  	{{ problem.description }}
		  </template>
		  <div slot="description">
			  <ul class="description" >
			  	<li class="line">
			  		<span class="label">知识点:</span>
			  		<span class="content" style="margin-right: -3px">
					  	<router-link 
					  	  v-for="tag in problem.tags"
					  	  :key="tag.id"
					  	  :to="'/problems/tag?id=' + tag.id">
					  	  <a-tag color="#87d068">{{ tag.name }}</a-tag>
					  	</router-link>	
				  	</span>
			  	</li>
			  	<li class="line">
			  		<span class="label">已通过:</span>
			  		<span class="content">{{ problem.solved }}</span>
			  	</li>
			  	<li class="line">
			  		<span class="label">正确率:</span>
			  		<span class="content">{{ problem.correctRate + '%' }}</span>
			  	</li>
			  </ul>
			  <div class="options-container" >
			  	<a-radio-group class="radio-group" @change="getUserAnswer">
			  		<div class="radio-container">
			  			<a-radio label="A" value="A">
			  				A.
			  				<span class="radio-content">{{ problem.optionA }}</span>
			  			</a-radio>
			  		</div>
			  		<div class="radio-container">
			  			<a-radio label="B" value="B">
			  				B.
			  				<span class="radio-content">{{ problem.optionB }}</span>
			  			</a-radio>
			  		</div>
			  		<div class="radio-container">
			  			<a-radio label="C" value="C">
			  				C.
			  				<span class="radio-content">{{ problem.optionC }}</span>
			  			</a-radio>
			  		</div>
			  		<div class="radio-container">
			  			<a-radio label="D" value="D">
			  				D.
			  				<span class="radio-content">{{ problem.optionD }}</span>
			  			</a-radio>
			  		</div>
			  	</a-radio-group>
			  </div>
			</div>
		</a-card-meta>
		<div class="submit-area" style="margin-left: 48px">
			<a-button type="primary" @click="onSubmit">提交结果</a-button>
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
	    	@click="onPrev(problem.id)"
	    	class="action-button" type="left-circle" style="fontSize: 1.8em" />
	    <a-icon @click="onNext(problem.id)" class="action-button" type="right-circle" style="fontSize: 1.8em" />
	 	</template>
	</a-card>
	<CommentsPanel 
	@getComments="getComments"
	class="comments" 
	:objects="{comments: comments, uid: problem.author.id, uname: problem.author.username, linkid: problem.id, type:'problem'}"></CommentsPanel>
</div>
</template>

<script>
import CommentsPanel from '@/components/Comment/CommentsPanel'

export default {
	name: "ProblemDetailsPanel",
	components: {
		CommentsPanel
	},

	created() {
		this.getProblemByIdRemote(this.$route.query.id)
	},
	data() {
		return {
			answer: "",
			problem: {},
			favorite: false,
			facoriteAmount: 0,
			supportAmount: 0,
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
		postPidToParentPanel(pid) {
			this.$emit('changePid', pid);
		},
// 后端请求
		refreshData(problem) {
			this.problem = problem;
			this.favoriteAmount = problem.favorites;
			this.supportAmount = problem.supports;
			this.checkIsFavoriteRemote(this.problem.id);
			this.getComments();
		},

		getComments() {
			this.$ajax.get(this.$path.CommentProblems, {
		    params: {
		      pid: this.problem.id,
		      pageNum: 1,
		      pageSize: 1000
		    }
		  })
			.then((response) => {
				this.comments = response.data.list
			})
		},
		getProblemByIdRemote(pid) {
			this.$ajax.get(this.$path.Problem, {
				params: {
					id: pid
				}
			}).then((response) => {
				this.refreshData(response.data);
			})
		},
		getPrevProblem(pid) {
			this.$ajax.get(this.$path.PROBLEM_PREV, {
				params: {
					id: pid
				}
			}).then((response) => {
				this.refreshData(response.data);
				this.postPidToParentPanel(this.problem.id);
			})
		},
		getNextProblem(pid) {
			this.$ajax.get(this.$path.PROBLEM_NEXT, {
				params: {
					id: pid
				}
			}).then((response) => {
				this.refreshData(response.data);
				this.postPidToParentPanel(this.problem.id);
			})
		},
		checkIsFavoriteRemote(pid) {
			this.$ajax.get(this.$path.CHECK_PROBLEM_FAVORITE, {
				params: {
					uid: this.$store.getters.user.id,
					pid: pid
				}
			}).then((response) => {
				this.favorite = response.data;
			})
		},

		addFavoriteRemote() {
			if(this.favorite == false) {
				this.$ajax.post(this.$path.FAVORITE_PROBLEM_ADD, {
					uid: this.$store.getters.user.id,
					pid: this.problem.id
				}).then(()=> {
					this.favorite = !this.favorite
					this.favoriteAmount = this.favoriteAmount + 1
				})
			}
		},
		deleteFavoriteRemote() {
			if(this.favorite == true) {
				this.$ajax.post(this.$path.FAVORITE_PROBLEM_DELETE, {
					uid: this.$store.getters.user.id,
					pid: this.problem.id
				}).then((response)=> {
					this.favorite = !this.favorite
					this.favoriteAmount = this.favoriteAmount - 1;
				})
			}
		},

		postSubmitRemote() {
			this.$ajax.post(this.$path.SUBMISSION_ADD, {
				uid: this.$store.getters.user.id,
				pid: this.problem.id,
				userAnswer: this.answer,
				status: this.problem.answer == this.answer ? 'Access' : 'Error'
			}).then((response) => {
				if(this.problem.answer == this.answer) {
					this.$message.success('Access!');
				}else {
					this.$message.error('Error!');
				}
			})
		},

// 前端行为
		getUserAnswer(e) {
			this.answer = e.target.value;
		},
		onSubmit() {
			if( this.answer == "") {
				return;
			}
			if( this.$store.getters.isLogin == false ) {
				this.$message.warn("请先登录!");
				return;
			}
			this.postSubmitRemote();
		},
		// 数据库不连续怎么办????
		onPrev(id) {
			this.getPrevProblem(id);
		},
		onNext(id) {
			this.getNextProblem(id);
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
.options-container {
	width: 66%;
	float: left;
	margin-top: 5%;
}
.description {
	display: block;
	margin-top: 5%;
	float: right;
	list-style: none;
	width: 33%;
	padding: 0;
	border: 1px solid #ccc;
	font-weight: bold;
}
.line {
	margin: 8px 0;
	height: 1.5em;
	border-bottom: 1px solid #ccc;
}
.label {
	float: left;
	margin-left: 8px;
}
.content {
	float: right;
	margin-right: 8px;
}
.radio-container {
	margin-bottom: 8px;
}
.radio-content {
	margin-left: 8px;
	white-space: normal;
}

.comments {
	margin-top: 16px;
}
</style>

