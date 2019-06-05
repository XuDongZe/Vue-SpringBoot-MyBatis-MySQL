<template>
<a-form
  :form="form"
  class="login-form"
>
  <a-form-item>
    <a-input
      v-decorator="[
        'username',
        {
          rules: [{ required: true, message: '请输入用户名!' }]
        }
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
        { 
          rules: [{ required: true, message: '请输入密码!' }] 
        }
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
  <a-form-item style="margin: -8px 0 0 0;">
    <a-checkbox
      v-decorator="[
        'remember',
        {
          valuePropName: 'checked',
          initialValue: true
        }
      ]"
    >
      Remember me
    </a-checkbox>
  </a-form-item>
  <a-form-item>
    <a-button
      type="primary"
      html-type="submit"
      class="login-form-button"
      @click="handleLogin"
    >
      登录
    </a-button>
    <a
      class="login-form-forgot"
      href="#"
    >
      忘记密码?
    </a>
    <a
      class="register-form-href"
      @click="handleRegister"
    >
      没有用户名? 现在注册->
    </a>
  </a-form-item>
</a-form>
</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
    }
  },
	beforeCreate () {
    let options = {
      mapPropsToFields: () => {
        console.log(this.username)
        return {
          username: this.$form.createFormField({
            value: this.username
          }),
          password: this.$form.createFormField({
            value: this.password
          })
        }
      }
    }
    this.form = this.$form.createForm(this, options);

  },
  computed: {
    username() {
      return this.$storage.get("username");
    },
    password() {
      return this.$storage.get("password");
    }
  },
  methods: {
    login(values) {
      let url = this.$path.CUSTOMER_LOGIN;
      this.$ajax.post(url, {
        username: values.username,
        password: values.password 
      })
      .then((response) => {
        let user = response.data;
        this.$store.commit('loginMutation', user); //状态
        this.$router.push('/'); // 路由
        this.$emit('cancel'); // 视图
      })
    },
    handleLogin (e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          if(values.remember == true) {
            this.$storage.save("username", values.username);
            this.$storage.save("password", values.password);
          }else {
            this.$storage.clear("username");
            this.$storage.clear("password");
          }
          this.login(values);
        }
      });
    },
    handleRegister (e) {
      e.preventDefault();
      this.$emit('change-to-register');
    },
    handleError (error) {
      console.log(error);
    }
  }
}
</script>

<style scoped>
.login-form .login-form-forgot {
  float: left;
}
.login-form .register-form-href {
  float: right;
}
.login-form .login-form-button {
  width: 100%;
}
</style>