package com.yin.yzjcourse.k23文件_IO_流等

import org.junit.Test
import java.io.File

class liu {

    /**
    T.use(block: (T) -> R): R
    是任何类型的扩展函数，其参数是函数类型的参数，该函数类型是一个参数一个返回值，这个参数就是调用use的对象
    实现了Closeable接口的对象可调用use函数
    use函数会自动关闭调用者（无论中间是否出现异常）
    Kotlin的File对象和IO流操作变得行云流水
    use函数内部实现也是通过try-catch-finally块捕捉的方式，所以不用担心会有异常抛出导致程序退出
    close操作在finally里面执行，所以无论是正常结束还是出现异常，都能正确关闭调用者

     遇到类似需求时，百度一下kotlin的相关知识，肯定用得到
     */
    @Test
    fun test() {

        //实现读取一个文件内每一行的功能
        //kotlin实现
        //细翻阅readLines这个扩展函数的实现你会发现，它也是间接调用了use，这样就省去了捕捉异常和关闭的烦恼
        //同样的，经过包装以后你只需要关注读出来的数据本身而不需要care各种异常情况
        File("/home/test.txt").readLines()
                .forEach { println(it) }

        /*
        //Java 实现
FileInputStream fis = null;
DataInputStream dis = null;
try {
    fis = new FileInputStream("/home/test.txt");
    dis = new DataInputStream(new BufferedInputStream(fis));
    String lines = "";
    while((lines = dis.readLine()) != null){
        System.out.println(lines);
    }
} catch (IOException e){
    e.printStackTrace();
} finally {
    try {
        if(dis != null)
            dis.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        if(fis != null)
            fis.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
         */
    }
}