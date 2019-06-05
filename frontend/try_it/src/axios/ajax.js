//request.js
import axios from "axios"
// import qs from "qs"
// import { errorCodeMsg as ErrorCodeMsg} from '../message/message'

/****** 创建axios实例 ******/
const service = axios.create({
		// baseURL: process.env.BASE_URL,  // api的base_url
		baseURL: '/api',
		timeout: 5000  // 请求超时时间
});

/****** request拦截器==>对请求参数做处理 ******/
service.interceptors.request.use(config => {
		// 这里添加loading遮罩
		window.vm.$store.commit('openLoading');
		// config.method === 'post'
		//     ? config.data = qs.stringify({...config.data})
		//     : config.params = {...config.params};
		// config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
		
		return config;
}, error => {
		Promise.reject(error);
});

/****** respone拦截器==>对响应做处理 ******/
service.interceptors.response.use(
		response => {
			window.vm.$store.commit('closeLoading');
			if (response.data.status != 0) {
				return response.data;
			} else {
				let msg = response.data.msg;
				window.vm.$message.error(msg);
				return Promise.reject(response.data);
			}
		},
		error => {  //响应错误处理
			window.vm.$store.commit('closeLoading');
			let text = JSON.stringify(error.response.data);
			window.vm.$message.error(text);
			return Promise.reject(error);
		}
);

export default service;