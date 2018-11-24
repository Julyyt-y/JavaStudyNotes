import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 知识点：类加载机制
 *
 * JVM（Java Virtual Machine Java虚拟机）和类：
 * 运行一个Java程序，该程序就会启动一个Java虚拟机进程，
 * 该Java程序中启动的所有线程都在改Java虚拟机进程中，他们都使用该JVM进程的内存区；
 * 当Java程序结束时，JVM进程结束，该进程在内存中的状态将会丢失；
 * 两个JVM之间不会共享数据。
 *
 * 类的加载（类初始化）：
 * 当程序主动使用某个类时，如果该类还没有被加载到内存中，
 * 则系统会通过加载、连接、初始化三个步骤来对该类进行初始化；
 * 类加载是指将类的class文件读入内存，并为之创建一个java.lang.Class对象；
 * 也就是说，当程序中使用任何类时，系统都会为之创建一个java.lang.Class对象；
 * 类本身也是一种对象，每个类是一批具有相同特征的对象的抽象（或者说概念）；
 * 而系统中所有的类实际上也是实例，他们都是java.lang.Class的实例。
 * 类加载由类加载器完成，类加载器通常由JVM提供，JVM提供的这些类加载器通常被称为系统类加载器；
 * 此外，开发者也可以通过继承ClassLoader基类来创建自己的类加载器；
 * 类加载器通常无需等到“首次使用”该类时才加载，Java虚拟机规范允许系统预先加载某些类。
 * 使用不同的类加载器从不同的来源加载类的二进制数据：
 *  1.从本地文件系统加载class文件（前面绝大部分示例的类加载方式）；
 *  2.从JAR包加载class文件；
 *  3.通过网络加载class文件；
 *  4.把一个Java源文件动态编译，并执行加载。
 *
 * 类连接：
 * 当类被加载之后，系统为之生成一个对应的Class文件，接着将会进入连接阶段，
 * 连接阶段负责把类的二进制数据合并到JAR包中，类连接分为三个阶段：
 *  1.验证：检测被加载的类是否有正确的内部结构；
 *  2.准备：负责为类的类变量分配内存，并设置默认初始值；
 *  3.解析：将类的二进制数据中的符号引用替换成直接引用。
 *
 * 类的初始化：
 * 在类的初始化阶段，虚拟机负责对类进行初始化，主要是对类变量进行初始化；
 * 在Java类中对类变量指定初始值有两种方式：
 *  1.声明类变量时指定初始值；
 *  2.使用静态初始化块时为类变量指定初始值；
 *  （这两种赋值方式执行的优先级是一样的，谁在前谁就先执行）。
 * JVM初始化一个类时有如下步骤：
 *  1.假如这个类还没有被加载和连接，则程序先加载并连接该类；
 *  2.假如这个类的直接父类还没有被初始化，则先初始化其直接父类；
 *  3.假如类中有初始语句，则系统依次执行这些初始化语句；
 * 执行2语句时，系统对该类的直接父类的初始化也按照这三步走，如果直接父类又有直接父类，则系统再重复再三步，
 * 所以JVM最先初始化的总是java.lang.Object类
 *
 * 类初始化的时机：
 * 当Java程序首次通过下面6种方式来使用某个类或接口时，系统就会初始化该类或接口：
 *  1.创建类的实例（使用new操作符来创建实例、通过反射来创建实例、通过反序列化的方式来创建实例）；
 *  2.调用某个类的类方法（类方法就是静态方法，静态方法属于整个类，不属于某个实例）；
 *  3.访问某个类或接口的类方法，或为该类变量赋值；
 *  4.使用反射方式来强制创建某个类的子类或接口对应的java.lang.Class对象；
 *  5.初始化某个类的子类（初始化某个类的子类时，该子类的所有父类都会被初始化）；
 *  6.直接使用java.exe命令来运行某个主类。
 * 【注】：1.对于一个final型类变量，如果该类变量的值在编译时期就确定了，则这个类变量相当于一个“宏变量”，
 *           Java编译器会在编译时期直接把这个类变量出现的地方替换成它的值，因此程序即使使用该静态类变量，也不会导致该类的初始化。
 *          （程序在其他地方使用该类变量时，实际上并没有使用该类变量，而是相当于使用常量）
 *          但是，如果final修饰的类变量的值不能在编译时期确定下来，则必须等到运行时才可以确定该类变量的值，
 *          如果通过该类来访问他的类变量，则会导致该类被初始化。
 *        2.当使用ClassLoader类的loadClass()方法来加载某个类时，该方法只是加载该类，并不会执行该类的初始化。
 *
 * 类加载器：
 * 类加载器负责将.class文件（可能在磁盘上，也可能在网络上）加载到内存中，并为之生成对应的java.lang.Class对象；
 * 在Java中，一个类用其全限定类名（包括类名和包名）作为标识，
 * 但在JVM中，一个类用其全限定类名和其类加载器作为其唯一标识；
 * JVM启动时，会形成由三个类加载器组成的初始类加载器层次结构：
 * 根类加载器（引导类加载器，不是java.lang.ClassLoader的子类）、扩展类加载器（加载JAR的扩展目录）、系统类加载器；
 *
 * 类加载机制：
 * JVM的类加载机制主要有三种：全盘负责、父类委托、缓存机制
 *   1.全盘负责：当一个类加载器负责加载某个Class时，该Class所依赖和引用的其他Class也将由该类加载器负责载入，
 *   除非显式使用另一个类加载器来载入；
 *   2.父类委托：先让parent（父）类加载器试图加载该Class，只有在父类加载器无法加载该类是才尝试从自己的类路径中加载该类；
 *   3.缓存机制：缓存机制将会保证所有加载过Class都会被缓存，当程序中需要使用某个Class时，
 *   类加载器会先从缓存区中搜寻该Class，只有当缓存区中不存在该Class时，系统才会读取该类对应的二进制数据，
 *   并将其转换成Class对象，存入缓存区中。
 * JVM的根类加载器并不是Java实现的，而且由于程序通常无须访问根类加载器，因此访问扩展类加载器的父类时返回null。
 * 类加载器加载Class大致经过8个步骤：
 *   1.检测此Class是否载入过（即缓存区中是否存在该Class），若有执行第8步，否则执行第2步；
 *   2.若父类加载器不存在，则执行第4步，否则执行第3步；
 *   3.请求使用父类加载器去载入目标类，成功载入则执行第8步，否则则执行第5步；
 *   4.请求使用根类加载器来载入目标类，成功载入则执行第8步，否则则执行第7步；
 *   5.当前类加载器尝试寻找Class文件，若找到则执行第6步，否则则执行第7步；
 *   6.从文件载入Class，成功载入后执行第8步；
 *   7.抛出ClassNotFoundException异常；
 *   返回对应的java.lang.Class对象。
 *
 * 创建并使用自定义的类加载器：
 * （Begin60.java）
 *
 * URLClassLoader类：
 * 该类是系统类加载器和扩展类加载器的父类（继承关系）；
 * URLClassLoader类功能比较强大，
 * 它既可以从本地文件系统获取二进制文件来加载类，也可以从远程主机获取二进制文件来加载类；
 * URLClassLoader类提供了两个构造器；
 * 得到了URLClassLoader对象之后，可以通过调用该对象的loadClass()方法来加载指定类。
 *
 */
public class Begin59 {
    //定义该类的类变量
    public static int aa = 6;

    //使用URLClassLoader类
    private static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //输出两个类变量赋值结果
        System.out.println(Test.a);
        System.out.println(Test.b);

        //访问、输出MyTest中的compileConstant类变量
        System.out.println(MyTest.compileConstant);

//        classLoaderTest();

//        BootstrapTest();

//        classLoaderPropTest();
    }

    public static void classLoaderTest() throws ClassNotFoundException{
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        cl.loadClass("ClassLoaderTest");    //此语句仅加载ClassLoaderTest类，并不处初始化该类
        System.out.println("系统加载ClassLoaderTest类");
        Class.forName("ClassLoaderTest");   //初始化ClassLoaderTest类
    }

    //获取根类加载器所在的全部URL数组

    /** 通过此程序的打印结果可以看出程序中可以使用String、System这些核心类库的原因：
        因为这些核心类库都在C:/Program%20Files/Java/jdk1.8.0_131/jre/lib/rt.jar文件中 */
    public static void BootstrapTest(){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();   //获取根类加载器所在的全部URL数组
        //遍历、输出根类加载器加载的全部URL
        for (int i = 0; i < urls.length; i++){
            System.out.println(urls[i].toExternalForm());
        }
    }

    //示范访问JVM的类加载器
    public static void classLoaderPropTest() throws IOException{
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();  //获取系统类加载器
        System.out.println("系统类加载器：" + systemLoader);
        // 获取系统类加载器的加载路径：通常由CLASSPATH环境变量指定
        //如果操作系统，没有指定CLASSPATH环境变量，则默认当前路径问系统类加载器的加载路径
        Enumeration<URL> eml = systemLoader.getResources("");
        while (eml.hasMoreElements()){
            System.out.println(eml.nextElement());
        }
        //获取系统类加载器的父类加载器，得到扩展；类加载器
        ClassLoader extensionLader = systemLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirt"));
        //父加载器是null，并不根类加载器，因为根类加载器并没有继承ClassLoader抽象类
        System.out.println("扩展类加载器的parent：" + extensionLader.getParent());
    }

    //定义一个获取数据库连接的方法
    public static Connection getConn(String ur,String user,String pass) throws Exception{
        if (conn == null){
            //创建一个URL数组
            URL[] urls = {new URL("file:mysql-connector-java-5.1.30-bin.jar")};
            //以默认的ClassLoader作为父ClassLoader，创建URLClassLoader
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            //加载MySQL的JDBC驱动，并创建默认实例
            Driver driver = (Driver)myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
            //创建一个设置JDBC连接属性的Properties对象
            Properties props = new Properties();
            //至少需要为该对象传入user和password两个属性
            props.getProperty("user",user);
            props.getProperty("password",pass);
            //调用Driver对象的connect方法来获得数据库的连接
            conn = driver.connect(String.valueOf(urls),props);
        }
        return conn;
    }

}

class AATest1{
    public static void main(String[] args){
        Begin59 a = new Begin59();    //创建AA类的实例
        a.aa ++; //让a实例的a变量的值自加
        System.out.println(a.aa);    //输出
    }
}
//先运行AATest1.main()，输出7，再运行AATest2.main()，输出6，可见两个JVM之间并不能共享数据。
class AATest2{
    public static void main(String[] args){
        Begin59 b = new Begin59();
        System.out.println(b.aa);
    }
}

//静态初始化块不会优先于类中其他语句执行，而知按他们在类中的顺序执行
//静态变量a先使用普通赋值语句赋值5，后被使用静态代码块赋值0，所以输出a = 0；
//静态变量b先使用静态代码块赋值为3，后使用普通赋值语句赋值6，所以输出b = 6；
class Test{
    static {
        b = 3;
    }
    static int a = 5;   //声明变量a时指定初始值
    static int b;
    //静态初始化块被当成类的初始语句
    static {
        //使用静态代码块为变量b指定初始值
        b = 6;
        a = 0;
    }
}

//展示final型的静态类变量不会导致类初始化
class MyTest{
    static {
        System.out.println("静态初始化块...");
    }
    //使用一个字符串直接量为static final的类变量赋值
    static final String compileConstant = "Julyyt";
}

//用于展示ClassLoader加载和初始化类
class ClassLoaderTest{
    static {
        System.out.println("ClassLoaderTest类的静态初始化块...");
    }
}