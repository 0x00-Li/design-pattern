package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 懒汉模式
 * <p>
 * <p>
 * 通过简单实现，在需要的时候，进行对象的初始化，但是带来线程不安全的问题
 * <p>
 * 经典的dcl方案
 */
public class Singleton_06_lazy {
    /**
     * 此处必须有volatile 修饰，不然保证不了线程安全
     * 这里和具体的类的初始化过程相关
     *
     * 类在初始化的时候，先进行初始化，然后再调用init
     *
     * 在没有volatile 修饰的时候，可能发生指令重排，导致刚初始化完，就赋值，所以，if就会出现错误判断
     *
     */
    private static volatile Singleton_06_lazy singleton; // JIT


    private Singleton_06_lazy() {
    }

    public  static Singleton_06_lazy getInstance() {
        // 经典的dcl(Double check lock)
        if(singleton ==null){
            synchronized (Singleton_06_lazy.class) {
                if (singleton == null) {
                    singleton = new Singleton_06_lazy();
                }
            }
        }

        return singleton;
    }
}
