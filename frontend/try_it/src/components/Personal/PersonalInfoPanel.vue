<template>
<a-card >
	<div slot="title">
		<span>我的信息</span>
		<div class="button-edit-group" v-show="isEdit">
			<a-button
				type="primary"
				@click="handleCancelSave"
				class="button-cancel"
				>
				取消
			</a-button>
			<a-button
				type="primary"
				@click="handleSave"
				class="button-save"
				>
				保存
			</a-button>
		</div>
		<div class="button-preview-group" v-show="!isEdit">
			<a-button
				type="primary"
				@click="handleEdit"
				class="button-edit"
				>
				编辑
			</a-button>
		</div>
	</div>
	
	<template>
	<a-form
	  :form="form"
	  class="login-form"
	>
		<a-form-item
	    v-bind="formItemLayout"
	    label="我的编号">
	    <div class="personal-info-text" >{{user.id}}</div>
	  </a-form-item>
	  <a-form-item
	    v-bind="formItemLayout"
	    label="我的昵称">
	    <a-input
	      v-if="isEdit"
	      v-decorator="[
	        'username', {initialValue: user.username}]"
	      :placeholder="user.username"
	    >
	    </a-input>
	    <div class="personal-info-text" v-else>{{user.username}}</div>
	  </a-form-item>

	  <a-form-item
	    v-bind="formItemLayout"
	    label="我的性别">
	    <a-select
	      v-if="isEdit"
	      v-decorator="['gender', {initialValue: user.gender}]"
	      :placeholder="user.gender"
	      >
	      <a-select-option value="男">男</a-select-option>
	      <a-select-option value="女">女</a-select-option>
	    </a-select>
	    <div class="personal-info-text" v-else>{{ user.gender }}</div>
	  </a-form-item>
	  
	  <a-form-item
	    v-bind="formItemLayout"
	    label="我的简介">
	    <a-textarea
	      v-if="isEdit"
	      :rows="5"
	      v-decorator="['description', {initialValue: user.description}]"
	      :placeholder="user.description"
	    >
	    </a-textarea>
	    <div class="personal-info-text" v-else>
	      {{ user.description }}
	    </div>
	  </a-form-item>
	</a-form>
	</template>
</a-card>
</template>

<script>
export default {
	name: "PersonalInfoPanel",
	beforeCreate () {
    this.form = this.$form.createForm(this);
  },
	data() {
		return {
			formItemLayout: {
        labelCol: { span: 6 },
        wrapperCol: { span: 14 }
      },
			isEdit: false
		}
	},
	computed: {
    user() {
      return this.$store.getters.user;
    }
  },
	methods: {
		postUserInfoRemote(values) {
			let user = {
				id: this.user.id,
				...values
			};
      this.$ajax.post(this.$path.CUSTOMER_UPDATE, {
      	...user
      }).then(() => {
        this.$store.commit('updateUserSlective', user);
      })
    },

		handleSave (e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
        	if(this.judgeIsChanged(values)) {
        		this.postUserInfoRemote(values);
        	}
        	this.isEdit = false;
        }
      });
    },

    judgeIsChanged(values) {
    	if(values.username === this.user.username &&
    		values.gender === this.user.gender &&
    		values.description === this.user.description) {
    		return false;
    	}
    	return true;
    },
		handleCancelSave() {
			this.isEdit = false
		},
		handleEdit() {
			this.isEdit = true
		}
	}

}
</script>

<style scoped>
.button-edit-group, .button-preview-group {
	display: inline-block;
	float: right;
}
.button-edit-group .button-cancel {
	margin-right: 16px;
}
</style>