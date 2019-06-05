<template>
<!--点击按钮触发图片统一上传-->
<mavon-editor
	id="editor"
	:subfield="false"
	:toolbarsFlag="styleObject.toolbarsFlag"
	:toolbars="toolbar"
	:style="`min-height: ${styleObject.minHeight}`"
	:defaultOpen="styleObject.view"
	:placeholder="styleObject.placeholder"
	ref="md"
	@imgAdd="$imgAdd" @imgDel="$imgDel">
</mavon-editor>
</template>
<script>

const _toobar = {
    bold: true, // 粗体
    italic: true,// 斜体
    header: true,// 标题
    underline: false,// 下划线
    strikethrough: false,// 中划线
    mark: false,// 标记
    superscript: false,// 上角标
    subscript: false,// 下角标
    quote: true,// 引用
    ol: true,// 有序列表
    ul: true,// 无序列表
    link: true,// 链接
    imagelink: false,// 图片链接
    code: true,// code
    table: true,// 表格
    subfield: true,// 是否需要分栏
    fullscreen: true,// 全屏编辑
    readmodel: true,// 沉浸式阅读
    htmlcode: true,// 展示html源码
    preview: true // 预览
}
export default {
	props:['styleObject'],
	data() {
		return {
			toolbar: _toobar,
			img_file: {}
		}
	},
	methods: {
		handlePreview() {
			if(this.styleObject.view === 'edit') {
				this.styleObject.view = 'preview'
			}else {
				this.styleObject.view = 'edit'
			}
		},
		handleSubmit() {

		},
		// 绑定@imgAdd event
		$imgAdd(pos, $file){
			// 缓存图片信息
			console.log('add', pos)
			this.img_file[pos] = $file;
		},
		$imgDel(pos){
			console.log('before delete', this.img_file)
			console.log('delete', this.img_file)
			console.log('pos', pos[0])
			delete this.img_file[pos[0]];
			console.log('after delete', this.img_file)
		}
		// uploadimg($e){
		// 	// 第一步.将图片上传到服务器.
		// 	var formdata = new FormData();
		// 	for(var _img in this.img_file){
		// 		formdata.append(_img, this.img_file[_img]);
		// 	}
		// 	this.$ajax({
		// 		url: 'server url',
		// 		method: 'post',
		// 		data: formdata,
		// 		headers: { 'Content-Type': 'multipart/form-data' },
		// 	}).then((res) => {
		// 		/**
		// 		 * 例如：返回数据为 res = [[pos, url], [pos, url]...]
		// 		 * pos 为原图片标志（0）
		// 		 * url 为上传后图片的url地址
		// 		 */
		// 		 // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
		// 		for (var img in res) {
		// 			// $vm.$img2Url 详情见本页末尾
		// 			this.$refs.md.$img2Url(img[0], img[1]);
		// 		}
		// 	})
		// },
	}
}
</script>
<style>
#editor {
	z-index: 1;
}
</style>