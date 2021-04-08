package fit.ome.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * 场景设定
 * <p>
 * 假定在一个业务处理流程中，我们想要完成
 * 时间的记录
 * 日志的记录
 * 等等
 * =========
 * <p>
 * 考虑下动态代理,我们基于cglib来实现动态代理，需要增加依赖
 *
 *
 * <dependency>
 * <groupId>cglib</groupId>
 * <artifactId>cglib</artifactId>
 * <version>3.2.12</version>
 * </dependency>
 */


public class BizService {


    public void doBiz() {


        System.out.println("process biz....");
        try {
            // 执行业务逻辑进行随机耗时
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BizService.class);
        // 多个call back 和filter需要进行配合使用，进行制定方法进行过滤
//        enhancer.setCallbackFilter();
//        enhancer.setCallbacks(new Callback[]{new BizLogInterceptor(), new BizTimeInterceptor()});
        enhancer.setCallback(new BizLogInterceptor());
        BizService o = (BizService) enhancer.create();
        o.doBiz();

    }
}

/**
 * 发现又把实现强依赖回来了，并且发信啊了无处安置的时间打印需求
 * :) 怎么办？
 */
class BizLogInterceptor implements MethodInterceptor {

    public void before() {
        System.out.println("before method....");
        System.out.println("[log]start biz process");
    }

    public void after() {
        System.out.println("[log]finish biz process");
        System.out.println("after method...");
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object invoke = methodProxy.invokeSuper(o, args);// method.invoke(o, args);
        after();
        return invoke;
    }
}

class BizTimeInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object invoke = methodProxy.invokeSuper(o, args);
        ;
        System.out.println("[delay]业务逻辑耗时：" + (System.currentTimeMillis() - start) + "ms");
        return invoke;
    }
}


/**
 * 业务接口
 */
interface Biz {
    void doBiz();
}