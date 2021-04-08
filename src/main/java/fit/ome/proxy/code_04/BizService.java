package fit.ome.proxy.code_04;

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
 * code_01 和code_02 都是提前编写好的代码，然后代理层层嵌套的（静态代理）
 * <p>
 * 考虑下动态代理
 */


public class BizService implements Biz {

    @Override
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
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        BizService biz = new BizService();
        Biz b = (Biz) Proxy.newProxyInstance(BizService.class.getClassLoader(),
                new Class[]{Biz.class},
                new BizLogHandler(biz));
        b.doBiz();
    }
}

/**
 * 发现又把实现强依赖回来了，并且发信啊了无处安置的时间打印需求
 * :) 怎么办？
 */
class BizLogHandler implements InvocationHandler {
    // 又把BizService 依赖回来了
    Biz biz;

    public BizLogHandler(Biz biz) {
        this.biz
                = biz;
    }

    public void before() {
        System.out.println("before method....");
        System.out.println("[log]start biz process");
    }

    public void after() {
        System.out.println("[log]finish biz process");
        System.out.println("after method...");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 记录日志

        before();
        Object invoke = method.invoke(biz, args);
        after();

        return invoke;
    }
}

class BizTimeHandler implements InvocationHandler {
    // 又把BizService 依赖回来了
    BizService biz;

    public BizTimeHandler(BizService biz) {
        this.biz
                = biz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object invoke = method.invoke(biz, args);

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