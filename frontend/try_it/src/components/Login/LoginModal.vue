<template>
<a-modal
  ref="loginModal"
  @cancel="handleCancel"
  :footer="null"
  :visible="getVisible"
  :title="getTitle"
  :closable="false"
  class="login-modal">
  <LoginForm
    @cancel="handleCancel"
    @change-to-register="changeToRegisterForm"
    v-if="isLoginForm">
  </LoginForm>
  <RegisterForm
    @change-to-login="changeToLoginForm"
    v-else>
  </RegisterForm>
</a-modal>
</template>

<script>
import LoginForm from '@/components/Login/LoginForm'
import RegisterForm from '@/components/Login/RegisterForm'
export default {
  components:{
    LoginForm, RegisterForm
  },
  data() {
    return {
      isLoginForm: true,
    }
  },
  computed: {
    getVisible() {
      return this.$store.getters.isLoginVisible
    },
    getTitle() {
      return this.isLoginForm ? "登录" : "注册"
    }
  },
  methods: {
    handleCancel (e) {
      this.$store.commit('closeLoginModal')
    },
    changeToLoginForm() {
      this.isLoginForm = true
    },
    changeToRegisterForm() {
      this.isLoginForm = false
    }
  }
};
</script>

<style scoped>
.login-modal {
  max-width: 300px;
}
</style>