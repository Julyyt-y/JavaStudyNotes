import java.io.*;
import java.util.Scanner;

/**
 * 知识点：读取键盘录入（重定向标准输入输出）
 * 需求：通过键盘录入数据。
 * 当录入一行数据后，就将该行数据大写打印出来，当录入的数据是over时，就停止录入
 *
 * System.out：对应的是标准输出设备（控制台或屏幕）
 * System.in：对应的是标准输入设备（键盘）
 *
 * 1.源：键盘录入
 *   目的地：控制台
 * 2.源：键盘录入
 *   目的地：文件
 * (把键盘录入的数据存储到文件中)
 * 3.源：文件
 *   目的：控制台
 * (把一个文件中的数据打印在控制台上)
 *
 *
 *
 * 改变标准输入输出设备：setIn();    setOut();
 *
 * 流操作的基本规律：（毕向东244-245）
 */
public class Begin53 {
    public static void main(String[] args)throws IOException{
//        test1();
//        test2();
//        test3();
//        test4();
        test5();

    }
    public static void test1()throws IOException{
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder(); //定义一个容器
        while (true){
            int ch = in.read();
            if (ch=='\r'){
                continue;
            }
            if (ch=='\n'){
                String s = sb.toString();
                if ("over".equals(s)){
                    break;
                }
                System.out.println(s.toUpperCase());
                sb.delete(0,sb.length());  //清空缓冲区
            }
            else
                sb.append((char)ch);
        }
    }

    //将字节流转成字符流再使用字符流缓冲区（这里是BufferedReader）的readLine方法
    public static void test2()throws IOException{
        //获取键盘录入对象
        InputStream in = System.in;
        //InputStreamReader将字节流对象转成字符流对象
        InputStreamReader isr = new InputStreamReader(in);
//        //使用字符流缓冲区（这里是BufferedReader），这里是将二进制文件使用字符流缓冲区
//        BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\DEMO1.txt")));
        //为提高效率，将字符流进行缓冲区技术高效操作，使用BufferedReader，这里是将键盘输入的文件使用字符流缓冲区
        BufferedReader bufr1 = new BufferedReader(isr);
        String line = null;
        while ((line=bufr1.readLine())!=null){
            if ("over".equals(line)){
                break;
            }
            System.out.println(line.toUpperCase());
        }
        bufr1.close();
    }

    public static void test3()throws IOException{
        //获取键盘录入对象
        InputStream in = System.in;
        //将字节流对象转成字符流对象，使用InputStreamReader
        InputStreamReader isr = new InputStreamReader(in);
        //使用字符流缓冲区
        BufferedReader bufr = new BufferedReader(isr);
        OutputStream out = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bufw = new BufferedWriter(osw);

        BufferedWriter bufw1 = new BufferedWriter(new OutputStreamWriter(System.out));   //目的地：控制台

        BufferedWriter bufw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\out.txt")));//目的地：文件

        BufferedReader bufr1 = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\DEMO1.txt")));
        BufferedWriter bufw4 = new BufferedWriter(new OutputStreamWriter(System.out));
        //源：文件；目的地：控制台

        String line = null;
        while ((line=bufr.readLine())!=null){
            if ("over".equals(line)){
                break;
            }
            bufw.write(line.toUpperCase());
            bufw.newLine();
            bufw.flush();
        }
        bufr.close();
    }

    public static void test4()throws IOException{
        try(FileInputStream fis = new FileInputStream("E:\\JAVA\\untitled\\src\\Begin53.java")){
            System.setIn(fis);      //将标准流输入重定向到fis输入流
            Scanner sc = new Scanner(System.in);    //使用System.in创建Scanner对象，用于获取标准输入
            sc.useDelimiter("\n");      //增加此行只把回车作为分隔符
            while (sc.hasNext()){
                System.out.println("键盘输入的内容是" + sc.next());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void test5() throws IOException{
        try(
                //一次性创建PrintStream输出流
                PrintStream ps = new PrintStream(new FileOutputStream("J:\\Begin53.txt"))){
            System.setOut(ps);      //将标准输出重定向到ps输出流
            System.out.println("普通字符串");    //想标准输出输出一个字符串
            System.out.println(new Begin53());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
