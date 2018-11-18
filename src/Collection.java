import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * 知识点：Collection
 * Collection的子接口：Set、Queue、List
 * （1）Set：无序、不可重复的序列
 * （2）List：有序、可重复的序列
 * （3）Queue：队列集合
 *
 * Iterator接口：
 * 使用：Iterator iterator();
 * 返回一个Iterator对象，用于遍历集合里的元素
 */

public class Collection {
    public static void main(String[] args){

//        base_method();

//        method_2();

        method_get();

        //        sortDemo();

//        maxDemo();

//        binarySearchDemo();

//        fillDemo();

//        replaceAllDemo();

//        reverseOrder();

//        swapDemo();

        shuffleDemo();
    }

    public static void shuffleDemo(){
        //new一个List的对象（集合）list，这个对象（集合）是ArrayList型的
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
        java.util.Collections.shuffle(list);
        //把集合中的元素重新按照随机的方式进行排放，故集合中的元素每次打印出来的顺序都不一样
        sop(list);
    }

    public static void swapDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
        //swap是Collection里的方法
        java.util.Collections.swap(list,1,2);  //角标为1和2的元素调换位置
        sop(list);
    }

    public static void reverseOrder(){
        TreeSet<String> ts = new TreeSet<String>(java.util.Collections.reverseOrder());
        //传进去一个比较器，用于对数据的顺序进行反转
        ts.add("abcd");
        ts.add("aaa");
        ts.add("z");
        ts.add("qq");
        ts.add("z");
        sop(ts);
    }

    public static void replaceAllDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
        java.util.Collections.replaceAll(list,"aaa","kkkk");
        sop(list);
    }

    public static void fillDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
        java.util.Collections.fill(list,"pp");  //将集合中的元素全都替换成“pp”
        //fill方法可以将list集合中所有元素替换成指定元素
        sop(list);
    }
    public static void sortDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
//        java.util.Collections.sort(list);  //排序
        java.util.Collections.sort(list,new StrLenComparator());  //按字符串长度排序
        sop(list);
    }
    public static void binarySearchDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        java.util.Collections.sort(list);
        sop(list);
        int index = java.util.Collections.binarySearch(list,"aaa");
        //取“aaa”的角标，若不存在，打印：-插入点-1
        sop("index:"+index);
    }

    public static void maxDemo(){
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("z");
        list.add("qq");
        list.add("z");
        sop(list);
        String max = java.util.Collections.max(list);
        sop("max:"+max);
    }

    public static void sop(Object obj){
        System.out.println(obj);
    }

    public static void method_get(){

        ArrayList al = new ArrayList();
        al.add("java01");
        al.add("java02");
        al.add("java03");
        al.add("java04");

        //获取迭代器，用于取出集合中的元素
        Iterator it = al.iterator();
//        sop(it.next());
//        sop(it.next());

        while (it.hasNext()){  //打印集合中全部元素
            sop(it.next());
        }
    }

    public static void method_2(){

        //创建一个集合容器，使用Collection接口的子类ArrayList
        ArrayList al1 = new ArrayList();
        al1.add("java01");
        al1.add("java02");
        al1.add("java03");
        al1.add("java04");

        ArrayList al2 = new ArrayList();
        al2.add("java01");
        al2.add("java02");
        al2.add("java05");
        al2.add("java06");

//        al1.retainAll(al2);  //取交集，al1中只会保留和al2中相同的元素

        al1.removeAll(al2);  //移除al1中与al2中相同的元素

        sop("al1:"+al1);
        sop("al2:"+al2);

    }

    public static void base_method(){

        //创建一个集合容器，使用Collection接口的子类ArrayList
        ArrayList al = new ArrayList();

        //1.添加元素
        al.add("java01");  //add方法用Object接口的原因：因为容器里面添加的对象是型的，
        al.add("java02");
        al.add("java03");
        al.add("java04");

        //打印集合
        sop("原集合："+al);

        //2.获取个数，集合长度
        sop("size:"+al.size());

        //3.删除元素
        al.remove("java02");
//        //清空集合
//        al.clear();
        //打印改变后的集合
        sop("改变后："+al);

        //4.判断元素
        sop("java03是否存在："+al.contains("java03"));
        sop("集合是否为空："+al.isEmpty());

    }


}

class StrLenComparator implements Comparator<String> {
    public int compare(String s1,String s2){
        if (s1.length() > s2.length()){
            return 1;
        }
        if (s1.length() < s2.length()){
            return -1;
        }
        return s1.compareTo(s2);
    }
}
