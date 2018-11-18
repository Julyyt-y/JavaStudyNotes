/**
 * 知识点：多线程---同步函数
 * 需求：银行有一个金库，有两个储户分别存300元，每次存100，存3次
 * 目的：该程序是否有安全问题，如果有，如何解决
 *
 * 如何找问题：
 *   1.明确哪些代码是多线程运行代码
 *   2.明确共享数据
 *   3.明确多线程运行代码中哪些语句是操作共享数据的
 *
 * 同步函数用的是哪一个锁：
 * 函数需要被对象调用，那么函数都有一个所属对象引用，就是this
 * 所以同步函数使用的锁是this（同步方法的同步监视器是this）
 * 但是，在同步函数被静态修饰后，使用的锁不是this（因为静态方法中也不可以定义this）
 * 静态进内存时，内存中本没有对象，但是一定有该类对应的字节码文件对象（类名.class）
 * 该对象的类型是Class
 * 静态的同步方法使用的锁是该方法所在类的字节码文件对象（类名.class）
 *
 * 同步代码块和同步函数有什么不同：
 *
 *
 */

class Bank1{
    private int sum;
    Object obj = new Object();
    public void add(int n) {
        //使用synchronized将需要互斥的代码包含起来，并上一把锁；
        //这样可以让add方法被一个线程完整的执行完再切换到下一个线程；
        //这把锁必须是需要互斥的多个线程间的共享对象，此程序中只是例举它怎么用，此代码其实无意义

        synchronized (obj) {
            sum = sum + n;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            System.out.println("sum + " + sum);
        }
    }
}

//同步函数
class Bank {
    private int sum;
    //同步方法就是用synchronized修饰的方法
    public synchronized void add(int n) {  //同步函数
        sum = sum + n;
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        System.out.println("sum + " + sum);
    }
}

class Cus implements Runnable{
    private Bank b = new Bank();

    @Override
    public void run() {
        for (int i = 0;i < 3;i ++){
            b.add(100);
        }
    }
}
public class Begin35 {
    public static void main(String[] args){
        Cus c = new Cus();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}
