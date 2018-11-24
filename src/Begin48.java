import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 知识点：集合框架--泛型限定
 * (泛型的高级用法)
 * ？为通配符，也可以理解为占位符，意思是元素类型未知的***
 * ？ extends E：可以接收E类型或E的子类型，上限；
 * ？ super E ：可以接收E类型或E的父类型，下限
 * eg：public static void printCall1 (ArrayList<? extends Person3> al){}
 *    表示printCall1方法的形参是 元素类型未知的ArrayList
 *
 * 类型通配符适用于任何支持泛型声明的接口和类
 *
 */
public class Begin48 {
    public static void main(String[] args){

//        method_1();

        method_2();


        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(5);
        //此处可准确的知道最后一个被复制的元素是Integer类型，与src集合元素的类型相同
        Integer last = copy(ln,li);     //此处调用后推断出最后一个被复制的元素是Integer类型的
        System.out.println(ln);

    }

    public static void method_2(){
        ArrayList<Person3> al = new ArrayList<>();
        al.add(new Person3("abc1"));
        al.add(new Person3("abc2"));
        al.add(new Person3("abc3"));
        al.add(new Person3("abc4"));

        ArrayList<Student5> al1 = new ArrayList<>();
        al1.add(new Student5("abc___1"));
        al1.add(new Student5("abc___2"));
        al1.add(new Student5("abc___3"));
        printCall1(al);
        printCall1(al1);
    }

    //使用类型通配符
    //意思是元素类型未知的ArrayList是Person3的子类
    //凡是Person3的子类都可以传入，但如果子类的类型未知，同样也不能把任何对象添加到这种集合中
    public static void printCall1(ArrayList<? extends Person3> al){  //泛型限定（被限制的类型通配符）
        Iterator<? extends Person3> it = al.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getName());
        }
    }

    public static void printCall2(ArrayList<?> al){  //泛型限定（被限制的类型通配符）
        ArrayList<?> c = new ArrayList<String>();
        //带通配符的ArrayList仅表示它是各种泛型ArrayList的父类，并不能把元素加入到其中
        // （因为集合ArrayList集合中元素的类型是未知的，所以无法向此集合中添加对象），
        // 故下面这句代码会导致异常
//        c.add(new Object());
    }

    //使用类型通配符的下限
    //意思：？类型是T类型的本身或者T类型的 父亲
    public static <T> T  copy(Collection<? super T> dest,Collection<T> src){
        T last = null;
        for (T ele : src){
            last = ele;
            dest.add(ele);
        }
        return last;
    }


    public static void method_1(){
        ArrayList<String> al = new ArrayList<String>();
        al.add("abc1");
        al.add("abc2");
        al.add("abc3");

        ArrayList<Integer> al1 = new ArrayList<Integer>();
        al1.add(4);
        al1.add(7);
        al1.add(1);

        printColl(al);
        printColl(al1);
    }

    public static void printColl(ArrayList<?> al){
        Iterator<?> it = al.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}

class Person3{
    private String name;
    Person3(String name){

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
    class Student5 extends Person3{
    Student5(String name){

        super(name);
    }
}
class AA{

}

/**
 * 更极端的情况：
 * 程序需要为类型形参设定多个上限（至多有一个父类上限，可以有多个接口上限），
 * 表明该类型参数必须是其父类的子类（是父类本身也行），并且实现多个上限接口
 */
class Apple<T extends Person3 & java.io.Serializable & Sleep>{

}

