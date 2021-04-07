package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 *     懒汉模式
 *     不支持并发
 *
 */
public class Singleton_01 {
    private static Singleton_01 code01;

    public static Singleton_01 getInstance() {
        if (code01 == null) {
            code01 = new Singleton_01();
        }
        return code01;
    }
}
