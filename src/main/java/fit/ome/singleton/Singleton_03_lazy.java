package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 懒汉模式
 * <p>
 * <p>
 * 通过简单实现，在需要的时候，进行对象的初始化，但是带来线程不安全的问题
 */
public class Singleton_03_lazy {
    // 初始化，直接创建好
    private static Singleton_03_lazy singleton_02;


    private Singleton_03_lazy() {
    }

    public static Singleton_03_lazy getInstance() {
        if (singleton_02 == null) {
            singleton_02 = new Singleton_03_lazy();
        }
        return singleton_02;
    }
}
