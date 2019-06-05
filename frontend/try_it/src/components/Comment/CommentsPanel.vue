<template>
<div>
  <a-card>
    <span slot="title">
      <span @click="handleReply(null)" style="margin-right: 16px; float: right">
        <a-button type="primary"><a-icon type="edit" />写评论</a-button>
      </span>
    </span>
    <a-list
      itemLayout="vertical"
      size="large"
      :dataSource="objects.comments"
   	>
     	<a-list-item slot="renderItem" slot-scope="item, index" :key="item.id"
        style="padding-bottom: 0"
        >
     		<a-list-item-meta >
    	    <router-link slot="avatar" :to="'/userspace?id=' + item.from.id">
    	      <a-tooltip :title="item.from.username" placement="left">
              <a-avatar  :src="item.from.avatar" >{{item.from.username}} </a-avatar>
            </a-tooltip>
    	    </router-link>
    	    <div slot="description">
            <div class="comment-to-user" >
              <span  class="comment-to-user">
                <router-link v-if="item.to.id !== '001'" :to="'/userspace?id=' + item.to.id">
                  @{{ item.to.username }}
                </router-link>
                <span class="comment-time">
                {{ item.time }}

              </span>
              </span>
            </div>
            <div style="margin-top: 3px;">
              <div class="comment-content">{{ item.content }}</div>
              <span class="review" style="float: right;">
                <a  @click="handleReply(item)">回复</a>
              </span>
              <span 
                v-if="canDelete(item.from.id)" style="float: right; margin-right: 16px">
                <a @click="handleDelete(item.id)">
                  删除
                </a>
              </span>
              <a-tooltip v-else title="没有权限">
              <span  style="color: gray; opacity: 0.6; float: right; margin-right: 16px">删除</span>
            </a-tooltip>
            </div>
          </div>
        </a-list-item-meta>
      </a-list-item>
   	</a-list>
  </a-card>
  <a-modal
    :footer="null"
    :visible="true"
    v-if="visible"
    @cancel="handleCancle">
    <a-form :form="modalForm">
        <a-textarea
          rows="3"
          :placeholder="editorStyle.placeholder"
          v-model="currentComment.content"
        >
        </a-textarea>

      <a-form-item style="margin-bottom: -8px">
        <a-button
          type="primary"
          style="min-width: 10%; margin: 0 43%"
          html-type="submit"
          @click="handleSubmit"
        >
          提交
        </a-button>
      </a-form-item>
    </a-form>
  </a-modal>
</div>
</template>

<script>
export default {
  name: "",
  props:['objects'],
  beforeCreate () {
    this.form = this.$form.createForm(this)
    this.modalForm = this.$form.createForm(this)
  },
  computed: {
    
  },
  data() {
    return {
      currentComment: {
        id: "",
        fromUid: "",
        toUid: "",
        content: ""
      },
      comments: [],
      visible: false,
      editorStyle: {
        placeholder: ""
      }
    }
  },

  methods: {
    canDelete(id) {
      return id === this.uid || id === this.$store.getters.user.id
    },
// 远端
    refresh() {
      this.$emit('getComments'); //刷新数据
    },

    postCommentRemote() {
      if(this.currentComment.content == "") {
        return;
      }
      let url = this.objects.type === 'problem' ? 
        this.$path.COMMENT_PROBLEM_ADD :
        this.$path.COMMENT_ARTICLE_ADD;

      this.$ajax.post(url, {
        ...(this.currentComment)
      }).then(() => {
        this.refresh();
        this.handleCancle(); // 刷新视图
      })
    },

    postDeleteRemote(id) {
      let url = this.objects.type === 'problem' ? 
        this.$path.COMMENT_PROBLEM_DELETE :
        this.$path.COMMENT_ARTICLE_DELETE; 
      this.$ajax.post(url, {
        id: id,
      })
    },

// 前端
    handleDelete(id) {
      this.postDeleteRemote(id);
      this.refresh();
    },

    handleCancle() {
      this.visible = false,
      this.currentComment.content = ""
    },
    handleSubmit() {
       this.postCommentRemote();
    },
    handleReply(item) {
      this.currentComment.fromUid = this.$store.getters.user.id;
      this.currentComment.toUid = item === null ? this.objects.uid : item.from.id;
      if(this.objects.type === 'problem') {
        this.currentComment.pid = this.objects.linkid; 
      }else {
        this.currentComment.aid = this.objects.linkid;
      }
      if(item != null) {
        this.editorStyle.placeholder = "@" + item.to.username + ": ";
      }else {
        this.editorStyle.placeholder = "@" + this.objects.uname + ": ";
      }
      this.visible = true
    }
  }
}
</script>

<style scoped>
.comment-content {
  margin-left: 6px;
  font-weight: bold;
  max-width: 95%;
  word-break: break-word;
}
</style>