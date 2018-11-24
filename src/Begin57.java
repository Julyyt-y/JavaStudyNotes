import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;
import java.util.Scanner;

/**
 * 知识点：IO异常的处理方式、异常的日志信息、Java虚拟机读取其他进程的数据、文件锁
 * 需求：将异常信息保存到文件中
 *
 * 文件锁
 * 使用文件锁可以有效地阻止多个进程并发修改同一个文件；
 * 文件锁控制文件的全部或部分字节访问；
 * lock()和tryLock()方法的区别：
 * 当lock()试图锁定某个文件时，如果无法得到文件锁，则程序将会一直阻塞，
 * 而tryLock()是尝试锁定文件，它将直接返回而不是阻塞，如果获得了文件锁，该方法返回文件锁，否则返回null。
 * lock(long position, long size, boolean shared){}
 * tryLock(long position, long size, boolean shared){}
 * 当shared为true时，表明该所是一个共享锁，它将允许多个进程来读取文件，但阻止其他进程获得对该文件的排他锁；
 * 当shared为false时，表明该所是一个排他锁，它将锁住对该文件的读写。
 *
 */
public class Begin57 {
    public static void main(String[] args)throws IOException,Exception{
//        readFromProcess();

//        writeToProcess();

        fileLockTest();
    }

    public static void IOExceptionDeal() throws IOException{
        FileWriter fw = null;
        try {
            fw = new FileWriter("E:\\demo.txt");
            fw.write("abcdefgh");
        }catch (IOException e){
            System.out.println("catch:"+e.toString());
        }
        finally {
            try {
                if (fw != null){
                    fw.close();
                }
            }
            catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }

    public static void IOExceptionLog(){
        try {
            int[] arr = new int[2];
            System.out.println(arr[3]);
        }
        catch (Exception e){
//            e.printStackTrace(new PrintStream("E:\\a.txt"));

            try {
                //打印时间和日志
                Date d = new Date();
                PrintStream ps = new PrintStream("E:\\exception.log");
                ps.println(d.toString());
                System.setOut(ps);
            }
            catch (IOException e1){
                throw new RuntimeException("日志文件创建失败");
            }
            e.printStackTrace(System.out);
        }
    }

    //需求：读取其他进程的输出信息
    public static void readFromProcess()throws IOException{
        Process p = Runtime.getRuntime().exec("javac");     //运行javac命令，返回运行该命令的子进程
        try(
                //以p进程的错误流创建BufferedReader对象，这个错误流对本程序是输出流，对p进程是输出流
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))){
            String buff = null;
            //采取循环方式来读取p进程的错误输出
            while ((buff = br.readLine())!=null){
                System.out.println(buff);
            }
        }
    }

    public static void writeToProcess()throws IOException{
        //运行"java ReadStandard"命令，返回运行该命令的子进程
        Process p = Runtime.getRuntime().exec("java ReadStandard");
        try(
                //以这个p进程的输出流创建PrintStream对象，这个输出流对本程序是输入流，对p进程是输出流
                PrintStream ps = new PrintStream(p.getOutputStream())){
            //向ReadStandard程序中写入内容，这些内容被ReadStandard读取
            ps.println("普通字符串");
            ps.println(new Begin55());
        }
    }

    //文件锁
    public static void fileLockTest() throws Exception{
        try(
                //使用FileOutputStream获取FileChannel
                FileChannel channel = new FileOutputStream("J:\\a.txt").getChannel()){
            FileLock lock = channel.tryLock();
            Thread.sleep(10000);
            lock.release();
        }
    }
}

//定义一个ReadStandard类，该类可以接收标准输入，并将标准输入写入J:\out.txt中
class ReadStandard{
    public static void main(String[] args){
        try(
                //使用System.in创建Scanner对象，用于获取标准输入
                Scanner sc = new Scanner(System.in);
                PrintStream ps = new PrintStream(new FileOutputStream("J:\\out.txt"))){
            sc.useDelimiter("\n");      //增加下面一行只把回车符作为分隔符
            //判断是否还有下一个输入项
            while (sc.hasNext()){
                ps.println("键盘输入的内容是：" + sc.next());
                if ("over".equals(sc)){
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
