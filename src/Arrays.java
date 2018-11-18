import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.*; //静态导入，导入的是Arrays这个类中的所有静态成员

/** 接Begin47*/

/**
 * 知识点：集合--Arrays（数组）

 *
 * asList：将数组变成List集合
 * 把数组变成集合的好处：可以使用集合的方法来操作数组中的元素，
 * 注意：将数组变成集合，不可以使用集合的增删方法，因为数组的长度是固定的
 * 如果数组中的元素都是对象，那么变成集合时，数组中的元素就直接转成集合中的元素
 * 如果数组中的元素都是基本数据类型，那么会将该数组作为集合中的元素存在
 *
 * toArray：将集合变成（指定类型的）数组（Collection接口中的toArray方法）
 *   1.指定类型的数组到底定义多长呢？
 *     当指定类型的数组长度小于了集合的size，那么该方法内部会创建一个新的数组，长度为集合的size；
 *     当指定类型的数组长度大于了集合的size，就不会新创建了数组，而是使用传递进来的数组；
 *     所以应当创建一个长度刚刚好的数组。
 *   2.为什么要将集合变数组？
 *     为了限定对元素的操作，不需要进行增删了
 *
 * 高级for循环：
 *   1.格式：for(数据类型  变量名：被遍历的集合（Collection）或者数组){
 *   }
 *   2.此循环的局限：只能对集合中的元素进行取出，而不能做修改动作
 *   3.对集合进行遍历：只能获取集合中的元素，不能对集合进行操作
 *     迭代器除了遍历，还可以进行remove集合中元素的动作
 *     如果是ListIterator，还可以在遍历过程中对集合进行增删改查的动作
 *
 *   JDK1.5以后出现的新特性：1.如下paramMethod； 2.静态导入
 *   方法的可变参数：其实就是上一种数组参数的简写形式，不用每一次都手动的建立数组对象，
 *             只要将操作的元素作为参数传递即可，隐式地将这些参数封装成了数组
 *             在使用时注意：一定要把可变参数定义在参数列表的最后面
 *   静态导入：当类名重名时，需要指定具体的包名；当方法重名时，需要指定具备所属的对象或者类
 *
 * 枚举就是Vector特有的取出方式
 * 发现枚举和迭代器很像，其实枚举和迭代器是一样的
 * 因为枚举的名称以及方法的名称都过长，所以被迭代器取代了
 */
public class Arrays {
    public static void main(String[] args){

        Vector v = new Vector();

        v.add("java01");
        v.add("java02");
        v.add("java03");
        v.add("java04");

        Enumeration en = v.elements();

        while (en.hasMoreElements()){
            System.out.println(en.nextElement());
        }


//        asList();

        toArray();

//        forEach();

//        paramMethod();
    }
    public static void paramMethod(){

        show(2,4,5,7,9);
    }
    public static void show(int... arr){  //...代表可变参数
        System.out.println(arr);  //打印数组的地址
        System.out.println(arr.length);
    }

    public static void forEach(){

        ArrayList<String> al = new ArrayList<String>();
        al.add("abc_1");
        al.add("abc_2");
        al.add("abc_3");
        Iterator<String> it = al.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
        for (String s : al){  //此循环的局限：只能对集合中的元素进行取出，而不能做修改动作
//            s = "kk";
            System.out.println(s);
        }

        int[] arr = {3,5,1};
        for (int i : arr){
            System.out.println("i:"+i);
        }

        HashMap<Integer,String> hm = new HashMap<Integer, String>();
        hm.put(1,"a");
        hm.put(2,"b");
        hm.put(3,"c");
        Set<Integer> keySet = hm.keySet();
        for (Integer i : keySet){
            System.out.println(i+":"+hm.get(i));
        }

//        Set<Map.Entry<Integer,String>> entrySet = hm.entrySet();
//        for (Map.Entry<Integer,String> me : entrySet){
//            System.out.println(me.getKey()+"....."+me.getValue());
//        }
        for (Map.Entry<Integer,String> me : hm.entrySet()){
            System.out.println(me.getKey()+"....."+me.getValue());
        }
    }

    public static void asList(){

        int[] arr = {2,4,5};
        //将数组元素打印出来
        System.out.println(java.util.Arrays.toString(arr));

        String[] arra = {"abc","cc","kkkk"};
        //将arra数组转为list集合
        List<String> list = java.util.Arrays.asList(arra);
        sop("contains:"+list.contains("cc"));
        //打印出来list集合
        sop(list);

        int[] nums = {2,4,5};
        List<int[]> li = java.util.Arrays.asList(nums);
        sop(li);

        Integer[] numss = {2,4,5};
        List<Integer> lists = java.util.Arrays.asList(numss);
        sop(lists);

    }
    public static void toArray(){

        ArrayList<String> al = new ArrayList<String>();
        al.add("abc_1");
        al.add("abc_2");
        al.add("abc_3");
        String[] arr = al.toArray(new String[3]);
        System.out.println(java.util.Arrays.toString(arr));

    }

    public static void sop(Object obj){

        System.out.println(obj);
    }
}
