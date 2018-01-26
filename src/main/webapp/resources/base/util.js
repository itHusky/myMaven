/**
 * 使用ajax提交表单
 * 
 * @param {}
 *            _url 目标URL
 * @param {}
 *            _data 传递JSON数据
 * @param {}
 *            _callbackSuccess 执行成功回调函数
 * @param {}
 *            _async 同步|异步
 * @param {}
 *            _callbackError 执行失败回调函数
 */
function ajaxFormUtil(_url, _data, _callbackSuccess, _async, _callbackError) {
	if (_async == null || _async == "undefined") {
		_async = true;// 默认异步
	}
	var content = "application/x-www-form-urlencoded; charset=utf-8";
	$.ajax({
		type : "POST",
		cache : false,
		async : _async,
		contentType : content,
		url : _url,
		data : _data,
		dataType : "json",
		success : _callbackSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("ajax Error:", XMLHttpRequest, textStatus,
				errorThrown);
			if ((_callbackError && typeof (_callbackError) === "function")) {
				_callbackError();
			} else {
				alert("系统内部错误！\nAjax错误信息："+ textStatus + "" + errorThrown);
			}
		}
	});
}

/*function ajaxFormUtil(_url, _data, _callbackSuccess){
	ajaxFormUtil(_url, _data, function(result){
		if (!result.success) {
			alert("请求失败：" + result.message);
		} else {
			_callbackSuccess(eval('('+ result.message +')'));
		}
	},null,null);
}*/
