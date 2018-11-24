/**
 * 知识点：String类
 *
 * String类没有子类
 *
 * String类适用于描述字符串事物，那么它就提供了多个方法对字符串进行操作；
 * 常见操作：
 * 1.获取：
 *     1.1字符串中包含的字符数，也就是字符串的长度int length():获取长度
 *     1.2根据位置获取位置上某个字符，char charAt(int index);
 *     1.3根据字符获取该字符在字符串中的位置，int indexOf(int ch)（接收的是Ascii码，返回的是ch在字符串中第一次出现的位置）
 *        int indexOf(int ch,int fromIndex):从fromIndex指定位置开始，获取ch在字符串中出现的位置
 *        int indexOf(String str)（返回的是str在字符串中第一次出现的位置）
 *        int indexOf(String str,int fromIndex):从fromIndex指定位置开始，获取str在字符串中出现的位置
 * 2.判断
 *     2.1字符串中是否包含某一个子串：boolean contains(str);
 *     2.2字符串中是否有内容:boolean isEmpty();原理就是判断字符串长度是否为0
 *     2.3字符串是否是以指定内容开头：boolean startWith(str);
 *     2.4字符串是否是以指定内容结尾：boolean endWith(str);
 *     2.5判断字符串内容是否相同，复写了Object类中的equals方法：boolean equals(str);
 *     2.6判断字符串内容是否相同，并忽略大小写：boolean equalsIgnoreCase();
 * 3.转换：
 *     3.1将字符数组转成字符串：
 *        构造函数：String(char[]);
 *                  String(char[],offset,count):将字符数组中的一部分转成字符串
 *        静态方法：static String copyValueOf(char[]);
 *                  static String copyValueOf(char[] data,int offset,int count)
 *                  static String valueOf(char[]);
 *     3.2将字符串转成字符数组：char[] toCharArray();
 *     3.3将字节数组转成字符串：String(byte[]);
 *                              String(byte[],offset,count):将字符数组中的一部分转成字符串
 *     3.4将字符串转成字节数组：byte[];
 *                              getByte();
 *     3.5将基本数据类型转换成字符串：static String valueOf(int);
 *                                    static String valueOf(double);
 *     特殊：字符串和字节数组在转换过程中，是可以指定编码表的
 * 4.替换：String replace(oldchar,newchar);
 * 5.切割：String[] split(regex);
 * 6.子串，获取字符串中的一部分：String substring(begin);
 *                               String substring(begin,end);
 * 7.转换：去除空格，比较
 *     7.1将字符串转成大写或者小写：String toUppCase();
 *                                  String toLowerCase();
 *     7.2将字符串两端的多个空格去除：String trim();
 *     7.3对两个字符串进行自然顺序比较：int compareTo(string);
 *
 * 练习：毕向东158-161
 */
public class Begin43 {

    public static void method_7(){
        String s = "  Hello Java   ";
        sop(s.toUpperCase());   //将字符串转换成大写
        sop(s.toLowerCase());   //将字符串转换成小写
        sop(s.trim());      //打印原字符串

        String s1 = "abc";
        String s2 = "aaa";
        sop(s1.compareTo(s2));  //s1>s2：返回一个正数；s1<s2：返回一个负数；s1=s2：返回0
    }

    public static void method_sub(){
        String s = "abcdef";
        sop(s.substring(2));    //从第3个位置开始往后全部字符串
        sop(s.substring(2,4));  //第3、4个位置上的字符串
    }


    public static void method_split(){
        String s = "zhangsan,lisi,wangwu";
        String[] arr = s.split(",");
        for (int x = 0;x <arr.length;x ++){
            sop(arr[x]);
        }
    }

    public static void method_replace(){
        String s = "hello java";
        String s1 = s.replace('a','n');//用n替换a
        sop("s="+s);
        sop("s1="+s1);
    }

    public static void method_trans(){
        char[] arr = {'a','b','c','d','e','f'};
        String s = new String(arr,1,3); //arr的第2,3,4个字符组成的子串
        sop("s="+s);
    }

    public static void method_is(){
        String str = "ArrayDemo.java";

        //判断文件名称是否是Array单词开头
        sop(str.startsWith("Array"));

        //判断文件名称是否是.java结尾
        sop(str.endsWith(".java"));

        //判断文件中是否包含Demo
        sop(str.contains("Demo"));
    }
    public static void method_get(){
        String str = "abcdef";

        //长度
        sop(str.length());

        //根据索引获取字符
        sop(str.charAt(4));

        //根据字符获取索引
        sop(str.indexOf('a'));
        sop(str.indexOf('a',3));  //没有找到时返回-1

        //反向索引一个字符串出现的位置
        sop(str.lastIndexOf("a"));
    }
    public static void main(String[] args){
/*        String s = new String("abc");  //

        String s1 = "abc";  //s1是一个类类型变量，“abc”是一个对象；字符串最大的特点：一旦被初始化，就不可以被改变

//        s1 = "kk";  //s1刚才指向abc，现在指向kk
        //这两个等价，使用起来也一样；
        // s和s1的区别：s1代表一个对象，s有两个对象

        String s3 = "abc";  //s1=s3,因为内存中已经有“abc”了，不会再开辟空间了

        System.out.println(s1.equals(s));  //String类复写了Object类中equals方法，定义了自己独特的内容，该方法用于判断字符串内容是否相同
        System.out.println(s == s1);*/

        method_get();

        method_is();

        method_trans();

        method_replace();

        method_split();

        method_sub();

        method_7();
    }

    public static void sop(Object obj){

        System.out.println(obj);
    }
}
