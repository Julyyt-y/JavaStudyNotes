import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 知识点：Java反射
 * 反射是框架设计的灵魂。反射就是把Java类中的各种成分映射成一个个的Java对象。
 * Java反射是在运行状态中，对于任意一个类，都能都知道这个类的所有属性和方法；
 * 对于任意一个对象，都能都调用它的任意一个方法和属性；
 * 这种动态获取的信息及动态调用对象的方法的功能称为Java的反射机制。
 * 即反射的本质就是得到class对象后反向获取某个类的对象的各种信息
 * （构造器、形参、方法、内外部类注解等等）。
 *
 * 通过反射来查看类信息：
 * 反射出现的原因：
 * 为解决某个变量在编译时期和运行时期类型不一致，和程序在运行时接收到一个外部传入的Object类型的对象；
 * 所以需要通过反射发现类和对象的真实信息。
 * 具体做法：
 *   1.如果在编译和运行时期都完全知道类型的具体信息，可以先使用instanceof运算符进行判断，
 *   再利用强制类型转换将其转换成其运行时类型的变量即可；
 *   2.如果根本无法预知该对象和类可能属于哪些类，程序只能依靠运行时信息来发现该对象的真实信息，
 *   这就必须使用反射。
 * 前面已经说过，每个类被加载后，系统都会为该类生成一个对应的Class对象；
 * 所以，我们要通过反射来获得某Class对象所对应的类的信息时，就必须先获得该类的Class对象。
 * 获得Class对象的三个方法：
 *   1.使用Class类的forName()静态方法；
 *   2.调用某个类的class属性来获取该类对应的Class对象，比如Person.class将会返回Person类对应的Class对象
 *   （第二种方法的代码更安全，程序性能更好）；
 *   3.调用某个对象的getClass()方法。
 * 具体的步骤见下面代码。
 *
 * 对应的类的信息都有：
 * 构造器、方法、成员变量、注释、内部类、外部类、该类所实现的接口、该类所继承的父类、
 * 该类的修饰符、所在包、类名、判断该类是否为接口、枚举、注解类型等。
 *
 */

//定义可重复注解
@Repeatable(Annos.class)
@interface Anno {}
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    Anno[] value();
}
//使用4个注解修饰该类
@SuppressWarnings(value = "unchecked")
@Deprecated
//使用重复注解修饰该类
@Anno
@Anno
public class Begin61 {
    //为该类定义一个私有构造器
    private Begin61(){ }
    //定义一个有参数的构造器
    public Begin61(String name){
        System.out.println("执行有参数的构造器");
    }
    //定义一个无参数的info方法
    public void info(){
        System.out.println("执行无参数的info方法");
    }
    //定义一个有参数的info方法
    public void info(String str){
        System.out.println("执行有参数的info方法" + "，其参数值：" + str);
    }
    //定义一个测试用的内部类
    class Inner{ }
    public static void main(String[] args) throws Exception{
        Class<Begin61> clazz = Begin61.class;   //获取Begin61对应的Class
        Constructor[] ctors = clazz.getConstructors();  //获取该Class对象所对应类的全部构造器
        System.out.println("Begin61类所对应的全部构造器如下");
        for (Constructor c : ctors){
            System.out.println(c);
        }
        Method[] mtds = clazz.getMethods(); //获取该Class对象所对应的全部public方法
        System.out.println("Begin61类所对应的全部public方法如下：");
        for (Method md : mtds){
            System.out.println(md);
        }
        //获取该Class对象所对应类的指定方法
        System.out.println("Begin61类里带一个字符串参数的info方法为：" +
                clazz.getMethod("info",String.class));
        Annotation[] anns = clazz.getAnnotations(); //获取该Class对象所对应类的全部注解
        System.out.println("Begin61的全部Annotation如下：");
        for (Annotation an : anns){
            System.out.println(an);
        }
        System.out.println("该Class元素上的@SuppressWarnings注解为："
                + Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
        System.out.println("该Class元素上的@Anno注解为："
                + Arrays.toString(clazz.getAnnotationsByType(Anno.class)));
        Class<?>[] inners = clazz.getDeclaredClasses(); //获取该Class对象所对应类的全部内部类
        System.out.println("Begin61的全部内部类如下：");
        for (Class c : inners){
            System.out.println(c);
        }
        //使用Class.forName()方法加载Begin61的Inner内部类
        Class inClazz =  Class.forName("Begin61$Inner");
        //通过getDeclaringClass()访问该了所在的外部类
        System.out.println("inClass对应的外部类为：" + inClazz.getDeclaringClass());
        System.out.println("Begin61的包为：" + clazz.getPackage());
        System.out.println("Begin61的父类为：" + clazz.getSuperclass());
    }
}
