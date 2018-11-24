/**
 * 知识点：模板方法
 * 需求：获取一个程序运行的时间
 * 原理：获取程序开始和结束的时间并相减
 * 获取时间：System.currentTimeMillis();
 *
 * 当代码完成优化后，就可以解决这类问题
 *
 * 什么是模板方法：
 * 在定义功能时，功能的一部分是确定的，但是有一部分是不确定的，而确定的部分在使用不确定的部分，
 * 那么这时就将不确定的部分暴露出去，由该类的子类去完成。
 */
//定义抽象类
abstract class GetTime{
    //final方法，目的是防止该方法中的内容被修改
    public final void getTime(){
        long start = System.currentTimeMillis();

        runcode();

        long end = System.currentTimeMillis();

        System.out.println("毫秒" + (end - start));
    }

    public abstract void runcode();
}

class SubTime extends GetTime{
    //复写父类的runcode方法（父类是个抽象类），只有复写了才能被使用
    public void runcode() {

        for (int x = 0; x < 4000; x++) {
            System.out.println(x);
        }
    }
}
public class Begin16 {
    public static void main(String[] args){
//        GetTime gt = new GetTime();
//        gt.getTime();
        SubTime gtt = new SubTime();  //创建SubTime的对象gtt
        gtt.getTime(); //调用方法
    }
}
