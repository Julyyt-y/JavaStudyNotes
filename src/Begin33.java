/**
 * 知识点：多线程
 *
 * 用多线程的目的只有一个，就是更好的利用CPU的资源
 *
 * 进程：是一个正在执行中的程序，每一个进程执行都有一个执行顺序，该顺序是一个执行路径，或者叫一个控制单元
 * （每一个应用程序只要启动，都会在内存空间中分配一片空间，内存都会分配一个地址，
 *  而地址其实就是用于定义空间、标识空间，用于封装内存中一些控制单元）
 *
 * 线程：就是进程中一个独立的控制单元，线程在控制着进程的执行
 *      一个进程中至少有一个线程
 *
 * Java VM  启动的时候会有一个进程Java.exe，该进程中至少有一个线程负责Java程序的执行，
 * 而且这个线程运行的代码存在于main方法中，该线程称之为主线程
 * 扩展：其实更细节说明jvm，jvm启动不止一个线程，还有负责垃圾回收机制的线程
 *
 * 重点：如何在自定义的代码中，自定义一个线程？
 *       创建线程的第一种方式：继承Thread类,步骤：
 *       1.定义类继承Thread
 *       2.复写Thread类中的run方法（目的：将自定义的代码存储在run方法中，让线程运行）
 *       3.该方法有两个作用：启动线程，调用run方法
 *       4.start方法：开启线程并运行（其实是使线程进入就绪状态）
 *
 *       创建线程的第二种方式：实现Runnable接口：
 *       1.定义类实现Runnable接口
 *       2.覆盖Runnable接口中的run方法，将线程要运行的代码存放在该run方法中
 *       3.通过Thread类建立线程对象
 *       4.将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 *         为什么要将Runnable接口的子类对象传递给Thread的构造函数？
 *         因为自定义的run方法所属的对象是Runnable接口的子类对象
 *         所以要让线程去指定指定对象的run方法，就必须要明确该方法所属对象
 *       5.调用Thread类的start方法开启线程并调用Runnable接口子类的run方法
 *
 * 多线程的一个特性：随机性
 * 发现每次运行结果都不同，因为多个线程都获取CPU的执行权，CPU执行到谁，就运行谁，
 * 在某一个时刻，只能有一个程序在运行（多核除外）
 * CPU在做着快速的切换，以达到看上去是同时运行的效果
 * 我们可以形象的把多线程的运行形容为互相抢夺CPU的执行权，谁抢到谁执行，至于执行多长时间，CPU说了算
 *
 * 为什么要覆盖run方法：
 * Thread类用于描述线程，该类就定义了一个功能，用于存储线程要运行的代码，该存储功能就是run方法，
 * 也就是说，Thread类中的run方法是用于存储线程要运行的代码
 *
 * static Thread currentThread();获取当前线程对象
 * getName();获取线程名称
 * 设置线程名称：setName或者构造函数
 *
 * 实现方式和继承方式有什么区别：
 * 实现方式的好处：避免了单继承的局限性
 * 在定义线程时，建议使用实现方式
 * 两种方式的区别：
 * 继承Thread：线程代码存放在Thread子类的run方法中
 * 实现Runnable：线程代码存放在接口的子类的run方法中
 *
 * 多线程运行可能会出现安全问题：
 * 出现问题的原因：
 * 当多条语句在操作同一线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完，
 * 另一个线程就参与进来执行，导致共享数据的错误
 * 解决办法：对多条操作共享数据的语句，只能让一个线程都执行完，且在执行过程中，其他线程不可以参与执行
 *
 * Java对多线程的安全问题提供了专业的解决方式，就是同步代码块
 * 同步代码块：
 * （需要同步的代码：需要共享操作的语句）
 *          synchronized(对象){
 *              需要被同步的代码
 *          }
 * 对象如同锁，持有锁的线程可以在同步中执行，
 * 没有持有锁的线程即使获取CPU的执行权，也进不去，因为没有获取锁
 *
 * 同步的前提：
 *   1.必须要有两个或多个线程
 *   2.必须是多个线程使用同一个锁
 * 必须保证同步中只能有一个线程在运行
 * 同步的好处：解决了多线程的安全问题
 * 同步的弊端：多个线程都需要判断锁，较为消耗资源
 *
 */

//class Demo6 extends Thread{
//    //自定义线程
//    public void run(){
//        for (int i = 0;i < 60;i ++)
//            System.out.println("demo_6 run"+i );
//    }
//}
//public class Begin33 {
//    public static void main(String[] args){
//        Demo6 d = new Demo6();  //创建好一个线程
//        //同步代码块，此处没有让此线程先执行完是因为持有锁的线程可以在同步中执行，而d对象没有锁
//        synchronized (d){
//            d.start();
//        }
////        d.start();  //开启线程并执行该线程的run方法，只有start方法才能开启线程
////        d.run();  //仅仅是对象调用方法，而线程创建了却并没有执行，run方法是用于封装线程运行的代码
//
//        for (int j = 0;j < 60;j ++)
//            System.out.println("hello world"+j);
//        //两个线程同时进行,CPU不停地在切换每一个进程中的线程
//    }
//}

/** 从运行结果来看，先执行的构造函数，再执行的start方法，最后执行的run方法。
 * 而且，是等所有的构造函数先执行完了再执行所有的start，以此类推
 */
class RunnableDemo implements Runnable{

    private Thread t;
    private String name;

    //构造函数（构造器），用于设置该线程的名字
    RunnableDemo(String name){
        this.name = name;
        System.out.println("Creating" + name);
    }
    //重写run方法，定义线程执行体，run方法的方法体就是线程的执行体
    @Override
    public void run(){
        System.out.println("Running" + name);
        try{
            for (int i=4;i>0;i--){
                System.out.println("Thread" + name + "，" + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread" + name + "interrupted。");
        }
        System.out.println("Thread" + name + "exiting。");
    }

    public void start(){
        System.out.println("Starting" + name);
        if (t == null){
            t = new Thread(this,name);
            t.start();
        }
    }
}
public class Begin33{

    public static void main(String args[]){
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }
}