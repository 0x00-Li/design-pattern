package fit.ome.singleton;

/**
 * 单例的简单实现
 * <br>
 * 不仅可以解决线程同步问题，还可以防止反射访问
 * 
 */
public enum Singleton_08_lazy {


    singleton;
public String test(){
    return "a";

}

    public static void main(String[] args) {
        System.out.println(Singleton_08_lazy.singleton.test());
    }
}
