package fit.ome.proxy.code_02;

import java.util.concurrent.TimeUnit;

/**
 * 场景设定
 * <p>
 * 假定在一个业务处理流程中，我们想要完成
 * 时间的记录
 * 日志的记录
 * 等等
 * =========
 * code_01 没解决，日志打印问题，再来一个
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
// 能满足基本需求，但是记录的时间包含了日志的时间
//        new BizTimeProxy(new BizLogProxy(new BizService())).doBiz();
        // 换下位置；看样子是perfect 了
        new BizLogProxy(new BizTimeProxy(new BizService())).doBiz();
    }
}

class BizLogProxy implements Biz {
    // 抽象画接口
    Biz biz;

    public BizLogProxy(Biz biz) {
        this.biz = biz;
    }

    @Override
    public void doBiz() {
        // 记录日志
        System.out.println("[log]start biz process");
        biz.doBiz();

        System.out.println("[log]finish biz process");
    }
}

class BizTimeProxy implements Biz {
    Biz biz;

    public BizTimeProxy(Biz biz) {
        this.biz = biz;
    }

    @Override
    public void doBiz() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        biz.doBiz();

        System.out.println("[delay]业务逻辑耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}

/**
 * 业务接口
 */
interface Biz {
    void doBiz();
}