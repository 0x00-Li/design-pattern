package fit.ome.proxy;

import java.util.concurrent.TimeUnit;

/**
 * 场景设定
 * <p>
 * 假定在一个业务处理流程中，我们想要完成
 * 时间的记录
 * 日志的记录
 * 等等
 */


public class BizService implements Biz {

    @Override
    public void doBiz() {
        // 记录日志
        System.out.println("[log]start biz process");
        // 记录开始时间
        long start = System.currentTimeMillis();
        System.out.println("process biz....");
        try {
            // 执行业务逻辑进行随机耗时
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[delay]业务逻辑耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("[log]finish biz process");
    }

    public static void main(String[] args) {
        // 记录日志
        System.out.println("[log]start biz process");
        // 记录开始时间
        long start = System.currentTimeMillis();
        new BizService().doBiz();
    }
}

/**
 * 这是拙劣的表演
 * 5毛钱的特效
 * ----
 * 能忍吗？
 */
class BizService02 extends BizService {
    @Override
    public void doBiz() {
        // 记录日志
        // 记录时间
        super.doBiz();
    }
}

/**
 * 业务接口
 */
interface Biz {
    void doBiz();
}