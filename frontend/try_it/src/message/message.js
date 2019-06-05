class ErrorCodeMsg {
	constructor() {
		this.UNKOWN = {
			code: 999,
			msg: "未知的错误"
		},
		this.error = [
			{
				code: 100,
				msg: "网络异常"
			},
			{
				code: 300,
				msg: "参数错误"
			},
			{
				code: 303,
				msg: "数据已经存在"
			},
			{
				code: 404,
				msg: "请求的接口或数据不存在"
			},
			{
				code: 500,
				msg: "服务器内部错误"
			},
			{
				code: 600,
				msg: "数据库内部错误"
			},
			this.UNKOWN
		];
	}

	getMessage(errCode) {
		let res = this.error.find((item) => {
			return item.code === errCode
		})
		if(res ) 
			return res.msg
		else {
			return this.UNKOWN.msg
		}
	}
}
export const errorCodeMsg = new ErrorCodeMsg();

// class Message {
// 	constructor() {
// 		console.log(window)
// 		this.error = window.vm.$message.error
// 		this.info = window.vm.$message.info
// 		this.warn = window.vm.$message.warn
// 		this.success = window.vm.$message.success
// 		this.error = window.vm.$message.error
// 	}
// }
// export const message = new Message();
