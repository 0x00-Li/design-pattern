package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 懒汉模式
 * <p>
 * <p>
 * 通过简单实现，在需要的时候，进行对象的初始化，但是带来线程不安全的问题
 * <p>
 * 通过静态内部类实现
 *
 * jvm 保证单例
 * 加载外部类不会导致内部类加载
 *
 */
public class Singleton_07_lazy {


    private static class Inner {
        private final static Singleton_07_lazy singleton = new Singleton_07_lazy();
    }

    private Singleton_07_lazy() {
    }

    public static Singleton_07_lazy getInstance() {


        return Inner.singleton;
    }
}
