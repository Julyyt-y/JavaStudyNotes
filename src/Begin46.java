import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 知识点：集合、遍历集合
 * 集合类出现的原因是：为了保存数量不确定的数据或具有映射关系的数据
 * 所有集合类都位于java.util包下，java.util.concurrent包下有一些多线程支持的集合类
 * 集合里只能保存对象（数组里可以保存数据和对象的引用）
 *
 * 集合类的根接口：Collection 和 Map（还有个Iterator是用来遍历Collection中的元素的）
 * Collection的子接口：Set、Queue、List
 * Set接口下的HashSet、TreeSet；List接口下的ArrayList、LinkedList；Queue接口下的ArrayDeque，这5个子类划重点
 * Map接口下的HashMap和TreeMap划重点
 *
 * Java集合大致分为四种体系（是集合也是接口）：
 * （1）Set：无序、不可重复的序列
 * （2）List：有序、可重复的序列
 * （3）Map：具有映射关系的集合
 * （4）Queue：队列集合
 *
 * Iterator必须依附于Collection对象
 * Iterator是把集合元素的值赋给了迭代变量，所以修改迭代变量并不会影响到集合元素
 * 只有Iterator的remove()方法删除上一次next()返回的集合元素时Collection集合里的元素才会被改变
 *
 */
public class Begin46 {
    public static void main(String[] args){

        //（1）使用Lambda表达式遍历集合
        //创建一个集合
        Collection books = new HashSet<>();
        books.add("高数");
        books.add("线代");
        books.add("英语");
        //调用forEach()方法遍历集合
        books.forEach(obj -> System.out.println("迭代集合元素：" + obj));


        //（2）使用Iterator遍历集合
        Iterator it = books.iterator();
        while(it.hasNext()){
            //it.next()方法返回的数据类型是Object类型，因此需要强制类型转换
            String book = (String)it.next();
            System.out.println(book);
            if (book.equals("高数")){
                //从集合中删除上一次next()方法返回的元素
                it.remove();
                //此代码会引发异常
//                books.remove(book);
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";
        }
        System.out.println(books);


        //（3）使用Lambda表达式遍历Iterator
        Iterator it1 = books.iterator();
        it1.forEachRemaining(o -> System.out.println("迭代集合元素：" + o));


        //使用foreach循环遍历集合元素
        for (Object obj :books){
            //此处的book变量也不是集合元素本身
            String book = (String)obj;
            System.out.println(book);
            if (book.equals("英语")){
                //这行代码会引发异常
                books.remove(book);
            }
        }


        //使用Predicate操作集合
        books.add("数据结构");
        books.add("形势与政策");
        books.add("abcdefghij");
        //程序传入一个Lambda表达式作为过滤条件，过滤掉长度小于10的元素
        books.removeIf(ele-> ((String)ele).length() < 10);
        System.out.println(books);
        //统计书名中“数据”字符串的图书数量
        System.out.println(calAll(books,ele->((String)ele).contains("数据")));
        //统计书名字符串长度大于5的图书数量
        System.out.println(calAll(books,ele->((String)ele).length() > 10));


        //使用Java新增的Stream（通用的流接口）操作集合
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-10)
                .add(19)
                .build();
        //下面调用聚集方法的代码每次只能执行一行
        System.out.println("is所有元素的最大值为：" + is.max().getAsInt());
        System.out.println("is所有元素的最小值为：" + is.min().getAsInt());
        System.out.println("is所有元素的总和为：" + is.sum());
        System.out.println("is所有元素的平均值为：" + is.average());
        //将is映射成一个新的Stream，新的Stream的每个元素是原Stream元素的2倍+1
        IntStream newIs = is.map(ele-> ele * 2 + 1);
        //使用方法引用的方式来遍历集合元素
        newIs.forEach(System.out::println);
    }

    public static int calAll(Collection books, Predicate p){
        int total = 0;
        for (Object obj:books){
            //使用Predicate的test()方法判断对象是否满足Predicate指定的条件
            if (p.test(obj)){
                total ++;
            }
        }
        return total;
    }
}
