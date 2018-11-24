import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 知识点：创建并使用自定义的类加载器、URLClassLoader类
 * 具体操作：
 * 先通过重写findCLass()方法来实现自定义的类加载机制，
 * 再调用ClassLoader的defineClass()来加载这个Class文件，并生成相应的Class对象。
 *
 */
public class Begin60 extends ClassLoader{
    //读取一个文件的内容
    private byte[] getBytes(String filename) throws IOException{
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        try(FileInputStream fin = new FileInputStream(file)){
            //一次性读取Class.read()的全部二进制数据
            int r = fin.read(raw);
            if (r != len){
                throw new IOException("无法读取全部文件：" + r + "!=" + len);
            }
            return raw;
        }
    }

    //定义编译指定Java文件
    private boolean compile(String javaFile) throws IOException{
        System.out.println("CompileClassLoader：正在编译" + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac" + javaFile);//调用系统的javac命令
        try{
            //其他线程都在等待这个线程完成
            p.waitFor();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        int ret = p.exitValue();    //获取javac线程的退出值
        return ret == 0;
    }

    /**重写ClassLoader的findClass方法,可实现自定义的类的加载机制*/
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class clazz = null;
        //将包路径中的点（.）替换成斜线（/）
        String fileStub = name.replace(".","/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        //当指定Java源文件存在，且Class文件不存在，
        //或者Java源文件的修改时间比Class文件的修改时间更晚时，重新编译
        if (javaFile.exists() &&
                (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())){
            try {
                //如果编译失败，或者该Class文件不存在
                if (!compile(javaFilename) || !classFile.exists()){
                    throw new ClassNotFoundException("ClassNotFoundException：" + javaFilename);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        //如果Class文件存在，系统负责将该文件转换成Class对象
        if (classFile.exists()){
            try {
                byte[] raw = getBytes(classFilename);   //将Class文件的二进制数据读入数组
                //调用ClassLoader的defineClass方法将二进制数据转换成Class对象
                clazz = defineClass(name,raw,0,raw.length);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        //如果clazz为null，表明加载失败，则抛出异常
        if (clazz == null){
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception{
        //如果允许该方法时没有参数，即没有目标类
        if (args.length < 1){
            System.out.println("缺少目标类，请按如下格式运行Java源文件");
            System.out.println("java Begin60 ClassName");
        }
        //第一个参数是需要运行的类
        String progClass = args[0];
        //剩下的这些参数将作为运行目标类时的参数，将这些参数复制到一个新数组中
        String[] progArgs = new String[args.length-1];
        System.arraycopy(args,1,progArgs,0,progArgs.length);
        Begin60 ccl = new Begin60();
        //加载需要运行的类
        Class<?> clazz = ccl.loadClass(progClass);
        //获取需要运行的类的主方法
        //使用反射来调用方法
        Method main = clazz.getMethod("main",(new String[0].getClass()));
        Object argsArray[] = {progArgs};
        main.invoke(null,argsArray);
    }
}
