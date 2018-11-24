import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 知识点：集合--Map集合
 *
 * Map 不是 collection的子接口或者实现类。Map是一个接口。
 *
 * Map:
 *    \--HashTable：底层是哈希表数据结构，不可以存入null键null值，该集合是线程同步的，jdk1.0效率低
 *    \--HashMap：底层是哈希表数据结构，允许使用null键和null值，该集合是不同步的，jdk1.2效率高
 *    \--TreeMap：底层是二叉树数据结构，线程不同步，可以用于给map集合中的键进行排序
 * 发现和Set很像，其实Set底层就是使用了Map集合
 *
 * Map集合：该集合存储键值对，一对一对往里存，而且要保证键的唯一性（所有操作都是对K进行）
 *     1.添加：put(K key,V value);
 *             putAll(Map<? extends K,?extends V> m)
 *     2.删除：clean();
 *             remove(Object key);
 *     3.判断：containsValue(Object value);
 *             containsKey(Object key);
 *             isEmpty();
 *     4.获取：get(Object key);
 *             size();
 *             values();
 *             entrySet();取出
 *             keySet();取出
 *
 * Map集合的取出原理：将map集合转成set集合，再通过迭代器取出
 * map集合的两种取出方式：
 *   \--keySet：将map中所有的键存入到Set集合，因为Set具备迭代器，
 *              所以可以用迭代方式取出所有的键，再根据get方法，获取每一个键对应的值
 *   \--entrySet：Set<Map.Entry<>entrySet> entrySet，将map集合中的映射关系存入到了set集合中，
 *                而这个关系的数据结构类型就是Map.Entry;
 *                Map.Entry：其实Entry也是一个接口，它是Map接口中的一个内部接口
 *
 * Map集合的取出原理：将map集合转成set集合，再通过迭代器取出
 *
 */
public class Map {
    public static void main(String[] args){
        java.util.Map<String,String> map = new HashMap<String, String>();
        //添加元素（如果添加时添加了两个相同的键，如两个01，那么后添加的值会覆盖先添加的值，put方法会返回被覆盖的那个值）
        map.put("01","zhangsan1");
        map.put("02","zhangsan2");
        map.put("03","zhangsan3");
        map.put("04","zhangsan4");
        System.out.println("containsKey:"+map.containsKey("02"));  //判断元素是否存在
        System.out.println("remove"+map.remove("02"));  //删除元素
        System.out.println(map);
        System.out.println("get:"+map.get("03"));  //获取，也可以用于判断某一个键是否存在
        map.put("05",null);  //HashTable的V不能为空
        //可以通过get方法的返回值判断一个键是否存在,通过返回空来判断

        Collection<String> coll = map.values();
        System.out.println(coll);
        //通过values获取map集合中所有的值
        System.out.println(map);

        //        keySet();

        entrySet();

    }

    public static void entrySet(){
        java.util.Map<String,String> map = new HashMap<String, String>();
        map.put("02","zhangsan2");
        map.put("03","zhangsan3");
        map.put("01","zhangsan1");
        map.put("04","zhangsan4");
        //将Map集合中的映射关系取出，存入到Set集合中
        Set<java.util.Map.Entry<String,String>> entrySet = map.entrySet();
        Iterator<java.util.Map.Entry<String,String>> it = entrySet.iterator();

        while (it.hasNext()){
            java.util.Map.Entry<String,String> me = it.next();
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key+":"+value);
        }
    }

    public static void keySet(){
        java.util.Map<String,String> map = new HashMap<String, String>();
        map.put("02","zhangsan2");
        map.put("03","zhangsan3");
        map.put("01","zhangsan1");
        map.put("04","zhangsan4");

        //先获取map集合的所有键的Set集合、keySet();
        Set<String> keySet = map.keySet();

        //有了Set集合，就可以获取迭代器
        Iterator<String> it = keySet.iterator();

        while (it.hasNext()){
            String key = it.next();  //取到了所有的键
            //有了键就可以通过集合的get方法获取其对应的值
            String value = map.get(key);
            System.out.println("key:"+key+",value:"+value);
        }
    }
}
