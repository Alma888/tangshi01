package lab;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class 求SHA256Demo {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //MD5
        //SHA-256  (拿值求哈希)，现在sha256比较成熟

        //*** Java 自带的数据加密类MessageDigest:
        /*
        MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。信息摘要
        是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
        MessageDigest 对象开始被初始化。该对象通过使用 update（）方法处理数据。
        任何时候都可以调用 reset（）方法重置摘要。一旦所有需要更新的数据都已经
        被更新了，应该调用digest() 方法之一完成哈希计算。

        对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，
        MessageDigest 对象被重新设置成其初始状态。
         */

        //返回实现指定摘要算法的 MessageDigest 对象。
        //SHA-256 - 所请求算法的名称
        MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
        String s="你好世界";

        byte[] bytes=s.getBytes("UTF-8");
        //使用指定的 byte 数组更新摘要。
        messageDigest.update(bytes);//传进去要摘要的字节
        //通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。
        byte[] result=messageDigest.digest();//输出已加密的字节
        System.out.println(result.length);
        for(byte b:result){
            System.out.printf("%02x",b);
        }
        System.out.println();
    }
    //beca6335b20ff57ccc47403ef4d9e0b8fccb4442b3151c2e7d50050673d43172
}
