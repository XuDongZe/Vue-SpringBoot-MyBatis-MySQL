<template>
		<div v-if="loading" class="loading_container" @touchmove.prevent @scroll.prevent>
				<div class="spinner">
						<div class="double-bounce1"></div>
						<div class="double-bounce2"></div>
						<p class="loading_tip">{{loadingText}}</p>
				</div>
		</div>
</template>

<script>
export default {
		name: "loading",
		data(){
			return {
			}
		},
		computed: {
			loading() {
				return this.$store.getters.loading
			}
		},
		props:{
				loadingText: {
					type:String,
					default:"loading..." 
				}

		}
}
</script>
<style type="text/css" lang="less" scoped>
.loading_container{
		z-index: 1001;
		position:fixed;
		top:0;
		left:0;
		bottom:0;
		right:0;
		width:100%;
		height:100%;
		background-color:rgba(0,0,0,.5);
		text-align:center;
		>span{
				position:absolute;
				top:50%;
				left:50%;
				transform:translate(-50%,-50%);
		}
}
.spinner {
		width: 60px;
		height: 60px;
		position: relative;
		top:50%;
		left:50%;
		transform: translate(-50%,-50%);
}
.loading_tip{
		color: #FFF;
		position: relative;
		top: 64px;
}
.double-bounce1, .double-bounce2 {
		width: 100%;
		height: 100%;
		border-radius: 50%;
		background-color: #67CF22;
		opacity: 0.6;
		position: absolute;
		top: 0;
		left: 0;
		-webkit-animation: bounce 2.0s infinite ease-in-out;
		animation: bounce 2.0s infinite ease-in-out;
}
.double-bounce2 {
		-webkit-animation-delay: -1.0s;
		animation-delay: -1.0s;
}
@-webkit-keyframes bounce {
		0%, 100% { -webkit-transform: scale(0.0) }
		50% { -webkit-transform: scale(1.0) }
}
@keyframes bounce {
		0%, 100% {
				transform: scale(0.0);
				-webkit-transform: scale(0.0);
		} 50% {
				transform: scale(1.0);
				-webkit-transform: scale(1.0);
		}
}
</style>
<style type="text/css" lang="less">
.loading{
		.rolling{
				width:50px;
				height:50px;
				border-radius:50%;
				border:1px solid transparent;
				border-top-color: #dedede;
				animation:rolling 2s infinite;
		}
}
@keyframes rolling{
		from{
				transform:rotate(0deg);
				-ms-transform:rotate(0deg); /* Internet Explorer */
				-moz-transform:rotate(0deg); /* Firefox */
				-webkit-transform:rotate(0deg); /* Safari 和 Chrome */
				-o-transform:rotate(0deg); /* Opera */
		}
		to{
				transform:rotate(360deg);
				-ms-transform:rotate(360deg); /* Internet Explorer */
				-moz-transform:rotate(360deg); /* Firefox */
				-webkit-transform:rotate(360deg); /* Safari 和 Chrome */
				-o-transform:rotate(360deg); /* Opera */
		}
}
</style>