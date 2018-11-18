import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 集合框架--List集合共性方法
 *
 *  * 总的说来，Java API中所用的集合类，都是实现了Collection接口，他的一个类继承结构如下：
 * Collection<--List<--Vector
 * Collection<--List<--ArrayList
 * Collection<--List<--LinkedList
 * Collection<--Set<--HashSet
 * Collection<--Set<--HashSet<--LinkedHashSet
 * Collection<--Set<--SortedSet<--TreeSet
 *
 * Collection:
 *       \--List：元素是有序的，元素可以重复，因为该集合体系有索引
 *           \--ArrayList：底层的数据结构使用的是数组结构；特点：查询速度很快，但是增删速度稍慢，线程不同步
 *           \--LinkedList：底层使用的是链表数据结构；特点：增删速度很快，查询稍慢
 *           \--Vector：底层使用的是数组数据结构，特点：线程同步（被ArrayList替代了）（见Begin46）
 *
 *  List：
 *      特有方法，凡是可以操作角标的方法都是该体系特有的方法
 *      增：add(index,element);
 *          addAll(index,Collection);
 *      删：remove(index);
 *      改：set(index,element);
 *      查：get(index);
 *          subList(from,to);
 *          listIterator();
 *
 * List集合特有的迭代器：ListIterator是Iterator的子接口
 *
 * 在迭代时，不可以通过集合对象的方法操作集合中的元素，因为会发生ConcurrentModificationException异常，
 * 所以在迭代时，只能用迭代器的方法操作元素，可是Iterator方法是有限的，
 * 只能对元素进行判断、取出、删除的操作，
 * 如果想要对元素进行其他的操作，如修改、添加等，就需要使用其子接口ListIterator，
 * 该接口只能通过List集合的listIterator方法获取
 *
 * LinkedList的特有方法：addFirst();   addLast();
 *                       getFirst();   getLast();  获取元素，但不删除元素，如果集合中没有元素，会出现NoSuchElementException
 *                       removeFirst();  removeLast();  获取元素，但是元素被删除，如果集合中没有元素，会出现NoSuchElementException
 * 在JDK1.6出现了替代方法：offerFirst();  offerList();
 *                         peekFirst();  peekLast();   获取元素，但不删除元素，如果集合中没有元素，会返回null
 *                         pollFirst();  pollLast();   获取元素，但是元素被删除，如果集合中没有元素，会返回null
 *
 * List集合判断元素是否相同时，依据的是equals方法
 *
 *
 */
public class CollectionList {
    public static void main(String[] args){
        method();

//        method_ArrayList();

//        method_LinkedList();

    }
    public static void method_LinkedList(){
        LinkedList link = new LinkedList();

        link.addFirst("java01");
        link.addFirst("java02");
        link.addFirst("java03");
        link.addFirst("java04");

        sop(link);
        sop(link.getFirst());
        sop("size="+link.size());
        sop(link.removeFirst());
        sop("size="+link.size());

        while (!link.isEmpty()){
            sop(link.removeFirst());
        }
    }

    public static void method_ArrayList(){

        //演示列表迭代器
        ArrayList al = new ArrayList();
        //添加元素
        al.add("java01");
        al.add("java02");
        al.add("java03");
        sop(al);
        //在迭代过程中，准备添加或者删除元素
        ListIterator li = al.listIterator();
        while (li.hasNext()) {
            Object obj = li.next();
            if (obj.equals("java02")) {
//                li.add("java009");  //在02的后面添加java009
                li.set("java006");  //把02修改成006
            }
        }
        while (li.hasPrevious()){
            sop("pre:"+li.previous());  //将集合中的元素倒着打印出来
        }

        sop("hasNext():"+li.hasNext());  //返回true或false
        sop("hasPrevious()"+li.hasPrevious());  //返回true或false

        sop(al);
    }

    public static void method(){

        ArrayList al = new ArrayList();

        //添加元素
        al.add("java01");
        al.add("java02");
        al.add("java03");

        sop("原集合是："+al);

        //在指定位置添加元素
        al.add(1,"java09");
        sop(al);

//        //删除指定位置的元素
//        al.remove(2);
//        sop(al);

        //修改元素
        al.set(2,"java007");
        sop(al);

        //通过角标获取元素（查）
        sop("get(1):"+al.get(1));

        //获取所有元素
        for (int i = 0;i <al.size();i ++){
            System.out.println("al("+i+")="+al.get(i));
        }

        Iterator it = al.iterator();
        while (it.hasNext()){
            sop("next:"+it.next());
        }

        //通过indexOf获取对象的位置
        sop("index="+al.indexOf("java007"));
        java.util.List sub = al.subList(1,3);
        sop("sub="+sub);
    }

    public static void sop(Object obj){

        System.out.println(obj);
    }
}
