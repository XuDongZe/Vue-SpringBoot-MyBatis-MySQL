import Vue from 'vue'
import Vuex from 'vuex'
import {Message as message} from 'ant-design-vue'

Vue.use(Vuex)

// const _userState = {
// 	STATA_TOURIST: 'tourist',
// 	STATE_USER: 'user',
// 	STATE_MANAGER : 'manager'
// }
export default new Vuex.Store({
	state: {
		login: false,
		loginClosable: true,
		user: null,
		loginVisible: false,
		loading: false,
		history: {
			favoriteList: [],
			problemDoneList: []
		},
		currentPid: "",
		// user: {
		// 	id: '2015302462',
		// 	username: "许东泽",
		// 	type: 'user' // or manager
		// }
	},
	getters: {
		isLogin: (state) => {
			return state.login
		},
		user: (state, getters) => {
			return getters.isLogin ? state.user : null
		},
		isLoginVisible: (state) => {
			return state.loginVisible
		},
		loading: (state) => {
			return state.loading
		},
		currentPid: (state) => {
			return state.currentPid;
		}
	},
	mutations: {
		setCurrentPid: (state, pid) => {
			state.pid = pid;
		},
		updateUserSlective(state, user) {
			this.state.user.username = user.username;
			this.state.user.gender = user.gender;
			this.state.user.description = user.description;
		},

		checkLogin: (state) => {
			if(state.isLogin == false) {
				message.warn('请先登录!');
			}
		},

		loginMutation: (state, user) => {
			state.user = user;
			state.login = true;
		},
		logoutMutation: (state) => {
			state.user = null
			state.login = false
		},
		// 公共的登录对话框属性
		closeLoginModal: (state) => {
				state.loginVisible = false
		},
		openLoginModal: (state) => {
			state.loginVisible = true;
		},

		// loading 遮罩层
		openLoading: (state) => {
			state.loading = true
		},
		closeLoading: (state) => {
			state.loading = false
		}
	},
	actions: {
		refreshToken(token) {
			localStorage.token = token
		}
	}
})
