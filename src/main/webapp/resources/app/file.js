/**
 * 下载文件 并保存
 * 
 * @param id
 *            文件流水号
 * @param name
 *            文件名称 用于浏览器本地创建文件
 * @param extension
 *            文件格式 用于浏览器本地创建文件
 */
function downForm(id, name, extension) {
	/*
	 * 自定义的JsonResult<String> status
	 */
	// var fileName = name + '.' + extension;
	ajaxFormUtil("downorup/download?id=" + id, /* $("#fm").serialize() 序列化  */null,
			function(result) {
				console.log(result);
				if (result.status) {
						/* 创建保存文件 */
						console.log(result.message);
					} else {
						layer.alert("下载失败！" + result.message + result.success);
					}
			});
};