package fit.ome.proxy.code_01;

import java.util.concurrent.TimeUnit;

/**
 * 场景设定
 * <p>
 * 假定在一个业务处理流程中，我们想要完成
 * 时间的记录
 * 日志的记录
 * 等等
 * =========
 * 来个削微优雅点的，搞个代理
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
        // 我艹，发现日志放哪呢？（：（：（： 略显不够扩展啊
        new BizTimeProxy(new BizService()).doBiz();
    }
}

class BizLogProxy implements Biz {
    BizService bizService;

    public BizLogProxy(BizService biz) {
        this.bizService = biz;
    }

    @Override
    public void doBiz() {
        // 记录日志
        System.out.println("[log]start biz process");
        bizService.doBiz();

        System.out.println("[log]finish biz process");
    }
}

class BizTimeProxy implements Biz {
    BizService bizService;

    public BizTimeProxy(BizService biz) {
        this.bizService = biz;
    }

    @Override
    public void doBiz() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        bizService.doBiz();

        System.out.println("[delay]业务逻辑耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}

/**
 * 业务接口
 */
interface Biz {
    void doBiz();
}