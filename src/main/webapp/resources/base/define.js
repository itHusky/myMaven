/**
 * define 
 * @fileOverview AMD规范，全称是Asynchronous Module Definition，即异步模块加载机制
 * define([module-name?], [array-of-dependencies?], [module-factory-or-object]);
 * module-name: 模块标识，可以省略。 
 * array-of-dependencies: 所依赖的模块，可以省略。
 * module-factory-or-object: 模块的实现，或者一个JavaScript对象。
 */
/**
 * 定义一个基础模块
 */
define('base', [], function() {

});
/**
 * 定义一个test模块
 */
define('test', [], function() {

	/**
     * 上传入口类。
     * @class test类
     * @constructor
     * @grammar new test( opts ) => Uploader 语法
     * @example 例 
     * var test = test({
     *     swf: 'path_of_swf/Uploader.swf',
     *
     *     // 开起分片上传。
     *     chunked: true
     * });
     */
    function test( opts ) {
        this.options = $.extend( true, {}, test.options, opts );
        this._init( this.options );
    }
    
    test.options = {};
	
	$.extend( test.prototype, {
		status:"这是一个测试！",
		base: function(){
			console.log("test.base");
		},
		test: function(){
			console.log("test.test");
		}
	});
	
	return test;
});
/**
 * 定义一个组件基类模块
 */
define('widgets/widget', [ 'base', 'test' ], function(base,test) {
	base.test = function(){
		return test.base;
	};
	return base("test").base;
});
/**
 * 定义一个PDF模块
 */
define('widgets/pdf', [ 'base' ], function() {

});
/**
 * 定义一个图片模块
 */
define('widgets/image', [ 'base' ], function() {

});

/**
 * 如何在界面中使用
*/
// 比方说在a.js文件中定义这个模块在界面中使用
require(["js/a"],function(){
	alert("define 模块");
});
require(["test"],function(){
	alert("define 模块");
});
/**
 * 加载的JS可能来自本地服务器、其他网站或CDN，这样就不能通过上面的方式来加载了
 */
require.config({
    paths : {
        "jquery" : ["http://libs.baidu.com/jquery/2.0.3/jquery"],
        "a" : "js/a"
    }
});
require(["jquery","a"],function($){
    $(function(){
        alert("load finished");  
    });
});