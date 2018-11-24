import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 知识点：使用反射生成并操作对象（2）
 *
 * 调用方法：
 * 当获得某个类对应的Class对象后，
 * 就可以通过该Class对象的getMethod()方法和getMethods()方法来获取指定方法或全部方法，
 * 这两个方法的返回值是Method数组或Method对象。
 *
 * 访问成员变量：
 * 通过Class对象的getFields()方法或getField()方法可以获取该类所包括的全部成员变量或指定成员变量；
 * Field提供了如下两组方法来读取或设置成员变量：
 * getXxx(Object obj)：获取obj对象的该成员变量的值；
 * setXxx(Object obj,Xxx val)：将obj对象的该成员变量设置成val值。
 * getDeclaredField()获取所有成员变量的值。
 *
 *
 * 【注】：
 * Properties类继承自Hashtable类并且实现了Map接口，也是使用一种键值对的形式来保存属性集。
 * 不过Properties有特殊的地方，就是它的键和值都是字符串类型。
 */

public class Begin63 {
    public static void main(String[] args) throws Exception{
//        Begin63 epf = new Begin63();
//        epf.init("J:\\obj.txt");
//        epf.initPool();
//        epf.initProperty();
//        System.out.println(epf.getObject("a"));
//
        /** 访问成员变量值。通过反射修改Person对象的name、age两个成员变量的值*/
        Person p = new Person();//创建一个Person对象
        Class<Person> personClazz = Person.class;//获取Person类对应的Class对象
        //获取Person的名为name的成员变量
        //使用getDeclaredField()方法表明可以获取各种访问控制符的成员变量
        Field nameField = personClazz.getDeclaredField("name");//获取名为name的成员变量
        nameField.setAccessible(true);  //设置通过反射访问该成员变量时取消访问权限检查
        nameField.set(p,"Julyyt"); //调用set()方法为p对象的name成员变量设置值
        Field ageField = personClazz.getDeclaredField("age");
        ageField.setAccessible(true);   //设置通过反射访问该成员变量时取消访问权限检查
        ageField.setInt(p,19);  //调用setInt()方法为p对象的age成员变量设置值
        System.out.println(p);

        arrayTest1();

        arrayTest2();
    }

    /**对对象池进行加强，允许在配置文件中增加配置对象的成员变量的值，
     对象池工厂会读取为该对象配置的成员变量值，并利用该对象对应的setter方法设置成员变量的值*/
    //定义一个对象池，前面是对象名，后面是实际对象
    private Map<String,Object> objectPool = new HashMap<>();
    private Properties config = new Properties();
    //从指定属性文件中初始化Properties对象
    public void init(String filename){
        try(FileInputStream fis = new FileInputStream(filename)){
            config.load(fis);
        }
        catch (IOException e){
            System.out.println("读取" + filename + "异常");
        }
    }
    //定义一个创建对象的方法
    //该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws
            InstantiationException,IllegalAccessException,ClassNotFoundException{
        //根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        //使用clazz对应类的默认构造器创建实例
        return clazz.newInstance();
    }
    //根据指定文件来初始化对象池，它会根据配置文件来创建对象
    public void initPool() throws
            InstantiationException,IllegalAccessException,ClassNotFoundException{
        for (String name : config.stringPropertyNames()){
            //每取出一对key-value对，如果key中不包含百分号（%）
            //这就表明是根据value来创建一个对象
            //调用createObject()创建对象，并将对象添加到对象池中
            if (!name.contains("%")){
                objectPool.put(name,createObject(config.getProperty(name)));
            }
        }
    }
    //该方法将会根据属性文件来调用对象的setter方法
    public void initProperty() throws
            InvocationTargetException,IllegalAccessException,NoSuchMethodException{
        for (String name : config.stringPropertyNames()){
            //每取出一对key-value对，如果key中不包含百分号（%）
            //即可认为该key用于控制调用对象的setter方法设置
            //%前半段为对象名字，后半段控制setter方法名
            if (name.contains("%")){
                String[] objAndProp = name.split("%");  //将配置文件中的key按%分割
                Object target = getObject(objAndProp[0]);  //取出调用setter方法的参数值
                //获取setter方法名：set + "首字母大写" + 剩下部分
                String mtdName = "set" + objAndProp[1].substring(0,1).toUpperCase()
                        + objAndProp[1].substring(1);
                //通过target的getClass()获取它的实现类所对应的Class对象
                Class<?> targetClass = target.getClass();
                Method mtd = targetClass.getMethod(mtdName,String.class);//获取希望调用的setter方法
                //通过Method的invoke方法执行setter方法
                //将config.getProperty(name)的值作为调用setter方法的参数
                mtd.invoke(target,config.getProperty(name));
            }
        }
    }
    public Object getObject(String name){
        return objectPool.get(name);//从objectPool中取出指定name对应的对象
    }

    public static void arrayTest1(){
        try {
            //创建一个元素类型为String，长度为10的数组
            Object arr = Array.newInstance(String.class,10);
            //依次为arr数组中index为5,6的元素赋值
            Array.set(arr,5,"Java01");
            Array.set(arr,6,"Java02");
            //依次取出arr数组中index为5,6元素的值
            Object obj1 = Array.get(arr,5);
            Object obj2 = Array.get(arr,6);
            //输出arr数组中index为5,6元素的值
            System.out.println(obj1);
            System.out.println(obj2);
        }catch (Throwable e){
            System.out.println(e);
        }
    }

    //使用Array创建一个三维数组
    public static void arrayTest2(){
        //创建一个三维数组，三维数组其实也是一维数组
        //是数组元素是二维数组的一维数组，因此可认为arr数长度为3的一位数组
        Object arr = Array.newInstance(String.class,3,4,10);
        Object arrObj = Array.get(arr,2);//获取arr数组中index为2的元素，该元素是二维数组
        //使用Array为二维数组的数组元素赋值，二维数组的数组元素是一维数组
        //所以传入Array的set()方法的第三个参数是一维数组
        Array.set(arrObj,2,new String[]{"Java001" ,"Java002"});
        //获取arrObj数组中index为3的元素该元素应该是一维数组
        Object anArr = Array.get(arrObj,3);
        Array.set(anArr,8,"Java");
        String[][][] cast = (String[][][])arr;  //将arr强制转换为三位数组
        //获取cast三维数组中指定元素的值
        System.out.println(cast[2][3][8]);
        System.out.println(cast[2][2][0]);
        System.out.println(cast[2][2][1]);
    }
}

class Person{
    private String name;
    private int age;
    @Override
    public String toString() {
        return "Person[name:" + name + ",age:" + age + "]";
    }
}
