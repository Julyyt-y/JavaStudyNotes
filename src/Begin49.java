import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 知识点：泛型限定、泛型方法与方法重载、泛型构造器、
 *
 * 泛型方法：
 * eg： static <T> void fromArrayToCollection(T[] a,Collection<T> c){}
 *      此泛型方法中带一个T类型形参（T类型的数组和数据类型是T类型的集合）。
 * 方法中声明的形参只能在该方法里使用，而接口、类声明中定义的类型形参可以在整个接口、类中使用；
 * 方法中的泛型参数无需显式传入实际类型参数，eg：fromArrayToCollection(oa,co)。
 *
 * 泛型构造器：
 * Java允许在构造器签名中声明类型形参，这种构造器叫泛型构造器
 *
 */
public class Begin49 {
    public static void main(String[] args){
        TreeSet<Student6> ts = new TreeSet<Student6>(new Comp());
        ts.add(new Student6("abc03"));
        ts.add(new Student6("abc02"));
        ts.add(new Student6("abc06"));
        ts.add(new Student6("abc01"));
        Iterator<Student6> it = ts.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getName());
        }


        TreeSet<Worker6> ts1 = new TreeSet<Worker6>(new Comp());
        ts1.add(new Worker6("w_abc03"));
        ts1.add(new Worker6("w_abc02"));
        ts1.add(new Worker6("w_abc06"));
        ts1.add(new Worker6("w_abc01"));
        Iterator<Worker6> it1 = ts1.iterator();
        while (it1.hasNext()){
            System.out.println(it1.next().getName());
        }


        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        //下面代码中T代表Object类型
        fromArrayToCollection(oa,co);   //方法中的泛型参数无需显式传入实际类型参数
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        //下面代码中T代表String类型
        fromArrayToCollection(sa,cs);
        //下面代码中T代表Object类型
        fromArrayToCollection(sa,co);
        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<>();
        //下面代码种T代表Number类型
        fromArrayToCollection(ia,cn);
        //下面代码中T代表Number类型
        fromArrayToCollection(fa,cn);
        //下面代码中T代表Number类型
        fromArrayToCollection(na,cn);
        //下面代码中T代表Object类型
        fromArrayToCollection(na,co);
        //下面代码中T代表String类型，但na是一个Number类型数组
        //因为Number既不是String类型，也不是它的子类，所以出现编译错误
//        fromArrayToCollection(na,cs);


        //泛型构造器中的T参数为String
        new Foo("高等数学");
        //泛型构造器中的T参数为Integer
        new Foo(200);
        //显式指定泛型构造器中的T参数为String
        //传给Foo构造器的实参也是String对象
        new <String> Foo("线性代数");
        //显式定义泛型构造器中的T参数为String
        //但传给Foo构造器的实参是Double对象，下面代码报错
//        new <String> Foo(12.3);
    }

    //泛型方法，该泛型方法中带一个T类型形参（T类型的数组和数据类型是T类型的集合）
    static <T> void fromArrayToCollection(T[] a,Collection<T> c){
        for (T o : a){
            c.add(o);
        }
    }
}

//class stuComp implements Comparator<Student6> {  //比较器
//    public int compare(Student6 s1,Student6 s2){
//        return s1.getName().compareTo(s2.getName());
//    }
//}
//class worComp implements Comparator<Worker6> {  //比较器
//    public int compare(Worker6 w1,Worker6 w2){
//        return w1.getName().compareTo(w2.getName());
//    }
//}
class Comp implements Comparator<Person6> {  //比较器,既能接收Person6又能接收Student6
    public int compare(Person6 p1,Person6 p2){

        return p2.getName().compareTo(p1.getName());  //此处只能用父类方法
    }
}


class Person6{
    private String name;
    Person6(String name){

        this.name = name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {

        return "person:"+name;
    }
}


class Student6 extends Person6{
    Student6(String name){

        super(name);
    }
}


class Worker6 extends Person6{
    Worker6(String name){

        super(name);
    }
}
//泛型方法和类型通配符的区别
interface Collec<E>{
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);
}
interface Colle<E>{
    <T> boolean containsAll(Collection<T> c);
    <T extends E> boolean addAll(Collection<T> c);
}

class Foo{
    //泛型构造器
    public <T> Foo(T t){
        System.out.println(t);
    }
}
/**
 * Java的抽象类Number：
 * Java抽象类Number类包含了六个子类,即我们众所周知的六个包装类:Integer,Long,Short,Byte,Double,Float;
 * 这六个包装类分别对应六个内置数据类型:int,long,short,byte,double,float.
 * 当内置的数据类型被当做对象使用时,编译器会自动把内置数据类型装箱成为对应的包装类;
 * 而相同的,当编译器需要对这个对象进行运算等操作时,编译器会将此对象自动转换为对应的内置数据类型,此操作也叫拆箱,和装箱正好相反.
 */
