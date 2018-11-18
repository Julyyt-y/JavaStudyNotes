import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 知识点：集合框架--HashSet
 * \--Set：元素是无序(存入和取出的顺序不一定一致)的，元素不可以重复，因为该集合体系没有索引
 *   常见的子类：\--HashSet：底层数据结构是哈希表，线程是非同步的(毕向东178)，可以放入null，但只能放入一个null
 *               \--TreeSet：可以对Set集合中的元素进行排序，底层数据结构是二叉树，不允许放入null值
 *                           保证元素唯一性的依据：compareTo方法return 0
 * Set集合的功能和Collection是一致的
 *
 * HashSet是如何保证元素的唯一性的呢？
 * 是通过元素的两个方法：hashCode和equals来完成的
 * 如果元素的hashCode值相同，才会判断equals是否为true
 * 如果元素的hashCode值不同，不会调用equals
 *
 * 注意：对于判断元素是否存在，以及删除等操作，依赖的方法是元素的hashcode和equals方法
 *
 * 排序时，当主要条件都相同时，一定要判断一下次要条件
 *
 * TreeSet排序的第一种方式：让元素自身具备比较性，
 * 元素需要实现Comparable接口，覆盖compareTo方法，这种方式也称为元素的自然顺序；
 * TreeSet排序的第二种方式：当元素自身不具备比较性时，或者具备的比较性不是需要的时，
 * 这时就需要让集合自身具备比较性。在集合初始化时，就有了比较方式
 * 当两种排序都存在时，以比较器为主
 * 定义一个类，实现Comparator接口，覆盖compare方法
 */

/*
public class CollectionSet {

    public static void sop(Object obj){
        System.out.println(obj);
    }

    public static void main(String[] args){
//        Demo9 d1 = new Demo9();
//        Demo9 d2 = new Demo9();
//        sop(d1);
//        sop(d2);

        HashSet hs = new HashSet();

        sop(hs.add("java01"));
        sop(hs.add("java01"));  //添加失败，返回false（已经添加了java01了）
        hs.add("java01");
        hs.add("java02");
        hs.add("java03");
        hs.add("java04");

        Iterator it = hs.iterator();

        while (it.hasNext()){  //无序
            sop(it.next());
        }
    }
}
*/


/*
public class CollectionSet {

    public static void sop(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new Person3("a1",11));
        hs.add(new Person3("a2",12));
        hs.add(new Person3("a3",13));
        hs.add(new Person3("a3",13));

        sop("al:"+hs.contains(new Person3("a2",12)));  //判断元素是否存在

        Iterator it = hs.iterator();

        while (it.hasNext()){
            Person3 p = (Person3)it.next();
            sop(p.getName()+"::"+p.getAge());
        }
    }
}
class Person3{
    private String name;
    private int age;
    Person3(String name,int age){
        this.name = name;
        this.age = age;
    }

        @Override
        public int hashCode() {
            System.out.println(this.name+"...hashCode");
            return name.hashCode()+age;
        }

    public boolean equals(Object obj){
        if (!(obj instanceof Person3)){
            return false;
        }
        Person3 p = (Person3)obj;
        System.out.println(this.name+"....."+p.name);
        return this.name.equals(p.name)&&this.age==p.age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
*/


//TreeSet
//需求：往TreeSet集合中自定义对象学生，想按照学生的年龄进行排序
//排序时，当主要条件都相同时，一定要判断一下次要条件
/*
public class CollectionSet {
    public static void main(String[] args){

        TreeSet ts = new TreeSet();

        ts.add(new Student3("lisi02",22));
        ts.add(new Student3("lisi007",20));
        ts.add(new Student3("lisi09",16));
        ts.add(new Student3("lisi01",16));

        Iterator it = ts.iterator();
        while (it.hasNext()){
            Student3 stu = (Student3)it.next();
            System.out.println(stu.getName()+"....."+stu.getAge());
        }

    }
}

class Student3 implements Comparable{
    private String name;
    private int age;

    Student3(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof Student3)) {
            throw new RuntimeException("不是学生对象");
        }
        Student3 s = (Student3) obj;

        System.out.println(this.name+"...compare..."+this.age);
        if (this.age > s.age) {
            return 1;
        }
        if (this.age == s.age){
            return this.name.compareTo(s.name);
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
*/

//当元素自身不具备比较性，或者具备的比较性不是所需要，这时需要让容器自身具备比较性，
//定义了比较器，将比较器对象作为参数传递给TreeSet集合的构造函数
//按照姓名排序
public class CollectionSet {
    public static void main(String[] args) {

        TreeSet ts = new TreeSet();
        ts.add(new Student3("lisi02",22));//使用自定义的构造器来创建对象
        ts.add(new Student3("lisi007",20));
        ts.add(new Student3("lisi09",16));
        ts.add(new Student3("lisi01",16));
        ts.add(new Student3("lisi007",18));

        Iterator it = ts.iterator();
        while (it.hasNext()){
            Student3 stu = (Student3)it.next();
            System.out.println(stu.getName()+"....."+stu.getAge());
        }
    }
}

class MyCompare implements Comparator{
    public int compare(Object o1,Object o2){
        Student3 s1 = (Student3)o1;
        Student3 s2 = (Student3)o2;

        int num = s1.getName().compareTo(s2.getName());

        if (num == 0){
            if (s1.getAge() > s2.getAge())
                return 1;
            if (s1.getAge() == s2.getAge())
                return 0;
            return -1;
        }
        return num;
    }
}

class Student3 implements Comparable{
    private String name;
    private int age;

    //构造器。将传入的name和age赋给this代表的对象，即初始化对象（为成员变量赋初值）
    Student3(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof Student3)) {
            throw new RuntimeException("不是学生对象");
        }
        Student3 s = (Student3) obj;

        if (this.age > s.age) {
            return 1;
        }
        if (this.age == s.age){
            return this.name.compareTo(s.name);
        }
        return -1;
    }

    public String getName() {

        return name;
    }

    public int getAge() {

        return age;
    }
}