import Vue from 'vue'
import Home from '@/views/Home'
import router from './router'
import store from './store'
import './plugins/plugin.js'

Vue.config.productionTip = false

window.vm = new Vue({
  router,
  store,
  render: h => h(Home)
}).$mount('#app');