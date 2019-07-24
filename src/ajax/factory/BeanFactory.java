package ajax.factory;
import java.util.ResourceBundle;

public class BeanFactory {

    private static ResourceBundle bundle;
    static {
        bundle=ResourceBundle.getBundle("instance");
    }

    /**
     * 根据提供的Key和class去创建对应的对象
     * @param <T>
     * @return
     */
    public static <T>T getInstance(String key,Class<T>classType){
        String className=bundle.getString(key);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
