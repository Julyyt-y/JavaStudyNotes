import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *  知识点：反射——Executable抽象类、使用反射生成并操作对象（1）
 *
 *  Executable抽象类：
 *  该对象代代表可执行的的类成员，该类下派生了Constructor和Method两个子类；
 *  Constructor类提供了大量方法来获取修饰该方法或构造器的注解信息、
 *  判断该方法或构造器是否包含数量可变的形参、获取该方法和构造器的修饰符、
 *  获取该方法或参数的形参个数及形参名。
 *
 *  Method类：
 *  负责管理所有类的方法。
 *
 *  使用指定构造器创建Java对象：
 *  利用Constructor对象（每个Constructor对应一个对象），
 *  具体有以下三步：
 *   1.获取该类的Class对象；
 *   2.利用Class对象的getConstructor()方法来获取指定的构造器；
 *   3.调用Constructor的newInstance()方法来创建对象。
 *
 * 【注】：
 * Properties类继承自Hashtable类并且实现了Map接口，也是使用一种键值对的形式来保存属性集。
 * 不过Properties有特殊的地方，就是它的键和值都是字符串类型。
 *
 */
public class Begin62 {

    /**使用反射来生成并操作对象,实现一个简单的对象池*/
    //定义一个对象池，前面是对象名，后面是实际对象
    private Map<String,Object> objectPool = new HashMap<>();
    //定义一个创建对象的方法，该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        //根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        //使用clazz对应的类的默认构造器创建实例
        return clazz.newInstance();
    }
    //该方法根据指定文件来初始化对象池
    //它会根据配置文件来创建对象
    public void initPool(String fileName) throws
            IllegalAccessException,ClassNotFoundException,InstantiationException{
        try (FileInputStream fis = new FileInputStream(fileName)){
            Properties props = new Properties();
            props.load(fis);
            for (String name : props.stringPropertyNames()){
                //每取出一对key-value对，就根据value创建一个对象
                //调用createObject()创建对象，并将对象添加到对象池中
                objectPool.put(name,createObject(props.getProperty(name)));
            }
        }
        catch (IOException e){
            System.out.println("读取" + fileName + "异常");
        }
    }
    public Object getObject(String name){
        //从ObjectPool中取出指定name对应的对象
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception {
//        methodParameterTest();

//        Begin62 pf = new Begin62();
//        pf.initPool("J:\\obj.txt");
//        System.out.println(pf.getObject("a"));
//        System.out.println(pf.getObject("b"));

        //使用指定构造器创建Java对象
        //获取JFrame对应的Class对象,"javax.swing.JFrame"是一个类
        Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        //获取JFrame类的指定构造器，此处String表示想获取一个只有字符串参数的构造器
        Constructor ctor = jframeClazz.getConstructor(String.class);
        //调用Constructor的newInstance方法创建对象，传给newInstance()方法的参数将作为对应构造器的参数
        Object obj = ctor.newInstance("测试窗口");
        //输出JFrame对象
        System.out.println(obj);
    }

    //Executable抽象类的方法参数反射功能
    public static void methodParameterTest() throws Exception {
        Class<Test0> clazz = Test0.class;   //获取String类
        //获取String类的两个参数的replace()方法
        Method replace = clazz.getMethod("replace", String.class, List.class);
        System.out.println("replace方法参数个数：" + replace.getParameterCount());
        //获取replace的所有参数信息
        Parameter[] parameters = replace.getParameters();
        int index = 1;
        //遍历所有参数
        for (Parameter p : parameters) {
            if (p.isNamePresent()) {
                System.out.println("——第" + index++ + "个参数信息——");
                System.out.println("参数名：" + p.getName());
                System.out.println("形参类型：" + p.getType());
                System.out.println("泛型类型：" + p.getParameterizedType());
            }
        }
    }
}
class Test0{
    public void replace(String str, List<String> list){}
}