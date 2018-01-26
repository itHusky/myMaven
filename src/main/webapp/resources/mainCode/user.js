// user/delete?id=${userList.userId }
function delRow(id) {
	layer.confirm('确定删除？', function(index) {
		
		// ajax 还没有封装成 ajaxFormUtil
		ajaxFormUtil("user/delete", {
			'id' : id
		}, function(result) {
			layer.msg(result);
		},null,function(){
			layer.msg(result);
		});
		
		layer.close(index);
	});
}