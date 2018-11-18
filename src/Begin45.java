/**
 * 知识点：基本数据类型对象包装类
 *
 * byte     Byte
 * short    short
 * int      Integer
 * long     Long
 * boolean  Boolean
 * float    Float
 * double   Double
 * char     Character
 *
 * 基本数据类型对象包装类的最常见作用就是用于基本数据类型和字符串类型之间做转换
 * 基本数据类型转成字符串：基本数据类型+""
 *                         基本数据类型.toString(基本数据类型值)，如Integer.toString(34);将34整数变成字符串"34"
 * 字符串转成基本数据类型：xxx a = Xxx.parseXxx(String);
 *                     eg：int a = Integer.parseInt("123");
 *                     eg：double b = Double.parseDouble("12.23");
 *                     eg：boolean.b = Boolean.parseBoolean("true");(这三个都是静态的)
 *                     Integer i = new Integer("123"); int num = i.intValue();(此方法是非静态的)
 * 十进制转成其它进制：toBinaryString();
 *                     toHexString();
 *                     toOctalString();
 * 其它进制转成十进制：parseInt(string,radix);
 *
 * JDK1.5版本以后出现的新特性
 */
public class Begin45 {
    public static void sop(String str){
        System.out.println(str);

    }

    public static void main(String[] args){
//        sop("int max:"+Integer.MAX_VALUE);  //获取到的是int类型的最大范围

//        //将一个字符串转成整数
//        int num = Integer.parseInt("123");  //必须传入数字格式的字符串
//        sop("num="+(num+4));

//        //进制转换
//        sop(Integer.toBinaryString(-6));
//        sop(Integer.toHexString(60));

        //Integer x = new Integer(4);
        Integer x = 4;  //JDK1.5版本以后出现的新特性:自动装箱;；4 等同于new Integer(4)
        x = x + 2;  //x进行自动拆箱，变成了int类型，和2进行加法运算，再将和进行装箱赋给x

        Integer m = 128;
        Integer n = 128;
        sop("m==n:"+(m==n));  //结果为false
        Integer a = 127;
        Integer b = 127;
        sop("a==b:"+(a==b));  //结果为true
        //JDK1.5版本以后出现的新特性:当数值在byte范围内时，
        // 对于新特性，如果该数值已经存在，则不会再开辟新的空间；超过byte范围时，会开辟新的空间

        Integer ii = new Integer("12345");
        int num = ii.intValue(); //num=12345
        System.out.println(num);
    }
}
