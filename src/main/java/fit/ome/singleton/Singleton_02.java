package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 饿汉模式，不管是否需求，直节通过代码块先创建好
 *
 *
 * 不需要过多思考，简单粗暴
 */
public class Singleton_02 {
    // 初始化，直接创建好
    private static Singleton_02 singleton_02;
    static {
        singleton_02=new Singleton_02();
    }

    private Singleton_02() {
    }

    public static Singleton_02 getInstance() {

        return singleton_02;
    }
}
