package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 懒汉模式
 * <p>
 * <p>
 * 通过简单实现，在需要的时候，进行对象的初始化，但是带来线程不安全的问题
 * <p>
 * 通过synchronized 锁定代码块保证线程安全，但是性能低下
 */
public class Singleton_05_lazy {
    // 初始化，直接创建好
    private static Singleton_05_lazy singleton_02;


    private Singleton_05_lazy() {
    }

    public  static Singleton_05_lazy getInstance() {
        // synchronized 加在if内部和外部有差异
        // if 外，保证线程安全，但是性能低下
        // if 里，保证不了线程安全? 其实不是下一个实现方式在分析
        synchronized (Singleton_05_lazy.class) {
            if (singleton_02 == null) {
                singleton_02 = new Singleton_05_lazy();
            }
        }
        return singleton_02;
    }
}
