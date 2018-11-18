import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 知识点：IO（Input、Output）流、File类
 *
 * IO流：
 * 即输入、输出流，主要包括字节流和字符流
 * Java的序列化机制：使用序列化机制可以把内存中的Java的对象转变成二进制字节流。
 * 输入流：只能从中读取数据，不能向其中写入数据，
 * 输出流：只能向其中写入数据，不能向其中读入数据；
 * 字节流操作的数据单位是8位的字节，字符流操作的数据是16位的字符；
 *
 * 需求：在硬盘上创建一个文件并写入一些文字数据。
 * 找到一个专门用于操作文件的Writer子类对象：FileWriter。前缀名是父类名，后缀名是该流对象的功能。
 *
 * 字节流的两个基类：InputStream
 *                   OutputStream
 * 字符流的两个基类：Reader
 *                     \--FileReader
 *                     \--BufferedReader
 *                   Writer
 *                     \--FileWriter
 *                     \--BufferedWriter
 *
 *
 * File类：
 * File类是java.io包下代表与平台无关的文件和目录，
 * 如果希望在程序中操作（新建、删除、重命名）文件和目录都可以通过File类来完成，
 * File不能访问文件内容本身，只有输入输出流可以访问文件本身；
 *
 */
public class Begin50 {
    public static void main(String[] args) throws IOException {

        IOTest1();

//        consMethod();

        file();
    }

    public static void IOTest1() throws IOException{
        //创建一个FileWriter对象，该对象一被初始化就必须明确被操作的文件,
        //而且该文件会被创建到指定目录下；如果该目录下已有同名文件，则已有的同名文件将被覆盖
        //其实该步就是在明确数据要存放的目的地
//        FileWriter fw = new FileWriter("E:\\demo.txt");
        //传递一个true参数，代表不覆盖已有的文件，并在文件的末尾处进行文件的续写
        FileWriter fw = new FileWriter("E:\\demo.txt",true);    //FileWriter是OutputStreamEriter的一个子类

        //调用write方法，将字符串写入到流中
        fw.write("abcde\n");
        //调用flush方法刷新该流的缓冲，将缓冲区中临时存放的数据存入目的文件中
        fw.flush();  //此时demo.txt文件中出现数据
        //继续调用write方法和flush方法将继续给目的文件中写入数据

        //关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据，将数据刷到目的文件中
        //和flush的区别：flush刷新后流可以继续使用，close刷新后将会关闭流
        fw.close();



        //创建一个文件读取流对象，和指定名称的文件相关联
        //要保证该文件是已经存在的，如果不存在，会发生异常
        FileReader fr = new FileReader("E:\\demo.txt");

        //调用读取流对象的read方法
        //第一种方法：read();方法：一次读一个字符，而且会自动往下读,如果已到底文件的末尾，则返回-1
//        while (true){
//            int ch = fr.read();
//            if (ch==-1) {
//                break;
//            }
//            System.out.println("ch="+(char)ch);
//        }

//        int ch = 0;
//        while ((ch = fr.read())!=-1){
//            System.out.println("ch="+(char)ch);
//        }

        //第二种方法：通过字符数组进行读取
        //定义一个字符数组，用于存储读到的字符；该read(char[])返回的是读到的字符个数
        //
        char[] buf = new char[1024];  //字符很多很多的时候，字符数组的长度通常会定义成1024的整数倍
        int num = 0;
        while ((num = fr.read(buf))!=-1){
            System.out.println(new String(buf,0,num));
        }
        fr.close();

    }

    //创建File对象
    public static void consMethod() throws IOException {

        //将a.txt封装成File对象。可以将已有的和未出现的文件或者文件夹封装成对象
        File f1 = new File("E:\\a_1.txt");
        File f2 = new File("E:\\17070046","b_1.txt");  //目录和文件

        sop("f1="+f1);
        sop("f2="+f2);

        //在window等系统上，如果路径开头是盘符，则说明此路径是一个绝对路径
        System.out.println(f1.getPath());   //打印f1对象的路径名
        System.out.println(f1.getAbsoluteFile());   //打印f1对象的绝对路径
        System.out.println(f1.getAbsolutePath());   //打印f1对象所对应的绝对路径名
        File file = new File(".");      //以当前路径来创建一个File对象
        File tmpFile = File.createTempFile("aaa",".txt",file);    //在当前路径下创建一个临时文件
        tmpFile.deleteOnExit();     //指定当JVM退出时删除该文件
        String[] fileList = f1.list();      //使用list()方法列出当前路径下的所有文件和路径
        System.out.println("======当前路径下所有文件和路径如下=====");
        for (String filename : fileList){
            System.out.println(filename);
        }
        //listRoots()静态方法列出所有文件的磁盘根路径
        File[] roots = File.listRoots();
        System.out.println("=====系统所有根路径如下=====");
        for (File root : roots){
            System.out.println(root);
        }

    }
    public static void sop(Object obj){

        System.out.println(obj);
    }

    /**
     * 文件过滤器：
     * 在File类的list()方法中可接受一个FilenameFilter参数，通过该参数可以只列出符合条件的文件
     * FilenameFilter是一个接口，此接口里包含一个accept()方法，该方法将依次对指定File的所有子目录或者文件进行迭代
     * 若该方法返回true，则下面file（）方法中的list()方法会列出其子目录或者文件
     */
    //文件过滤器
    public static void file(){
        //以当前路径来创建一个File对象
        File file = new File(".");
        //使用Lambda表达式（目标类型FilenameFilter）实现文件过滤器
        //如果文件名以.java结尾，或者文件对应一个路径，则返回true
        String[] nameList = file.list((dir, name) -> name.endsWith(".java") || new File(name).isDirectory());
        for (String name : nameList){
            System.out.println(name);
        }
    }
}
