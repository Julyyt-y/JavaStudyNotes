import java.io.*;

/**
 * 知识点：字节流和字符流、处理流、节点流、转换流、推回输入流
 * 需求：想要操作图片数据、文档数据
 *
 * 字节流：InputStream（读）
 *               \--FileInputStream
 *               \--BufferedInputStream
 *         OutputStream（写）
 *               \--FileOutputStream
 *               \--BufferedOutputStream
 *
 * 字符流：Reader
 *            \--FileReader
 *            \--BufferedReader
 *        Writer
 *            \--FileWriter
 *            \--BufferedWriter
 *
 * 字节流和字符流的使用区别：
 * 通常来说，字节流的功能比字符流强大，因为计算机里所有数据都是二进制的，而字节流可以处理二进制文件。
 * 如果用字节流来处理文本文件，则需用合适的方式把字节转换成字符，这就增加了编程的复杂度。
 * 所以通常有如下规则：
 * 如果进行输入/输出的文件是文本内容，则考虑使用字符流；
 * 如果进行输入/输出的文件是二进制内容，则考虑使用字节流。
 *
 * 处理流printStream：
 * 功能：可以隐藏底层设备上结点流的差异，并对外提供更加方便的输入/输出方法；
 * 使用处理流的思路：使用处理流来包装节点流，程序通过处理流来执行输入/输出功能，让节点流与底层的I/O设备、文件交互。
 * （在创建处理流是传入一个节点流作为构造器参数即可）
 *
 * 结点流：
 * 字节流以字节数组为节点，字符流以字符数组为节点；
 *
 * 转换流：
 * 用于将字节流转换成字符流；
 * InputStreamReader将字节输入流转换为字符输入流，OutputStreamWriter将字节输出流转换成字符输出流。
 *
 * 推回输入流：
 * 有PushbackInputStream和PushbackReader；
 * 这两个推回流都有一个推回缓冲区，当程序调用这两个推回流的unread()方法时，
 * 系统将会把指定数组的内容推回到该缓冲区中，
 * 而推回输入流每次调用read()方法时总会先从推回缓冲区读取，
 * 只有完全读取了推回缓冲区的内容后，
 * 但还没有装满read()所需的数组时才会从原输入流中读取。
 *
 * BufferedReader BufferedWriter：
 * BufferedReader由Reader类扩展而来，提供通用的缓冲方式文本读取，readLine读取一个文本行，
 * 从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取。
 * BufferedWriter  由Writer 类扩展而来，提供通用的缓冲方式文本写入， newLine使用平台自己的行分隔符，
 * 将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
 *
 * 缓冲区：
 * 缓冲区的出现提高了对数据的读写效率；缓冲区要结合流才可以使用；在流的基础上对流的功能进行了增强。
 * 缓冲区的出现是为了提高流的操作效率，所以在创建缓冲区之前，必须要先有流对象。
 * 该缓冲区中提供了一个跨平台的换行符newLine();
 * 字符流缓冲区：该缓冲区提供了一次读一行的方法readLine，方便于对文本数据的获取
 * （readLine方法返回时只返回回车符之前的数据内容，并不返回回车符）
 * 当返回空时表示读到了文件的末尾。
 * 对应类：BufferedWriter
 *         BufferedReader
 *
 * FileInputStream和FileOutputStream：
 * 两个类的构造方法都需要指定文件的来源；
 * FileInputStream从文件系统中的某个文件中获得输入字节，获取的文件可用性取决于主机环境；
 * FileOutputStream向文件系统中的某个文件中获得输入字节，获取的文件可用性取决于主机环境。
 *
 * 【注】：
 * 计算机的文件常被分为文本文件和二进制文件两大类：
 * 所有能用记事本打开并看到其中字符内容的都是文本文件，反之则称为二进制文件。
 *
 * 练习：复制一个图片（毕向东238）
 *       演示MP3的复制，通过缓冲区（毕向东239）
 *
 */
public class Begin51 {
    public static void main(String[] args)throws IOException{
//        readFile_1();
//        readFile_2();
//        readFile_3();
//        InputStream();

//        writeFile_1();
//        writeFile_2();
//        writeFile_3();

//        printStream();

//        stringNode();

//        change();

//        pushback();

//        test();

//        copy_1();

//        copy_2();

        copyFile();

    }

    public static void readFile_1()throws IOException{
        FileInputStream fis = new FileInputStream("J:\\1707004615袁亚婷.docx");
        int ch = 0;
        while ((ch = fis.read())!=-1){
            System.out.println((char)ch);
        }
        fis.close();
    }

    //此方法适用于文件非常小，否则打印出的全是乱码
    public static void readFile_2() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\JAVA\\untitled\\src\\Begin51.java");    //创建字节输入流
        byte[] bbuf = new byte[1024];   //创建一个长度为1024的“竹筒”
        int hasRead = 0;    //用于保存实际读取的字节数
        while ((hasRead = fis.read(bbuf))>0){
            System.out.print(new String(bbuf,0,hasRead));     //取出“竹筒”中水滴（字节），将字节数组转化为字符串输出
        }
        fis.close();    //关闭文件
    }

    //数据太大时不建议使用这种方式，否则打印出的全是乱码
    public static void readFile_3()throws IOException{
        FileInputStream fis = new FileInputStream("E:\\JAVA\\untitled\\src\\Begin51.java");

//        int num = fis.available();
//        System.out.println("num="+num);

        byte[] buf = new byte[fis.available()];  //定义一个刚刚好的缓冲区，不用再循环了
        fis.read(buf);
        System.out.println(new String(buf));

        fis.close();
    }

    //向FileOutputStream类new的文件中写入数据（此数据是用户通过程序输入的）
    //（将文件中内容先清除再写入）
    public static void writeFile_1()throws IOException{
        FileOutputStream fos = new FileOutputStream("J:\\Java_Begin51.txt");

        fos.write("abcde".getBytes());

        fos.close();
    }

    //（将文件中内容先清除再写入）
    public static void writeFile_2() throws IOException{
        try(FileWriter fw = new FileWriter("J:\\Java_Begin51.txt")){
            fw.write("\t 锦瑟--李商隐\r\n");
            fw.write("锦瑟无端五十弦，一弦一柱思华年\r\n");
            fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃\r\n");
            fw.write("沧海月明珠有泪，蓝田日暖玉生烟\r\n");
            fw.write("此情可待成追忆，只是当时已惘然\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //从一个文件中读取内容并写入到另一个文件（将文件中内容先清除再写入）
    //如果J:\Java_Begin51.txt文件存在就直接写入，不存在就先创建后写入
    public static void writeFile_3(){
        try(FileInputStream fis = new FileInputStream("E:\\JAVA\\untitled\\src\\Begin51.java");
            FileOutputStream fos = new FileOutputStream("J:\\Java_Begin51.txt")) {

            byte[] bbuf = new byte[1024];
            int hasRead = 0;
            //从循环流中取出数据
            while ((hasRead = fis.read(bbuf))>0){
                fos.write(bbuf,0,hasRead);  //每读取一次，即写入文件输出流，读了多少就写多少
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理流的使用
    public static void printStream(){
        try(FileOutputStream fos = new FileOutputStream("J:\\test.txt");
            PrintStream ps = new PrintStream(fos)){
            //使用printStream执行输出
            ps.println("普通字符串");
            //直接使用printStream输出对象
            ps.println(new Begin51());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //节点流的使用
    public static void stringNode(){
        String src = "月亮的阴暗面越少"+ "," + "光华就越亮" + ",";
        char[] buffer = new char[32];
        int hasRead = 0;
        try (StringReader sr = new StringReader(src)){
            //采用循环读取方式读取字符串
            while ((hasRead = sr.read(buffer))>0){
                System.out.println(new String(buffer,0,hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try(
                //创建StringWriter时，实际上以一个StringBuffer作为输入结点
                //下面指定的20就是StringBuffer的初始长度
                StringWriter sw = new StringWriter()){
            //调用StringWriter的方法执行输出
            sw.write("愿你成为超级月亮，");
            sw.write("心怀坦荡，");
            sw.write("闪闪发光。");
            System.out.println("————下面是sw字符串结点里的内容————");
            //使用toString()方法返回StringWriter字符串结点内容
            System.out.println(sw.toString());
        }
        catch (IOException e){
                    e.printStackTrace();
        }
    }

    /**
     * BufferedReader具有缓冲功能,可以一次读取一行文本（以换行符为标志），
     * 如果没有读到换行符，则程序阻塞，等读到换行符时为止。
     */
    //转换流的使用
    //将键盘输入的文本转换成字符流
    public static void change(){
        try(
                //下面两行代码将System.in包装成BufferedReader
                //将System.in对象转换成Reader对象
                InputStreamReader reader = new InputStreamReader(System.in);
                //将普通的Reader包装成BufferedReader
                BufferedReader br = new BufferedReader(reader)){
            String line = null;
            //采用循环方式来逐行读取
            while ((line = br.readLine())!=null){
                //如果读取的字符为“exit”，则程序退出
                if (line.equals("exit")){
                    System.exit(1);
                }
                System.out.println("输入内容为："+ line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //推回输入流
    public static void pushback() throws IOException{
        try(
                //创建一个PushBackReader对象，指定推回缓冲区的的长度为64
                PushbackReader pr = new PushbackReader(new FileReader("J:\\Java_Begin51.txt"),64)){
            char[] buf = new char[32];
            String lastContent = "";    //用以保存上次读取的字符串内容
            int hasRead = 0;
            //循环读取文件内容
            while ((hasRead = pr.read(buf))>0){
                String content = new String(buf,0,hasRead); //将读取的内容转换成字符串
                int targetIndex = 0;
                //将上次读取的字符串和本次读取的字符串拼起来
                //查看是否包含目标字符串，如果包含目标字符串，则将本次内容与上次内容一起推回缓冲区，
                //再次读取指定长度的内容（就是目标字符串之前的内容）
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader"))>0){
                    pr.unread((lastContent + content).toCharArray());   //将本次内容与上次内容一起推回缓冲区
                    if (targetIndex > 32){
                        buf = new char[targetIndex];    //重新定义一个长度为targetIndex的数组
                    }
                    pr.read(buf,0,targetIndex);     //再次读取指定长度的内容（就是目标字符串之前的内容）
                    System.out.print(new String(buf,0,targetIndex));
                    System.exit(0);
                }
                else {
                    System.out.print(lastContent);  //打印上次读取的内容
                    lastContent = content;      //将本次内容设为上次读取的内容
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void test()throws IOException{
        //创建一个字符写入流对象
        FileWriter fw = new FileWriter("E:\\buf.txt");
        //为了提高字符写入流效率，加入了缓冲技术
        //只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可
        BufferedWriter bufw = new BufferedWriter(fw);
        bufw.write("abcde");
        bufw.newLine();//换行
        //只要用到缓冲区，就一定要刷新
        bufw.flush();
        //关闭缓冲区：其实关闭缓冲区就是在关闭缓冲区中的流对象
        //不用写fw.close();
        bufw.close();

        //字符读取流缓冲区
        //创建一个读取流对象和文件相关联
        FileReader fr = new FileReader("E:\\buf.txt");
        //为了提高效率，加入缓冲技术，将字符流读取对象作为参数传递给缓冲对象的构造函数
        BufferedReader bufr = new BufferedReader(fr);
        //一行一行地读
//        String s1 = bufr.readLine();
//        System.out.println("s1:"+s1);
        String line = null;
        while ((line=bufr.readLine()) != null){
            System.out.println(line);
        }
        bufr.close();
    }

    public static void copy_1() throws IOException {
        long start = System.currentTimeMillis();
        copy_1();
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"毫秒");
    }
    //通过字节流的缓冲区完成复制
    public static void copy_2()throws IOException{
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("E:\\17070046\\2017团日活动文件\\中北大学 17070046微视频.mp4"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("E:\\团日活动.mp4"));
        int by = 0;
        while ((by=bufis.read())!=-1){
            bufos.write(by);
        }
        bufos.close();
        bufis.close();
    }

    //通过缓冲区复制文件
    public static void copyFile(){
        BufferedReader bufr = null;
        BufferedWriter bufw = null;
        try {
            bufr = new BufferedReader(new FileReader("E:\\BufferedDemo.java"));
            bufw = new BufferedWriter(new FileWriter("E:\\bufferWriter_copy.txt"));
            String line = null;
            while ((line=bufr.readLine())!=null){
                bufw.write(line);
                bufw.newLine();
                bufw.flush();
            }
        }
        catch (IOException e){
            throw new RuntimeException("读写失败");
        }
        finally {
            try {
                if (bufr!=null){
                    bufr.close();
                }
            }
            catch (IOException e){
                throw new RuntimeException("读取关闭失败");
            }
            try {
                if (bufw!=null){
                    bufw.close();
                }
            }
            catch (IOException e){
                throw new RuntimeException("写入关闭失败");
            }
        }
    }
}
