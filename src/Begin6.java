/**
 * 知识点：进制转换
 * 需求：无
 */

public class Begin6 {
    public static void main(String[] args){
        toBin(6);

        toHex(564);
        tohex(345);
        ToHex(60);
        System.out.println( );
        ToHex(-60);
    }

    /**
     * 十进制转化为 2 进制
     */
    public static void toBin(int num){
        StringBuffer sb = new StringBuffer(); // 装数据的容器，但此容器会将得到的数据进行反转
        while(num > 0){
            sb.append(num % 2);  //此容器中提供的一种添加的方法，算一个数加一个数
            num =num / 2;
        }
        System.out.println(sb);
        System.out.println(sb.reverse());  //将数据反转回来
    }

    /**
     * 十进制转化为 2 进制 ：查表法
     */
    public static void tobin(int num){
        char[] chs = {'0','1'};
        int p = chs.length;
        while(num != 0){
            int temp = num & 1;
            chs[--p] = chs[temp];
            num = num >>>1;
        }
        System.out.println("p="+p);
    }

    /**
     * 十进制转化为 16 进制(1)
     */
    public static void toHex(int num){
        for(int x = 0;x < 8;x ++){
            int temp = num & 15;
            if(temp > 9)
                System.out.print((char)(temp - 10 + 'A'));
            else
                System.out.print(temp);

            num = num >>> 4;
        }
        System.out.println("\n");
    }

    /**
     * 十进制转化为 16 进制(1)
     */
    public static void tohex(int num){
        StringBuffer sb = new StringBuffer();

        for (int x = 0;x < 8;x ++){
            int temp = num & 15;
            if (temp > 9)
                sb.append((char)(temp - 10 + 'A'));
            else
                sb.append(temp);

            num = num >>> 4;
        }
        System.out.println(sb.reverse());
    }

    /**
     *十进制转化为 16 进制 ：查表法
     */
    public static void ToHex(int num){
        char[] chs = {'0','1','2','3',
                      '4','5','6','7',
                      '8','9','A','B',
                      'C','D','E','F'};
        for (int i = 1;i < 8;i ++){
            int temp = num & 15;
            num = num >>> 4;
            System.out.print(chs[temp]);
        }
    }
}
