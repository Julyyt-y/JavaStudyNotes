import javax.swing.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

/**
 * 反射——使用泛型来限制Class类
 *
 * String.class的类型实际上是Class<String>；
 * 如果Class对应的类暂时未知，则使用Class<?>；
 * 通过在反射中使用泛型，可以避免使用反射生成的对象需要强制转换。
 */
//下面代码提供一个简单的对象工厂，该对象工厂可以根据指定类来提供该类的实例
public class Begin64 {

    private Map<String,Integer> score;

    public static void main(String[] args) throws Exception{

//        //获取实例后无须类型转换
//        Date d = Begin64.getInstance(Date.class);
//        JFrame f = Begin64.getInstance(JFrame.class);

        //使用Begin64的newInstance()创建一维数组
        String[] arr = Begin64.newInstance(String.class,10);
        //使用Begin64的newInstance()创建二维数组，在这种情况下只要设置数组元素的类型是int[]即可
        int[][] intArr = Begin64.newInstance(int[].class,5);
        arr[5] = "Java";
        //intArr是二维数组，初始化该数组的第二个数组元素，二维数组的元素必须是一维数组
        intArr[1] = new int[]{23,12};
        System.out.println(arr[5]);
        System.out.println(intArr[1][1]);

        //使用反射来获取泛型信息
        genericTest();

    }

    public static<T> T getInstance(Class<T> cls){
        try{
            return cls.newInstance();//返回使用该Class对象创建的实例
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //为示范泛型的优势，可以对Array的newInstance()方法进行包装
    public static <T> T[] newInstance(Class<T> componentType,int length){
        return (T[])Array.newInstance(componentType,length);
    }

    //使用反射来获取泛型信息
    public static void genericTest() throws Exception{
        Class<Begin64> clazz = Begin64.class;
        Field f = clazz.getDeclaredField("score");
        Class<?> a = f.getType(); //直接使用getType()取出类型只对普通类型的成员变量有效
        System.out.println("score的类型是：" + a); //输出java.util.Map
        Type gType = f.getGenericType(); //获得成员变量f的泛型类型
        //如果gType类型是ParameterizedType对象
        if (gType instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType)gType; //强制类型转换
            Type rType = pType.getRawType(); //获取原始类型
            System.out.println("原始类型是：" + rType);
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型信息是：");
            for (int i = 0; i < tArgs.length; i++){
                System.out.println("第" + i + "个泛型信息是" + tArgs);
            }
        }else {
            System.out.println("获取泛型信息出错！");
        }
    }
}
