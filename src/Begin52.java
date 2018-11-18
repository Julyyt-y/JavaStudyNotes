import java.io.*;

/**
 * 知识点：FileReader、FileWriter和LineNumberReader
 * 需求：1、拷贝文本文件，将C盘的一个文本文件复制到D盘
 *       2、模拟一个带行号的缓冲区对象（毕向东236），将指定文本文件读出定打印到屏幕上
 *
 * 复制的原理：将C盘下的文件数据存储到D盘的一个文件中
 * 步骤：1.在D盘创建一个文件，用于存储C盘文件的数据；
 *       2.定义读取流和C盘文件关联；
 *       3.通过不断的读写来完成数据的存储；
 *       4.关闭资源
 */
public class Begin52 {
    public static void main(String[] args)throws IOException{

//        copy_1();

//        copy_2();

//        FileReader_1();

        LineNumberReader();
    }

    //每次读1024个char字符
    public static void copy_2(){
        FileWriter fw = null;
        FileReader fr = null;
        try {
            fw = new FileWriter("D:\\dddddd.txt");
            fr = new FileReader("E:\\DEMO2.txt");
            char[] buf = new char[1024];
            int len = 0;
            while ((len=fr.read(buf))!=-1){
                fw.write(buf,0,len);
            }
        } catch (IOException e){
            throw new RuntimeException("读写失败");
        }
        finally {
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e){

                }
                if (fw != null){
                    try {
                        fw.close();
                    }
                    catch (IOException e){

                    }
                }
            }
        }
    }

    //从E盘读一个字符，就往D盘写一个字符
    public static void copy_1()throws IOException{
        //创建目的地
        FileWriter fw = new FileWriter("D:\\DEMOoo.txt");

        //与已有文件关联
        FileReader fr = new FileReader("E:\\DEMO1.txt");
        int ch = 0;
        while ((ch=fr.read())!=-1){
            fw.write(ch);
        }
        fw.close();
        fr.close();
    }

    //FileReader的其它用法演示
    //程序最后使用了自动关闭资源的try语句来关闭文件输入流，这样保证了输入流一定会被关闭
    public static void FileReader_1() throws IOException {
        try(FileReader fr = new FileReader("E:\\JAVA\\untitled\\src\\Begin51.java")) {
            char[] cbuf = new char[32];     //创建一个长度为32的“竹筒”
            int hasRead = 0;    //用于保存实际读取的字符数
            //创建循环来重复“取水”
            while ((hasRead = fr.read())>0){
                System.out.print(new String(cbuf,0,hasRead));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //LineNumberReader的使用
    public static void LineNumberReader() throws IOException {
        FileReader fr = new FileReader("J:\\Java_Begin51.txt");  //E盘中必须有文件
        LineNumberReader lnr = new LineNumberReader(fr);

        String line = null;
        while ((line=lnr.readLine())!=null){
            System.out.println(lnr.getLineNumber()+"..."+line);  //getLineNumber获取行号
        }
        lnr.close();
    }
}
