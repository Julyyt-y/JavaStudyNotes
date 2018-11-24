public class Test11 {
    public static void main(String[] args){
        method_1();
    }
    public static void method_1(){

        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1.equals(i2));
        System.out.println(i1 == i2);
        String s1 = "androidLab";
        String s2 = "androidLab";
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        String s3 =  new String("androidLab");
        String s4 = new String("androidLab");
        System.out.println(s3.equals(s4));
        System.out.println(s3 == s4);
    }
}
