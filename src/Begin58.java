import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * 知识点：Date、Calendar、Math、System
 *
 * Date：日期类，表示特定的瞬间，精确到毫秒
 *
 * Math：Math类包含于执行于基本数学运算的方法，如初等指数、对数、平方根、三角函数等
 *
 * System：
 * System类中的方法和属性都是静态的
 * System:描述系统的一些信息
 * out：标准输出，默认时控制台（或屏幕）；
 * in：标准输入，默认是键盘
 *
 * properties getProperties();：获取系统属性信息
 *
 * 练习：毕向东216
 */
public class Begin58 {
    public static void main(String[] args){

//        dateDemo();

        calendarDemo();

    }
    public static void calendarDemo(){
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年");
//        String year = sdf.format(date);
//        System.out.println(year);

        //打印地点时间（很详细）
        Calendar c = Calendar.getInstance();
        sop(c);

        sop(c.get(Calendar.YEAR)+"年");

        //重新设定时间
        c.set(2018,2,21);
        c.add(Calendar.YEAR,4);  //加4年
    }

    public static void dateDemo(){

        //打印出现在的具体时间
        Date date = new Date();
        System.out.println(date);

        //格式化日期,将模式封装到SimpleDateFormat对象中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日E");
        //调用format方法让模式格式化指定Date对象
        String time = sdf.format(date);
        System.out.println("time="+time);
    }
    public static void sop(Object obj){
        System.out.println(obj);
    }

    //Math类
    public static void mathTest(){
        double d = Math.ceil(12.34);
        //ceil方法：返回大于指定数据的最小整数
        sop("d="+d);

        double d1 = Math.floor(12.34);
        //返回小于指定数据的最大整数
        sop("dd="+d1);

        long l = Math.round(12.54);
        //四舍五入
        sop("d=="+d);
        sop("d1=="+d1);
        sop("l=="+l);

        double d2 = Math.pow(2,3);
        //2的3次方
        sop("pow"+d2);

        //打印随机数,打印10个100以内的随机数
        for (int i = 0;i < 10;i ++){
            int d3 = (int)(Math.random()*100+1);
            sop(d3);
        }
    }

    //System类
    public static void systemTest(){
        Properties properties = System.getProperties();  //获取系统属性信息
        //因为Properties是Hashtable的子类，也就是Map集合的一个子类对象，
        //那么可以通过map的方法取出该集合中的元素，
        //该集合中存储的都是字符串，没有泛型定义。

        //如何在系统中自定义一些特有信息呢？
        System.setProperty("mykey","myvalue");

        //获取指定属性信息
        String value = System.getProperty("os.name");
        System.out.println("value="+value);
        String v = System.getProperty("haha");  //获取haha键
        System.out.println("v="+v);  //没有haha键

//        //获取所有属性信息
//        for (Object object : properties.keySet()){
//            String value = (String)properties.get(object);
//            System.out.println(object+"..."+value);
//        }
    }

}
