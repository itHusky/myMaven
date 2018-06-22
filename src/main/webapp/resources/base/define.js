/*!
 *参考资料：https://www.cnblogs.com/zhongjiang/p/6554514.html 重点
 *参考资料：http://www.w3school.com.cn/js/js_objects.asp 重点
 *参考资料：https://blog.csdn.net/dbtbest/article/details/78324275
 *参考资料：http://www.requirejs.cn/ 官网
 */
/*!
AMD 是 RequireJS 在推广过程中对模块定义的规范化产出。
CMD 是 SeaJS 在推广过程中对模块定义的规范化产出。
对于依赖的模块，AMD 是提前执行，CMD 是延迟执行。
AMD:提前执行（异步加载：依赖先执行）+延迟执行
CMD:延迟执行（运行到需加载，根据顺序执行）
CMD 推崇依赖就近，AMD 推崇依赖前置。
*/
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
	return ({
		sa : function() {
			console.log("show Base sa!");
		}
	});
});
/**
 * 定义一个test模块
 */
define('test', [ 'require' ], function() {

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
	function test(opts) {
		this.options = $.extend(true, {}, test.options, opts);
		this._init(this.options);
	}

	test.options = {};

	$.extend(test.prototype, {
		status : "这是一个测试！",
		base : function() {
			console.log("test.base");
		},
		test : function() {
			console.log("test.test");
		}
	});

	return test;
});
/**
 * 定义一个组件基类模块
 */
define('widgets/widget', [ 'base', 'test' ], function(base, test) {
	base.test = function() {
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
require([ "js/a" ], function() {
	alert("define 模块");
});
require([ "test" ], function() {
	alert("define 模块");
});
/**
 * 加载的JS可能来自本地服务器、其他网站或CDN，这样就不能通过上面的方式来加载了
 */
require.config({
	paths : {
		"jquery" : [ "http://libs.baidu.com/jquery/2.0.3/jquery" ],
		"a" : "js/a"
	// js文件别名
	}
});
require([ "jquery", "a", "base" ], function($) {
	$(function() {
		alert("load finished");
	});

});

/**
 CMD 规范：通用模块定义
 该规范明确了模块的基本书写格式和基本交互规则。该规范是在国内发展出来的。AMD是依赖关系前置，CMD是按需加载。
 在 CMD 规范中，一个模块就是一个文件。代码的书写格式如下：
 define(factory);
 factory 为函数时，表示是模块的构造方法。执行该构造方法，可以得到模块向外提供的接口。factory 方法在执行时，默认会传入三个参数：require、exports 和 module
 */
define(function() {

});