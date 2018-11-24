import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 知识点：循环
 * 此处主要说的是C语言里面没有的循环：Foreach循环
 * 这种循环遍历数组和集合更加简洁。
 * 使用foreach循环遍历数组或集合的元素时：
 * 1.无需获得数组或集合的长度；
 * 2.无需根据索引来访问数组元素或集合元素，
 * foreach循环自动遍历数组或集合的每个元素。
 *
 *  foreach循环和普通循环的区别：
 *  foreach循环无需循环条件，无需循环迭代语句，因为这些部分都是由系统来完成的，
 *  foreach循环自动迭代数组的每一个元素，当每个元素都被迭代一次后，foreach循环自动结束。
 *
 *  注意：使用foreach循环时，通常不要对循环遍历赋值，原因：语法上允许，但没有太大意义，且易引起错误。
 *
 *  高级for循环（forEach）：
 *   1.格式：for(数据类型  变量名：被遍历的集合（Collection）或者数组){
 *   }
 *   2.此循环的局限：只能对集合中的元素进行取出，而不能做修改动作
 *   3.对集合进行遍历：只能获取集合中的元素，不能对集合进行操作
 *     迭代器除了遍历，还可以进行remove集合中元素的动作
 *     如果是ListIterator，还可以在遍历过程中对集合进行增删改查的动作
 */

public class begin3 {
    public static void main(String[] args){
        String[] arr = {"java","book","aaa","fff"};
        for (String arrs : arr) {
            System.out.println(arrs);
        }
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
}
