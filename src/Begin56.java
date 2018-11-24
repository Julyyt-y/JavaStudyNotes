import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.SortedMap;

/**
 * 知识点：NIO（Java新IO）
 *
 * Java新IO采用内存映射文件的方式来处理输入/输出流，新IO将文件或文件的一段区域映射到内存中，
 * 这样就可以像访问内存一样来访问文件了。
 *
 * 新IO的两个核心对象：Channel（通道）和 Buffer（缓冲）
 * Channel是对传统输入/输出系统的模拟，新IO中的数据都需要通道进行传输（程序不能直接访问Channel中的数据）；
 * Channel与传统InputStream和OutputStream不同的地方是，Channel提供了一个map()方法，该方法可以将“一块数据”映射到内存中；
 * 如果说传统的输入/输出是面向流的处理，则新IO则是面向块的处理。
 * Buffer可以理解成一个容器，它的本质是一个数组；
 * 发送到Channel中的所有对象必须先放入Buffer中，而从Channel中读取的数据也必须先放入Buffer中。
 *
 * ByteBuffer还提供了一个allocateDirect()方法（直接Buffer，其他的称为普通Buffer），但直接Buffer只适用于长生存期的Buffer
 *
 * 字符集和Charset：
 * 字符集就是为每个字符编个号
 *
 */
public class Begin56 {
    public static void main(String[] args)throws IOException{

//        bufferTest();

//        fileChannelTest();

//        randomFileChannelTest();

//        readFile();

        allCharset();
    }

    //使用Buffer
    public static void bufferTest(){
        CharBuffer buff = CharBuffer.allocate(8);   //创建Buffer
        System.out.println("capacity：" + buff.capacity());  //容量是8个数据
        System.out.println("limit：" + buff.limit());    //界限是8（8后面没有既不可被读，也不可被写）
        System.out.println("position：" + buff.position());  //位置是0（相当于记录指针的位置是0）
        //放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');
        System.out.println("加入三个元素后，position = ：" + buff.position());   //位置指向3
        buff.flip();    //调用flip()方法，该方法的作用是将limit设置为position所在的位置,并将position设为0
        System.out.println("执行flip()方法后，limit = " + buff.limit());  //所以界限变成了3
        System.out.println("position = " + buff.position());    //position变成了0
        System.out.println("第一个元素（position=0）：" + buff.get());//取出第一个元素
        System.out.println("取出第一个元素后，position = " + buff.position());   //位置是1
        buff.clear();   //调用clear方法，该方法的作用是将position置为0，将limit置为capacity（容量）
        System.out.println("执行clear()方法后，limit = " + buff.limit());     //limit是8
        System.out.println("执行clear()方法后,position = " + buff.position());   //位置是0
        System.out.println("执行clear()方法后，缓冲区内容并没有被清除：" + "第三个元素为：" + buff.get(2));//第三个元素是c（第三个元素的位置为2）
        System.out.println("执行绝对读取后，position = " + buff.position());    //位置依旧是0
    }

    //使用Channel（此处主要是FileChannel的使用展示）
    //将Begin56.java里面的内容复制到J:\FileChannelTest.txt中
    public static void fileChannelTest(){
        File f = new File("E:\\JAVA\\untitled\\src\\Begin56.java");
        try(
                //创建FileInputStream，以该文件输入流创建FileChannel
                FileChannel inChannel = new FileInputStream(f).getChannel();
                //以文件输出流创建FileChannel，以控制输出
                FileChannel outChannel = new FileOutputStream("J:\\FileChannelTest.txt").getChannel()){
            //将FileChannel里的全部数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            Charset charset = Charset.forName("GBK");   //使用GBK的字符来创建解码器
            outChannel.write(buffer);   //直接将Buffer里的数据全部输出
            buffer.clear();     //再次调用buffer的clear()方法，复原limit、position的位置
            CharsetDecoder decoder = charset.newDecoder();  //创建解码器（CharsetDecoder）对象
            CharBuffer charBuffer = decoder.decode(buffer);     //使用解码器将ByteBuffer转换成CharBuffer
            System.out.println(charBuffer);     //CharBuffer的toString()方法可以获取对应的字符串
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //以追加方式复制文件
    public static void randomFileChannelTest() throws IOException{
        File f = new File("J:\\FileChannelTest.txt");
        try(
                //创建一个RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(f,"rw");
                //获取RandomAccessFile的对象Channel
                FileChannel randomChannel = raf.getChannel()){
            //将Channel中的所有数据映射成ByteBuffer
            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            randomChannel.position(f.length()); //吧Channel的记录指针移动到最后
            randomChannel.write(buffer);    //将buffer中的数据输出
        }
    }

    //采用“多次取水”的方式复制文件
    public static void readFile() throws IOException{
        try(
                FileInputStream fis = new FileInputStream("J:\\FileChannelTest.txt");
                FileChannel fcin = fis.getChannel()){
            ByteBuffer bbuf = ByteBuffer.allocate(256);
            while(fcin.read(bbuf)!=-1){
                bbuf.flip();
                Charset charset = Charset.forName("GBK");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cbuff = decoder.decode(bbuf);
                System.out.println(cbuff);
                bbuf.clear();
            }
        }
    }

    //获取当前Java所支持的全部字符集
    public static void allCharset(){
        SortedMap<String,Charset> map = Charset.availableCharsets();
        for(String alias:map.keySet()){
            System.out.println(alias + "----->" + map.get(alias));
        }
    }
}
