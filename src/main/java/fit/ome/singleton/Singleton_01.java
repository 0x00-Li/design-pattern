package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 饿汉模式，不管是否需求，直接先创建好了
 *
 *
 * 不需要过多思考，简单粗暴
 */
public class Singleton_01 {
    // 初始化，直接创建好
    private static Singleton_01 singleton_01 = new Singleton_01();

    private Singleton_01() {
    }

    public static Singleton_01 getInstance() {

        return singleton_01;
    }
}
