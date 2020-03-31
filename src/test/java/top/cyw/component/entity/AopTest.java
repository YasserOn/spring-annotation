package top.cyw.component.entity;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.cyw.aop.config.MainConfigAOP;
import top.cyw.aop.service.CalculatorService;

public class AopTest {


    /**【AOP原理】：看给容器注册了什么组件 而这个组件又有什么作用！--->  这个组件什么时候工作、这个组件工作的功能
     * 1、
     * @EnableAspectJAutoProxy 是什么呢
     *      @Import(AspectJAutoProxyRegistrar.class)：给spring容器注册了AspectJAutoProxyRegistrar注册
     *             >1、利用 AspectJAutoProxyRegistrar 注册了
     *                  key : value = internalAutoProxyCreator : AnnotationAwareAspectJAutoProxyCreator对象
     *             >2、获取到@EnableAspectJAutoProxy注解的属性，是否为true  如果为true再做一些工作
     *
     * 2、AnnotationAwareAspectJAutoProxyCreator 这个组件有什么作用呢？
     *      看到@EnableXxx注解--->看他到底注册那个bean --->思考这个bean又有什么作用呢
     *          看继承的父类 + 实现的接口
     *              AnnotationAwareAspectJAutoProxyCreator
     *                  ->AspectJAwareAdvisorAutoProxyCreator
     *                      ->AbstractAdvisorAutoProxyCreator
     *                          ->AbstractAutoProxyCreator
     *                              ->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
     *                                  关注后置处理器 ( 在bean初始化前后完成了什么工作 )
     *                                  自动装配了 BeanFactory
     *
     * 3、从上到下进行分析！  并关注 bean后置处理器 和 BeanFactory 到底完成了什么工作  关注重写的方法!
     *          AbstractAutoProxyCreator: 以下重写方法
     *              public void setBeanFactory(BeanFactory beanFactory)
     *              public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
     *              public Object postProcessAfterInitialization(@Nullable Object bean, String beanName)
     *
     *          AbstractAdvisorAutoProxyCreator:
     *              重写！
     *              public void setBeanFactory(BeanFactory beanFactory) --> initBeanFactory((ConfigurableListableBeanFactory) beanFactory);
     *
     *          AspectJAwareAdvisorAutoProxyCreator:
     *              无
     *          AnnotationAwareAspectJAutoProxyCreator:
     *              重写 initBeanFactory
     *              protected void initBeanFactory(ConfigurableListableBeanFactory beanFactory)
     *
     *          在MainConfigAOP配置类中 给两个@Bean注册方法上打上断点
     *
     *
     *
     */

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigAOP.class);
//        因为是我们自己new的对象，而不是spring帮我们管理的bean
//        CalculatorService service = new CalculatorService();
//        service.div(2,1);
        /**  从spring容器中取出的bean 执行方法的时候 就被aop切到了 */
        CalculatorService service = context.getBean(CalculatorService.class);
        service.div(2, 1);
    }

    /**
     * aop创建流程
     *  1、传入配置类，创建ioc容器
     *
     *  public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
     * 		this();
     * 		register(annotatedClasses);
     * 		refresh();
     *}
     *  2、 注册配置类，调用 refresh() 刷新容器
     *  3、refresh刷新容器 -> 创建容器中所有的bean  --> 相当于初始化容器
     *
     *      registerBeanPostProcessors(beanFactory); //其中有一步注册后置处理器 来拦截bean的创建
     *          1）获取ioc容器中已经定义了的后置处理器，【并未创建，只是创建定义信息！】
     *          String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
     *          2) 添加了一个额外的 后置处理器
     *          beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));
     *          3) 区分 后置处理器 优先级  实现了 PriorityOrdered,Ordered,以及原生的后置处理器
     *          // Separate between BeanPostProcessors that implement PriorityOrdered,
 * 		        // Ordered, and the rest.
     * 		        优先注册  实现PriorityOrdered接口的后置处理器
     * 		        再注册    实现Ordered接口的后置处理器
     * 		            AnnotationAwareAspectJAutoProxyCreator 实现了 Order 接口
     * 		                想要getBean 但是第一次是获取不到的
     * 		        最后注册  没有实现优先级接口的后置处理器
     *
     * 		    4)获取不到，就注册  注册 BeanPostProcessor --> 创建 BeanPostProcessor 对象，保存在容器中
     * 		            创建 internalAutoProxyCreator : AnnotationAwareAspectJAutoProxyCreator
     * 		                a、创建bean实例
     * 		                b、给 bean的各种属性赋值 populateBean(beanName, mbd, instanceWrapper);
     * 		                c、初始化 bean initializeBean(beanName, exposedObject, mbd);
     * 		                    1)、invokeAwareMethods(beanName, bean) : Aware接口的赋值  Aware接口有啥用？
     * 		                            补充：
     * 		                                调用到了 AbstractAdvisorAutoProxyCreator
     * 		                                        public void setBeanFactory(BeanFactory beanFactory)
     * 		                                            调用到了 AnnotationAwareAspectJAutoProxyCreator 的 initBeanFactory
     *
     * 		                    2)、wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
     * 		                        调用 所有 后置处理器的 postProcessBeforeInitialization 方法
     * 		                            如果 有一个后置处理器的 postProcessBeforeInitialization 方法 返回值为null则直接返回
     * 		                    3)、执行 invokeInitMethods :执行自定义初始化方法
     *                                  a、@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
     *                                  b、实现初始化和销毁接口 InitializingBean、DisposableBean
     *                                  c、@PostConstruct、@PreDestroy  标注初始化和销毁时需要执行的方法。
     *                                  d、实现接口 BeanPostProcessor，来在 Bean 初始化完成前后执行操作
     *                          4)、执行 wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
     *                              调用 所有 后置处理器的 postProcessAfterInitialization 方法
     *
     *                       d、最终构建出
     *                          internalAutoProxyCreator : AnnotationAwareAspectJAutoProxyCreator
     *                              --> aspectJAdvisorsBuilder  aspectJAdvisorFactory
     *          5)、把 BeanPostProcessor 注册到 BeanFactory中:
     *                  beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
     *
     *======================以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程=============================================
     *
     */

}
