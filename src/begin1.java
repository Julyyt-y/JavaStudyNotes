import java.text.DecimalFormat;

/**
 * 知识点：数据类型及运算符
 * 需求：无
 */

public class begin1 {
    public  static void main(String[] args) {

        int X = 4;
        System.out.println(X);
        byte b = 122;
        System.out.println(b);
        long L = 4;
        double f = 0.000;
//        double d = 34.56;
//        char ch = '4';
//        char ch1 = 'a';
//        char ch2 = '+';
//        boolean bo = true;
//        String author = "李华";

        DecimalFormat df = new DecimalFormat(b + "#.00");
        System.out.println(df.format(f));


        int a = 5;
        a = a + 6;
        System.out.println(a);
        byte c = 2;
        c = (byte)(c + 2);//类型强制转换
        System.out.println(c);
        System.out.println('a');
        System.out.println('a'+1); //'a'对应的ASCII码为97，故打印出来的事98
        System.out.println('1'+1); //字符1对应的ASCII码为49
        System.out.println((char)('a'+1));  //打印出b
        System.out.println((char)7);
        int x = 4270;
        x = x / 1000 * 4;
        System.out.println("x = "+x);
        System.out.println(-10/4);
        System.out.println(-1%5);
        System.out.println("hahahahaha"+"HaHaHaHa");
        System.out.println("ab"+5+5);
        System.out.println("ab"+(5+5));
        System.out.println("\"Hello world\"");
        System.out.println("\\hello\\");
        System.out.println(3>4);
        System.out.println(3!=4);
        System.out.println(true^true);  // '^'表示：如果相对应位值相同，则结果为0，否则为1，输出：false
        System.out.println(false|true);
        int g = 2;
        System.out.println(g>3 & g<6);
        System.out.println('A' << 2);   // << ：按位左移运算符。左操作数按位左移右操作数指定的位数，输出：260
        System.out.println('A' >>> 2);  // >>> ：按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充，
        // 输出：16
        System.out.println(~6);       //' ~ '表示：按位取反运算符翻转操作数的每一位，即0变成1，1变成0
        System.out.println((char)(60-10+'A'));  //输出 s
        System.out.println("Hello" + 'a' + 7); // 7转换为字符串，输出：Helloa7
        System.out.println( 'a' + 7 + "Hello"); //'a'先自动提升为int类型，+7得104，104再自动转换为字符串，输出：104Hello
        System.out.println(a + "春节");  //输出：11春节

        int[] array = new int[7];
        array = new int[]{1, 2, 3, 4, 5, 6, 7};

        //instanceof 运算符：该运算符用于操作对象实例，检查该对象是否是一个特定类型（类类型或接口类型）
        String name  = "ZORO";
        boolean result  = name instanceof String;  //检查name对象是否是String类型（特定类型）
        System.out.println(result);

    }

}
