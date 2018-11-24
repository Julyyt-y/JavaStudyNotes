/**
 * 知识点：异常机制
 * 1.异常：就是程序在运行时出现不正常情况。
 * 2.异常由来：问题也是现实生活中一个具体的事物，也可以通过Java类的形式进行描述，并封装成对象，
 *           其实就是Java对不正常情况进行描述后的对象体现。
 *
 * 3.对于问题的划分：两种，一种是严重的问题“不可治愈”，一种是非严重的问题“可治愈”，
 *   对于严重的：Java通过Error类进行描述，
 *               对于Error，一般不编写针对性的代码对其进行处理；
 *   对应非严重的：Java通过Exception类进行描述，
 *                 对应Exception，可以使用针对性的处理方式进行处理；
 *    无论Error还是Exception，都具有一些共性内容，比如：不正常情况信息、引发原因等。
 *
 * 所有的异常类是从 java.lang.Exception 类继承的子类，
 * Exception是Throwable类的子类。除了Exception外，Throwable还有一个子类Error 。
 * 异常类Exception有两个主要的子类：IOException 类和 RuntimeException 类。
 * 一个方法可以声明多个异常，多个异常之间用逗号隔开。
 *  Throwable
 *       \--Error
 *       \--Exception
 *
 * 4.异常处理：
 *   try{
 *      需要被检测的代码；
 *   }
 *   catch(异常类  变量){
 *    处理异常的代码的处理方式；
 *   }
 *   finally{
 *      一定会执行的语句；
 *   }
 * 三种结合方式：try-catch；try-finally；try-catch-finally
 *
 * 5.对捕获到的对象进行常见方法操作：
 *   String getMessage();等
 *
 * 其实jvm默认的异常处理机制就是在调用printStackTrace方法，打印异常的堆栈的跟踪信息
 *
 * 6.对多异常的处理：
 *   a.声明异常时，建议声明更为具体的异常
 *   b.声明几个异常，就对应有几个catch块，不要定义多余的catch块，
 *     如果多个catch块中的异常出现继承关系，父类catch块放在最后面
 *   c.建立在进行catch处理时，catch中一定要定义具体处理方式
 *     不要简单定义一句e.printStackTrace(); ,
 *     也不要简单的就书写一条输出语句
 *
 * catch是用于处理异常，如果没有catch语句，则代表异常没有被处理过；
 * 如果该异常是检测时异常，那么必须声明。
 *
 */
class Demo2{
    //在功能上通过throws关键字声明了该功能有可能出现的问题
    int div(int a,int b)throws ArithmeticException,ArrayIndexOutOfBoundsException{
        int[] arr = new int[a];
        System.out.println(arr[4]);
        if (b == 0){
            //自定义异常，将toString显示的错误信息改成“被0除啦”
            throw new ArithmeticException("被0除啦");
        }
        return a/b;
    }
}

public class Begin26 {
    public static void main(String[] args){
        Demo2 d = new Demo2();
        try{
            int x = d.div(5,0);
            System.out.println("x="+x);
        }
//        catch (Exception e){  //父类catch块
//            System.out.println(e.toString());
//            System.exit(0); //系统退出，jvm结束
//        }

        /**多重捕获块
         * 在try语句后面添加任意数量的catch块。如果保护代码中发生异常，异常被抛给第一个catch块。
         * 如果抛出异常的数据类型与ExceptionType1匹配，它在这里就会被捕获。
         * 如果不匹配，它会被传递给第二个catch块。
         * 如此，直到异常被捕获或者通过所有的catch块。
         */
        catch (ArithmeticException e){
            System.out.println("除以0啦");
            System.out.println(e.getMessage());  //打印  异常信息
            System.out.println(e.toString());  //打印 异常名称：异常信息
            e.printStackTrace();  //打印  异常名称，异常信息，异常出现的位置
            //其实jvm默认的异常处理机制就是在调用printStackTrace方法，打印异常的堆栈的跟踪信息
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.toString());
            System.out.println("角标越界啦");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println("over");
    }
}
