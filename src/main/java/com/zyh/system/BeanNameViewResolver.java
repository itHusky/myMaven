package com.zyh.system;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 1、Spring MVC 九大组件之 ViewResolver
 * 注：ViewResolver用来将String类型的视图名（也叫逻辑视图）和Locale解析为View类型的视图
 * ViewResolver的使用需要注册到Spring MVC容器中，
 * 默认使用的是org.springframework.web.servlet.view.InternalResourceViewResolver
 *
 * 2、Spring MVC 九大组件之 RequestToViewNameTranslator
 * ViewResolver根据ViewName查找View，但有的Handler处理完并没有设置View，
 * 也没有设置viewName，这时就需要从request中获取viewName。
 * 也就是RequestToViewNameTranslator的任务
 * 代码：
 *  public interface RequestToViewNameTranslator {
 *    String getViewName(HttpServletRequest request) throws Exception;
 *  }
 * 只有一个getViewName方法，能够从request获取到viewName就可以了
 *
 * 注意事项：XXX
 * RequestToViewNameTranslator在Spring MVC容器中只能配置一个，
 * 所以所有request到ViewName的转换规则都要在一个Translator里面实现。
 *
 * 3、Spring MVC 九大组件之LocaleResolver
 * ViewResolver有两个参数，viewname和Locale，viewname来自Handler或RequestToViewNameTranslator。
 * locale变量就来自LocaleResolver。
 * LocaleResolver用于从request解析出Locale。
 * 一共只有两个方法，
 * 第一个方法就是起到获取Locale的作用，在介绍doService方法时说过，
 * 容器会将localeResolver设置到request的attribute中。
 * request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE,this.localeResolver);
 * 第二个方法可以将Locale设置到request中。
 * SpringMVC提供了统一修改request中Locale的机制，
 * 就是我们在分析doDispatch时见过的Interceptor。SpringMVC已经写好现成的了，配置一下就可以，
 * 也就是org.springframework.web.servlet.i18n.LocaleChangeInterceptor.
 * <mvc:interceptors>
 *   <mvc:interceptor>
 *       <mvc:mapping path="/*"/>
 *       <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
 *       //这里也可以自定义参数的名称，如：
 *       //<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor" p:paramName="lang"/>
 *   </mvc:interceptor>
 * </mvc:interceptors>
 * 用途：
 * 用到Locale的地方有两处，
 *      1、ViewResolver解析视图的时候。
 *      2、使用到国际化资源或者主题的时候。国际化资源或主题主要使用RequestContext的getMessage和getThemeMessage方法。
 *
 * 4、Spring MVC 九大组件之ThemeResolver（主题解析器）
 * 代码：
 *  public interface ThemeResolver {
 *       String resolveThemeName(HttpServletRequest request);
 *       void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName);
 *  }
 *  SpringMVC中一套主题对应一个properties文件，里面存放着跟当前主题相关的所有资源，如图片、css样式表等，如：
 *    #theme.properties
 *    logo.pic=/images/default/logo.jpg
 *    logo.word=excelib
 *    style=/css/default/style.css
 *
 * SpringMVC跟主题有关的类主要有ThemeResolver、ThemeSource和Theme。
 *   1. ThemeResolver的作用是从request解析出主题名。
 *   2. ThemeSource的作用是根据主题名找到具体的主题。
 *   3. Theme是ThemeSource找出的一个具体的主题。
 *
 * 5、Spring MVC 九大组件之 MultipartResolver
 * 用于处理上传请求，处理方法时将普通的request包装成MultipartHttpServletRequest，
 * 后者可以直接调用getFile方法获取File，如果上传多个文件，
 * 还可以调用getFileMap得到FileName->File结构的Map，这样就使得上传请求的处理变得非常简单。
 * 然后，其实不包装直接用request也可以。所以SpringMVC中此组件没有提供默认值
 * public interface MultipartResolver {
 *     boolean isMultipart(HttpServletRequest request);//判断是不是上传请求
 *     MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException;//将request包装成MultipartHttpServletRequest
 *     void cleanupMultipart(MultipartHttpServletRequest request);//清除上传过程中产生的临时资源
 * }
 *
 * 6、Spring MVC 九大组件之FlashMapManager
 * FlashMap在之前的文章1中提到过了，主要用在redirect中传递参数。
 * 而FlashMapManager是用来管理FlashMap的，定义如下：
 *   public interface FlashMapManager {
 *      FlashMap retrieveAndUpdate(HttpServletRequest request, HttpServletResponse response);//恢复参数，将恢复过的和超时的参数从保存介质中删除；
 *      void saveOutputFlashMap(FlashMap flashMap, HttpServletRequest request, HttpServletResponse response);//用于将参数保存起来。
 *   }
 * 默认实现是org.springframework.web.servlet.support.SessionFlashMapManager。
 * 它将参数保存在session中，实现原理就是利用session作为中转站保存request中的参数，达到redirect传递参数的
 *
 * ...
 * 测试中未投入使用
 *
 * @author      1101399
 * @CreateDate  2018-3-12 上午10:39:06
 */
public class BeanNameViewResolver extends WebApplicationObjectSupport implements ViewResolver, Ordered {

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     *
     * 获取此对象的订单值。
     * <p>较高的值被解释为较低的优先级。 作为结果，
     * 具有最低值的对象具有最高的优先级（有些
     * 类似于Servlet {@code load-on-startup}值）。
     * <p>相同的顺序值将导致任意的排序位置
     * 受影响的对象。
     * @返回订单价值
     * 参见#HIGHEST_PRECEDENCE
     * 参见#LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Resolve the given view by name.
     * <p>Note: To allow for ViewResolver chaining, a ViewResolver should
     * return {@code null} if a view with the given name is not defined in it.
     * However, this is not required: Some ViewResolvers will always attempt
     * to build View objects with the given name, unable to return {@code null}
     * (rather throwing an exception when View creation failed).
     * @param viewName name of the view to resolve
     * @param locale Locale in which to resolve the view.
     * ViewResolvers that support internationalization should respect this.
     * @return the View object, or {@code null} if not found
     * (optional, to allow for ViewResolver chaining)
     * @throws Exception if the view cannot be resolved
     * (typically in case of problems creating an actual View object)
     *
     * 按名称解析给定的视图。
     * 注意：为了允许ViewResolver链接，ViewResolver应该
     * 如果没有定义给定名称的视图，则返回{@code null}。
     * 但是，这不是必需的：一些ViewResolver总是会尝试
     * 使用给定名称构建View对象，无法返回{@code null}
     *（当View创建失败时抛出异常）。
     * @param viewName要解析的视图名称
     * @参数locale用于解析视图的语言环境。
     * 支持国际化的ViewResolver应该尊重这一点。
     * @返回View对象，如果没有找到，则返回null
     *（可选，允许ViewResolver链接）
     * @throws异常，如果视图无法解析
     *（通常在创建实际View对象时出现问题）
     *
     */
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    /* XXX
     * WebApplicationContext继承ApplicationContext类
     *          ApplicationContext应用上下文，进行了很多扩展，它作为容器的高级形态存在
     *
                     在讲SpringMVC容器创建时介绍过WebApplicationContext是在FrameworkServlet中创建的，默认使用的是XmlWebApplicationContext，它的父类是AbstractRefreshableWebApplicationContext，这个类实现了ThemeSource接口，实现方式是在内部封装了一个ThemeSource属性，然后将具体工作交给它。
                     这里可以把ThemeSource理解成一个配置文件，默认使用的是WebApplicationContext。ThemeResolver默认使用的是FixedThemeResolver。这些我们可以自己配置，例如：
        <bean id="themeSource" class="org.springframework.ui.context.support.ResourceBundleThemeSource" p:basenamePrefix="com.excelib.themes."/>
        <bean id="themeResolver" class="org.springframework.web.servlet.theme.CookieThemeResolver" p:defaultThemeName="default"/>
                      这里配置了themeResolver,themeSource默认主题名为default，配置文件在comexcelib.themes包下。
                        切换Theme：同切换Locale相同，依然是使用Interceptor。配置如下：
                <mvc:interceptors>
                    <mvc:interceptor>
                        <mvc:mapping path="/" />
                        <bean class="org.springframework.web.servlet.theme.ThemeChangeInterceptor" p:paramName="theme"/>
                    </mvc:interceptor>
                </mvc:interceptors>
                    可以通过paramName设置修改主题的参数名，默认使用”theme”。下面的请求可以切换为summer主题。

    interface  ThemeResolver

    //org.springframework.web.servlet.support.RequestContext;
    public String getThemeMessage(String code, Object[] args, String defaultMessage) {
        return getTheme().getMessageSource().getMessage(code, args, defaultMessage, this.locale);
    }
    public Theme getTheme() {
        if (this.theme == null) {
            // Lazily determine theme to use for this RequestContext.
            this.theme = RequestContextUtils.getTheme(this.request);
            if (this.theme == null) {
                // No ThemeResolver and ThemeSource available -> try fallback.
                this.theme = getFallbackTheme();
            }
        }
        return this.theme;
    }
    //org.springframework.web.servlet.support.RequestContextUtils
    public static Theme getTheme(HttpServletRequest request) {
        ThemeResolver themeResolver = getThemeResolver(request);
        ThemeSource themeSource = getThemeSource(request);
        if (themeResolver != null && themeSource != null) {
            String themeName = themeResolver.resolveThemeName(request);
            return themeSource.getTheme(themeName);
        }
        else {
            return null;
        }
    }

     */
}
