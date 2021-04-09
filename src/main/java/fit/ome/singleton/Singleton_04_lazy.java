package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 懒汉模式
 * <p>
 * <p>
 * 通过简单实现，在需要的时候，进行对象的初始化，但是带来线程不安全的问题
 * <p>
 * 通过synchronized 保证线程安全，但是性能低下
 */
public class Singleton_04_lazy {
    // 初始化，直接创建好
    private static Singleton_04_lazy singleton_02;


    private Singleton_04_lazy() {
    }

    public synchronized static Singleton_04_lazy getInstance() {
        if (singleton_02 == null) {
            singleton_02 = new Singleton_04_lazy();
        }
        return singleton_02;
    }
}
