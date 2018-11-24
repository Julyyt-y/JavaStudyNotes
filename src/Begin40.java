/**
 * 知识点：多线程---停止线程、后台线程
 *
 * 如何停止线程？
 * stop方法已经过时，现只有一种：run方法结束线程
 * 开启多线程运行，代码通常是循环结构,
 * 只要控制住循环，就可以让run方法结束。也就是让线程结束
 *
 * 特殊情况：
 * 当线程处于冻结状态，就不会读取到标记，那么线程就不会结束
 * 当没有指定的方式让冻结的线程恢复到运行状态时，这时需要对冻结进行清除，
 * 强制让线程恢复到运行状态中来，这样就可以操作标记让线程结束
 *
 * Thread类中提供了该方法：interrupt();
 *
 * 后台线程：
 * setDaemon()，
 * 所有前台线程执行完后后台线程会自动关闭，
 * 当后台线程被关闭时，资源的回收是立即的，不等待的，也不考虑后台线程是否执行完成，就算是正在执行中也立即被终止，
 * 后台线程一般用于处理时间较短的任务，比如处理客户端发过来的请求信息
 *
 */

/*
class StopThread implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName()+"...run");
        }
    }

    public void changeFlag(){
        flag = false;
    }
}
public class Begin40 {
    public static void main(String[] args){
        StopThread st = new StopThread();

        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);

        t1.start();
        t2.start();

        int num = 0;
        while (true){
            if (num ++ ==60){
                st.changeFlag();
                break;
            }
            System.out.println(Thread.currentThread().getName()+"......"+num);
        }
        System.out.println("over");
    }
}
*/


class StopThread implements Runnable{
    private boolean flag = true;
    @Override
    public synchronized void run() {
        while (flag){
            try {
                wait();
            } catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+".....Exception");
                flag = false;
            }
            System.out.println(Thread.currentThread().getName()+"...run");
        }
    }

    public void changeFlag(){
        flag = false;
    }
}
public class Begin40 {
    public static void main(String[] args){
        StopThread st = new StopThread();

        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);

        t1.start();
        t2.start();

        int num = 0;

        while (true){
            if (num ++ ==60){
                t1.interrupt();
                t2.interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName()+"......"+num);
        }
        System.out.println("over");
    }
}