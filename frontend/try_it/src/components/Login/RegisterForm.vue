<template>
<a-form
	:form="form"
	class="register-form"
>
	<a-form-item>
		<a-input
			v-decorator="[
				'username',
				{ rules: [{ required: true, message: '请输入用户名!' }] }
			]"
			placeholder="用户名"
		>
			<a-icon
				slot="prefix"
				type="user"
				style="color: rgba(0,0,0,.25)"
			/>
		</a-input>
	</a-form-item>
	<a-form-item>
		<a-input
			v-decorator="[
				'password',
				{ rules: [{ required: true, message: '请输入密码!' }] }
			]"
			type="password"
			placeholder="密码"
		>
			<a-icon
				slot="prefix"
				type="lock"
				style="color: rgba(0,0,0,.25)"
			/>
		</a-input>
	</a-form-item>
	<a-form-item>
		<a-button
			type="primary"
			html-type="submit"
			class="register-form-button"
			@click="handleRegister"
		>
			注册
		</a-button>
		<a
			class="back-to-login"
			@click="handleLogin"
		>
			返回登录
		</a>
	</a-form-item>
</a-form>
</template>

<script>
export default {
	name: "RegisterForm",
	beforeCreate () {
		this.form = this.$form.createForm(this);
	},
	methods: {
		handleRegister (e) {
			e.preventDefault();
			let vm = this;
			vm.form.validateFields((err, values) => {
				if (!err) {
					//可能需要在store的action中使用异步操作
					this.$ajax.post("/register", {
						username: values.username,
						password: values.password
					})
					.then(() => {
						this.$storage.save("username", values.username);
						this.$storage.save("password", values.password);
						//注册成功转到登录界面
						this.$emit('change-to-login');
					})
				}
			});
		},
		handleLogin (e) {
			e.preventDefault();
			this.$emit('change-to-login');
		}
	}
}
</script>

<style scoped>
.register-form .register-form-button {
	width: 100%;
}
.register-form .back-to-login {
	float: left;
}
</style>