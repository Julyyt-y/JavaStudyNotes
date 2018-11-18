import java.util.*;

/**
 * 知识点：泛型
 *
 * 泛型：JDK1.5版本以后出现的新特性，用于解决安全问题，是一个类型安全机制
 * 泛型其实就是允许程序在创建集合时指定该集合中元素的类型
 * 把一个对象“丢进”集合里，集合就会“忘记这个对象的数据类型，再次取出该对象时，该对象的编译类型就变成了Object类型
 * 而泛型的出现，正事为了解决该问题
 * 具体有：泛型类、泛型接口、类型通配符、泛型方法
 *
 * 好处：
 * 1.将运行时期出现的问题ClassCaseException转移到了编译时期
 * 2.避免了强制转换的麻烦
 *
 * 泛型格式：通过< > 来定义
 *
 * 在使用Java提供的对象时，什么时候写泛型呢？
 * 通常在集合框架中很常见，只要见到 <> 就要定义泛型
 * 其实 <> 就是用来接收类型的
 * 当使用集合时，将集合中要存储的数据类型作为参数传递到 <> 中即可
 *
 * 什么时候定义泛型类：
 * 当类中要操作的引用数据类型不确定的时候，早期定义Object来完成扩展，
 * 现在定义泛型来完成扩展
 *
 * 泛型类定义的泛型，在整个类中有效，如果方法被使用，那么泛型类的对象明确要操作的具体类型后，
 * 所有要操作的类型就已经固定了。
 * 为了让不同的方法可以操作不同的类型，而且类型还不确定，
 * 那么可以将泛型定义在方法上
 *
 * 泛型的实质：
 * 允许在定义接口、类时声明类型形参，
 * 类型形参是在整个接口、类体内可当成类型使用，
 * 几乎所有课使用普通类型的地方都可以使用这种类型形参
 *
 * 特殊：
 * 静态方法不可以访问类上定义的泛型，如果静态方法的应用的数据类型不确定，可以将泛型定义在方法上
 *
 * 【注】：Java允许在构造器后不需要完整的泛型信息只给出一对尖括号<>即可，
 *         如：List<String> str = new ArrayList<>;这种语法被称为泛型的“菱形”语法
 *         String是的子类型，但List<String>并不是<Object>的子类
 */
public class Begin47 {
    public static void main(String[] args){

//        method_1();

        method_2();

//        method_4();

//        method_5();

//        //此段代码会报错，因为只希望该集合保存字符串，而程序不小心把一个Integer对象丢进和集合里
//        //导致抛出ClassCastException异常
//        List strList = new ArrayList();
//        strList.add("Java");
//        strList.add("Android");
//        strList.add(5); //
//        strList.forEach(str->System.out.println(((String)str).length()));

        //上段代码的改进
        //在创建strList1集合时指定该集合中元素的类型只能是String型的
        //接口  对象 = new 类
        List<String> strList1 = new ArrayList<>();
        List<Integer> strList2 = new ArrayList<>();
//        List<String> strList1 = new ArrayList<String>(); //上一条语句的原型
        System.out.println(strList1.getClass() == strList2.getClass());     //打印true
        //输出true表明不论泛型的实际类型参数是什么，他们在运行时总有相同的类（class）
        strList1.add("Java");
        strList1.add("Android");
        strList1.add("python");
//        strList1.add(5); //编译时期会发生错误（报红）
        strList1.forEach(str->System.out.println(((String)str).length()));//打印出集合里每个对象的长度
    }

    public static void method_5(){
        Demo9 d = new Demo9();
        d.show1("haha");
        d.show1(new String("Java666"));
        d.show1(new Integer(4));
        d.print("heihei");
        d.print(111);
        d.print(new Integer(123));
    }

    public static void method_4(){

//        Tool t = new Tool();
//        t.setObject(new Worker());
//        Worker w = (Worker)t.getObject();

        Utils<Worker> u = new Utils<Worker>();
    }

    public static void method_2(){

        TreeSet<String> ts = new TreeSet<String>(new LenComparetor());

        ts.add("abcd");
        ts.add("cc");
        ts.add("cba");
        ts.add("aaa");
        ts.add("z");
        ts.add("hahaha");

        Iterator<String> it = ts.iterator();

        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }
    }

    public static void method_1(){

        //意思：定义一个名为ArrayList的String类型的容器
        ArrayList<String> al = new ArrayList<String>();  //泛型

        al.add("abc01");
        al.add("abc0991");
        al.add("abc014");

        Iterator<String> it = al.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s+";"+s.length());
        }
    }
}

class Worker{

}

//class Tool{  //设计这个类来完成程序扩展，泛型前做法
//    private Object obj;
//    public void setObject(Object obj){
//        this.obj = obj;
//    }
//    public Object getObject() {
//        return obj;
//    }
//}

//定义泛型类（这表明并不是只有集合类才可是使用泛型声明，其他类也可声明泛型）
class Utils<QQ>{
    //几乎所有使用普通类型的地方都可以使用这种类型形参（此处是在定义类时声明的类型形参QQ）
    private QQ q;
    public void setObject(QQ q){

        this.q = q;
    }
    public QQ getObject() {

        return this.q;
        //或return q;
    }
    //使用T类型形参来定义构造器
    //定义构造器时不能使用Utils<QQ>,只能使用Utils，调用该构造器时可是使用Utils<QQ>
    Utils utils(QQ q) {
        this.q = q;
        //此处返回值类型必须是Utils<QQ>
        return (Utils<QQ>) q;
    }
}
//A11类继承Utils类
class A11 extends Utils<String>{
    //继承父类方法
    public String getObject(){
        return "子类" + super.getObject();
    }
}


//class LenComparetor implements Comparator{
//    public int compare(Object o1,Object o2){
//        String s1 = (String)o1;
//        String s2 = (String)o2;
//    }
//}
//或
class LenComparetor implements Comparator<String>{
    public int compare(String o1,String o2) {
        int num = new Integer(o1.length()).compareTo(new Integer(o2.length()));

        if (num == 0) {
            return o1.compareTo(o2);
        }
        return num;
    }
}

//将泛型定义在方法上（定义泛型类）
class Demo9<T>{
    //使用T类型形参来定义方法（函数）
    public <T> void show1(T t){

        System.out.println("show:"+t);
    }
    //使用Q类型形参来定义方法（函数）
    public <Q> void print(Q q){

        System.out.println("print:"+q);
    }
    //使用W类型形参来定义方法（函数）
    public static <W> void method(W t){

        System.out.println("method"+t);
    }
}