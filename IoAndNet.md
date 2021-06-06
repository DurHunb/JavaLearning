# 一、File类

> `java.io.File`
>
> File类的一个对象，代表一个文件或者一个文件目录



## 创建File的实例

```java
    @Test
    public void test(){
        
        //相对路径，相对当前module
        File file1 = new File("textFile.txt");
        
        //绝对路径
        File file2 = new File("D:\\Data\\textFile.txt");
        
        //指定父目录，在指定子文件或者子目录
        File file3 = new File("D:\\Data","serach");
        File file4 = new File(file3,"serach");
    }
```



## File 类的使用∶ 路径分隔符

- Java程序支持跨平台运行，路径中的每级目录之间用一个路径分隔符隔开。

- 路径分隔符和系统有关，因此路径分隔符要慎用。∶
  - windows和DOS系统默认使用"`\`"来表示
  - UNIX和URL使用"`/`"来表示



- 为了解决这个隐患，File类提供了一个常量∶

```java 
public static final String separator。
```

根据操作系统，动态的提供分隔符。

```java
File file1 = new File("d:\\atguigu\\info.txt");

File file2 = new File("d:"+File.Sparator＋"atguigu"＋File.separator＋"info.txt");
```



## File类的方法

> File类中涉及到关于文件或者文件目录的创建、删除、重命名、修改时间、文件大小等方法，
>
> 并未涉及到写入或者读取文件内容的操作。
>
> 如果需要读取或写入文件内容，必须使用IO流完成。
>
> File的参数常会作为参数，传入到IO流中



### 获取

```java
    //适用于文件
	public String getAbsolutePath():获取绝对路径
    public String getPath():获取路径
    public String getName():获取名称
    public String getParent():获取上层文件日录路径。若无，返回hull。【根据file是绝对路径还是相对路径，返回其上一层】
    public Long length():获取文件长度（即:字节数）。不能获取日录的长度。
    public long lastModified():获取最后一次的修改时间，毫秒值
    
    //适用于文件目录
    public String[]List():获取指定目录下的所有文件或者文件目录的名称数组
    public File[]listFiles():获取指定目录下的所有文件或者文件日录的File数组
```

```java
用法例：file1.getPath()
```





### 重命名

要想保证返回true，需要file1在硬盘中存在，且file2不能在硬盘中存在

> 调用该函数后，原本位置的file1文件将消失，会出现在file2文件位置，并完成重命名

```java
public boolean renameTo(File des):把文件重命名为指定的文件路径
    比如：file1.renameTo(file2)
```

```java
    @Test
    public void test() {
        //file1文件存在
        File file1 = new File("src\\textFile.txt");
        System.out.println(file1.length());
        //file2文件不存在
        File file2 = new File("D:\\customerData\\hi.txt");
        boolean is_rename = file1.renameTo(file2);
        System.out.println(is_rename);
    }
```



### 判断

```java
    //File类的判断功能
    public boolean isDirectory():判断是否是文件目录
    public boolean isFile():判断是否是文件
    public boolean exists():判断是否存在
    public boolean canRead():判断是否可读
    public boolean canWrite():判断是否可写
    public boolean isHidden():判断是否隐藏Ⅰ
```

```java
用法例：file1.isFile()
```





### 创建

注意事项∶

- 如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下.

```java
public boolean createNewFile():创建文件。若文件存在，则不创建，返回false
public boolean mkdir():创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
public boolean mkdirs():创建文件目录。如果上层文件目录不存在，一并创建。
    
```





### 删除



删除注意事项∶

- Java中的删除不走回收站。
- 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录

```java
public boolean delete():删除文件或者文件夹
```









# 二、IO流



## 1、IO流原理及流的分类

流的分类

- 按操作数据单位不同分为∶字节流（8 bit），字符流（16 bit）

- 按数据流的流向不同分为∶输入流，输出流
- 按流的角色的不同分为∶ 节点流，处理流

| 【抽象基类】 | 字节流       | 字符流 |
| ------------ | ------------ | ------ |
| 输入流       | InputStream  | Reader |
| 输出流       | OutputStream | Writer |



1. Java的IO流共涉及40多个类，实际上非常规则，都是从如上4个抽象基类派生的。
2. 由这四个类派生出来的子类名称都是以其父类名作为子类名后缀。



<img src=".\IO.assets\image-20210517201238449.png" alt="image-20210517201238449" style="zoom: 60%;" />



## 2、节点流（文件流）

### FileInputStream

- 字节流

- 用于二进制文件

- 使用方式和FileReader一样



### FileOutputStream

- 字节流
- 用于二进制文件

- 使用方式和FileWriter一样



### FileReader

> 字符流

说明点：

- read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
- 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
- 读入的文件一定要存在，否则会报FileNotFoundException。为了避免这个报错，也可以加一个`if(fr!=null)`判断



> hello.txt文件里内容是		helloworld123



```java
//不规范！！！！！
//此种写法仅供参考FileReader的使用
	@Test
    public void testFileReader() throws IOException {
        //1.实例化File类的对象，指明要操作的文件
        File file = new File("src\\hello.txt");//相较于当前Module
        System.out.println(file.getAbsolutePath());
        // 2. 提供具体的流
        FileReader fr = new FileReader(file);

        //3.数据的读入
        //read()∶返回读入的一个字符。如果达到文件末尾，返回-1
        int data = fr.read();
        while (data != -1) {
            System.out.println((char) data);
            data = fr.read();
        }

        //4.流的关闭
        fr.close();
    }
```



> 一定要保证文件的关闭，需要用try-catch。
>
> 不能用throw。

```java
    @Test
    public void testFileReader()  {
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("src\\hello.txt");//相较于当前Module
            System.out.println(file.getAbsolutePath());
            // 2. 提供具体的流
            fr = new FileReader(file);

            //3.数据的读入
            //read()∶返回读入的一个字符。如果达到文件末尾，返回-1
            int data = fr.read();
            while (data != -1) {
                System.out.println((char) data);
                data = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fr!=null){
                    //4.流的关闭
                	fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```



#### 使用reader的重载方法

> 如果文件较大，就不能再一个一个字符的读取。下面是可以一次读【数组长度】个字符

```java
//对read操作升级，使用read重载方法
    @Test
    public void test2()  {
        File file = new File("D:\\Test\\src\\hello.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            //下面是可以一次读【数组长度】个字符
            
            char[] cbuf = new char[5];
            int len;
            //read(char[] cbuf) 返回每次读入到cbuf数组中的字符个数，如果达到末尾，返回-1
            while ((len = fr.read(cbuf)) != -1){

                //错误的写法，如果用length，则每次都会读5个。
                //其实每次读都是覆盖之前读取的内容，
                //比如倒数第二次是world，倒数第一次只读了123，则倒数第一次输出的数组则是123ld
//                for (int i = 0;i<cbuf.length;i++){
//                    System.out.print(cbuf[i]);	//helloworld123ld
//                }

                //正确的写法一，用len表示每次读的个数,读了几个遍历几个
                for (int i = 0;i<len;i++){
                    System.out.print(cbuf[i]);//helloworld123ld
                }
                
                 //正确的写法二,char型数组转为String，从第0个开始取，取到第len-1个
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

```



### FileWriter

> 字符流
>
> 流的处理都要用try-catch-finally

```java
//不规范，流的处理都要用try-catch-finally
	@Test
    public void testFileWriter() throws IOException {
        //1.提供File类的对象，指明写出的文件
        File file = new File("src\\helloWriter.txt");
 
        //2. 提供FileWriter对象，用于写出,默认为false,覆盖原文件
        FileWriter fw = new FileWriter(file);
        //在原文件上追加
        //FileWriter fw = new FileWriter(file,true);        
        
        //3.写出操作
        String cb = "abdef";
        char[] buffer = new char[]{'a','b','c'};
        fw.write("where are you?\n");
        fw.write("you need have a dream\n");
        fw.write(cb,0,4);//按照索引写入
        fw.write(buffer);//写入数组
        
        //4.关闭文件
        fw.close();
    }
```



## 3、缓冲流

处理流的一种

<font color = 'red'>缓冲流</font>建立在<font color = 'red'>节点流</font>的基础上



### BufferedInputStream

> 对应FileInputStream
>
> 使用 见 BufferedOutputStream

> 内部提供一个缓存区，提高了读取速度。
>
> 每次读写都会刷新缓存区



### BufferedOutputStream

> 对应FileOutputStream

> 内部提供一个缓存区，提高了读取速度。
>
> 每次读写都会刷新缓存区




- **使用缓冲流BufferedWriter，需要先建立其对应的节点流**

```java
    /*实现非文本文件的复制*/
    @Test
    public void BufferedStreamTest() throws IOException {
        //1.造文件
        File srcFile = new File("src\\43t2.jpg");
        File destFile = new File("src\\day_10\\43t2.jpg");

        //2.造流
        // 2.1 造节点流
        FileInputStream fis = new FileInputStream((srcFile));
        FileOutputStream fos = new FileOutputStream(destFile);
        //2.2 .造缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //3. 复制的细节∶读取、写入
        byte[] buffer = new byte[10];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            bos. write(buffer,0,len);
        }

        //4.资源的关闭
        //先关闭外层流，再关闭内层流
        bos.close();
        bis.close();
        //可以省略，因为关闭外层流的同时，内层流也会关闭
        fos.close();
        fis.close();
    }
```



### BufferedReader

> 对应FileReader。
>
> 使用 见 BufferedOutputStream。

> 内部提供一个缓存区，提高了读取速度。
>
> 每次读写都会刷新缓存区。



### BufferedWriter

> 对应FileWriter。
>
> 使用 见 BufferedOutputStream。

> 内部提供一个缓存区，提高了读取速度
>
> 每次读写都会刷新缓存区



## 4、转换流

处理流的一种

作用：提供了字节流和字符流的转换



### InputStreamReader

> 将一个<font color='red'>字节</font>的输入流， 转换为一个<font color='red'>字符</font>的输入流

```java
    /*
    *  此时也应该使用try-catch-finally
    */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("dbcp.txt");
        //InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符

        //参数2指明了字符集，具体使用哪个字符集，取决于文件dbcp.txt保存时使用的字符集
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            String str = new String(cbuf, 0, len);
            System.out.print(str);
            isr.close();

        }
    }
```



### OutputStreamReader

> 将一个<font color='red'>字节</font>的输出流，转换为一个<font color='red'>字符</font>的输出流

```java
    /*
    * 复制文件
    */
    @Test
    public void test2() throws Exception {
        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");
        
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis,  "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
        //2.读写过程
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            osw.write(cbuf, O, len);
        }
        //3.关闭资源
        isr.close();
        osw.close();
    }
```



## 5、标准输入输出流

- 标准的输入、输出
          System.in∶ 标准的输入流，默认从筵盘输入
          System.out∶标准的输出流，默认从控制/台输出
- 重新指定输入和输出的设备
  - System类的`setIn(InputStream is)`
  - System类的`setOut(PrintStream ps)`

```java
public class OtherStreamTest {
    /*
    练习∶
    	从键盘输入字符串，要求将读取到的整行字宇转成大写输出。然后继续进行输入操作，
    直至当输入"e"或者exit"时，退出程序。
        方法一∶使用Scanner实现
        方法二∶使用System.in实现。System.in → 转换流 → BufferedReader的readline()
    */
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        while (true){
            System.out.println("请输入字符串");
            String data = br.readLine();

            //忽略大小写
            //交换data和"e"的位置，避免空指针
            if ("e".equalsIgnoreCase(data)||"exit".equalsIgnoreCase(data)){
                System.out.println("程序结束");
                break;
            }
            String upperCase = data.toUpperCase();
            System.out.println(upperCase);
        }
        br.close();
    }
}

```





## 6、打印流

```java
 	@Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D\\text.txt"));

            // 创建打印输出流， 设置为自动刷新模式(写入换行符或字节 '\n'时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);

            if (ps != null) {
                // 把标准输出流（控制台输出）改成输出到文件
                System.setOut(ps);
                // 输出ASCII字符
                for (int i = 0; i <= 255; i++) {
                    System.out.print((char) i);
                    // 每50个数据一行
                    if (i % 50 == 0) {
                        System.out.println();// 换行
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
```



## 7、对象流

`ObjectInputStream`和`OjbectOutputSteam`

- 用于存储和读取基本数据类型数据或对象的处理流。
- 它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。



> 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
>
> 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。



- 序列化∶ 用`ObjectOutputStream`类保存基本类型数据或对象的机制。

- 反序列化∶ 用`ObjectInputStream`类读取基本类型数据或对象的机制。



**自定义类的序列化前提**

> ​	条件一：
>
> 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可序列化的，为了让某个类是可序列化的，该类必须实现如下两个接口之一。
>
> - Serializable
>
> - Externalizable
>
> 否则，会抛出NotSerializableException异常
>
> 
>
> ​	条件二：
>
> 自定义类增加全局常量——"序列版本号"
>
> ```java
> public static final long serialVersionUID = 479134875618347L;
> ```
>
> 自定义异常类也要加这个。
>
> 
>
> ​	条件三：
>
> 自定义类内部的所有属性，也需要是可序列化的。
>
> （默认情况下，基本数据类型都是可以序列化的）





注意：

`ObjectOutputStream`和`ObjectInputStream`不能序列化`static`和`transient`修饰的成员变量。

- 如果那些属性不想被序列化，可以使用这两个关键字



```java
    @Test
    public void testObjectOutputStream() throws IOException, ClassNotFoundException {
        //序列化过程
        ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(new File("data.dat")));
        oos.writeObject(new String("我爱我家"));
        oos.flush();//刷新缓存区
        oos.close();

        //反序列化过程
        ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(new File("data.dat")));
        Object obj = ois.readObject();
        String str = (String)obj;
        System.out.println(str);
        ois.close();
    }
```



## 8、随机存取文件流

> 随机指的是可以改变指针位置，实现任意位置的读和写



RandomAccessFile 类

> `RandomAccessFile` 类声明在java.io包下，但直接继承于`java.lang.Object`类。
>
> 它实现了`Datalnput`、`DataOutput`这两个接口，也就意味着这个类<font color='red'>既可以作为输入流，又可作为输出流</font>。

> `RandomAccessFile`类支持"随机访问"的方式，程序可以直接跳到文件的任意地方来读、写文件>支持只访问文件的部分内容>可以向已存在的文件后追加内容

> `RandomAccessFile `对象包含一个记录指针，用以标示当前读写处的位置。 `RandomAccessFile `类对象可以自由移动记录指针。



- 构造器

  创建 RandomAccessFile 类实例需要指定一个mode 参数，该参数指定 RandomAccessFile 的访问模式

  - r 以只读方式打开
  - rw 打开以便读取和写入
  - rwd∶打开以便读取和写入; 同步文件内容的更新
  - rws∶打开以便读取和写入;同步文件内容和元数据的更新

```java
public RandomAccessFile(File file, String mod)
public RandomAccessFile(String name, String m)
```

​	

> 如果模式为只读r。则不会创建文件，而是会去读取一个已经存在的文件，如果读取的文件不存在则会出现异常。 如果模式为rw读写。如果文件不存在则会去创建文件，如果存在则不会创建。



```java
    @Test
    public void testRandomAcessFile () throws IOException {
        //输入
        RandomAccessFile rafRead = new RandomAccessFile(new File("src\\hello.txt"),"r");
        //输出
        RandomAccessFile rafWrite = new RandomAccessFile(new File("src\\helloRandom.txt"),"rw");

        byte[] buffer = new byte[1024];
        int len;
        while((len=rafRead.read(buffer))!=-1){
            rafWrite.write(buffer,0,len);
        }

        rafRead.close();
        rafWrite.close();
    }
```



### 改变指针位置



```java
long getFilePointer()∶ 获取文件记录指针的当前位置

void seek(long pos)∶将文件记录指针定位到 pos 位置
```



```java
    @Test
    public void testRandomAcessFile () throws IOException {

        RandomAccessFile rafWrite = new RandomAccessFile(new File("helloRandom.txt"),"rw");
        
		//改变指针索引位置是3
		rafWrite.seek(3);
        //write方法，其实是从指针位置开始覆盖掉
        rafWtite.write("xyz".getBytes());
        rafWrite.close();
    }
```



# 三、NIO

> Java NIO （New IO，Non-Blocking IO）是从Java 1.4版本开始引入的一套新的IOAPI，可以替代标准的Java IO API。

> NIO与原来的IO有同样的作用和目的，但是使用的方式完全不同，NIO支持<font color='red'>面向缓冲区</font>的（IO是面向流的）、基于通道的IO操作。

​		NIO将以更加高效的方式进行文件的读写操作。

- Java API中提供了两套NIO，一套是针对标准输入输出NIO，另一套就是网络编程NIO。

- `java.nio.channels.Channel`
  - FileChannel∶处理本地文件
  - SocketChannel∶TCP网络编程的客户端的Channel
  - ServerSocketChannelTCP：网络编程的服务器端的Channel
  - DatagramChannel∶ UDP网络编程中发送端和接收端的Channel



## Path、Paths和Files核心API

> 早期的Java只提供了一个File类来访问文件系统，但File类的功能比较有限，所提供的方法性能也不高。而且，大多数方法在出错时仅返回失败，并不会提供异常信息。



NIO.2为了弥补这种不足，引入了Path接口，代表一个平台无关的平台路径，描述了目录结构中文件的位置。

Path可以看成是File类的升级版本，实际引用的资源也可以不存在。

- 在以前IO操作都是这样写的∶

```java
import java.io.File;

File file = new File("index.html");
```

- 但在Java7 中，我们可以这样写∶

```java
import java.nio.file.Path; 
import java.nio.file.Paths;

Path path = Paths.get("index.html");//Path 
```





# 四、网络编程



建立IP对象

```java
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        //建立InetAddress对象
        InetAddress inet1 = InetAddress.getByName("192.168.10.14");
        System.out.println(inet1);
        InetAddress inet2 = InetAddress.getByName("www.atguigu.com");
        System.out.println(inet2);
        InetAddress inet3 = InetAddress.getByName("127.0.0.1");
        System.out.println(inet3);

        //建立InetAddress对象，本地ip
        InetAddress inet4 = InetAddress.getLocalHost();
        System.out.println(inet4);

        //getHostName(),获取域名
        System.out.println(inet2.getHostName());
        //getHostName()，获取ip地址
        System.out.println(inet2.getHostAddress());
    }
}
```













## 端口号



端口号标识正在计算机上运行的进程（程序）

- 不同的进程有不同的端口号

- 被规定为一个 16 位的整数 O~65535。



端口分类∶

- 公认端口∶0~1023。被预先定义的服务通信占用

  如∶HTTP占用端口80，FT!占用端口21，Telnet占用端口23

- 注册端口∶1024~49151。分配给用户进程或应用程序。

  如∶ Tomcat占用端口8080，MySQL占用端口3306，Oracle占用端口1521等

- 动态/私有端口∶ 49152~65535。



端口号与IP地址的组合得出一个网络套接字∶ Socket。



## TCP网络编程

三次握手，四次挥手。

- 如果先启动客户端，再启动服务器端，则客户端会报错。

  所以需要先启动服务器，再启动客户端。



### 从客户端发送一条信息给服务器端

```java
    //客户端
    @Test
    public void client() throws IOException {
        //1.创建socket对象，指明服务器端的ip和端口号
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet, 8899);

        //2.获取一个输出流
        OutputStream os = socket.getOutputStream();

        //3.写出数据
        os.write("你好，我是renbo的客户端".getBytes());

        //4.流的关闭
        os.close();
        socket.close();
    }

    //服务器端
    @Test
    public void server() throws IOException {
        //1.创建服务器端的ServerSocket，指明端口号
        ServerSocket ss = new ServerSocket(8899);

        //2.调用accept()，表示接受来自于客户端的输出socket
        Socket socket = ss.accept();

        //3.获取输入流
        InputStream is = socket.getInputStream();
        
        //4.读取输入流的数据

        //不建议，传中文过来，一个utf-8字符占三个字节，可能会有乱码
//        byte[] buffer = new byte[20];
//        int len;
//        while((len=is.read(buffer))!=-1){
//            String str = new String(buffer,0,len);
//            System.out.println(str);

        //使用这种方法，会在全部字节写入后，再转换成字符串
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[5];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer,0,len);
        }

        System.out.println("收到了来自于："+socket.getInetAddress().getHostAddress()+"的数据");

        System.out.println(baos.toString());

        //关闭资源
        baos.close();
        is.close();
        socket.close();
        ss.close();
    }
```







### 客户端发完文件后服务器端发送反馈

```java
//客户端
    @Test
    public void client() throws IOException {
        //1.创建socket对象，指明服务器端的ip和端口号
        InetAddress inet = InetAddress.getByName("127.0.0.1");

        Socket socket = new Socket(inet, 8899);

        //2.获取一个输出流
        OutputStream os = socket.getOutputStream();
        //本地文件的输入流
        FileInputStream fis = new FileInputStream(new File("beauty_message.jpg"));

        //3.将本地文件写出到输出流
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer,0,len);
        }

        //关闭数据的输出，防止服务器端一直处于读数据的状态
        socket.shutdownOutput();

        //接受服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        //避免乱码
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        System.out.println(baos.toString());

        //4.流的关闭
        baos.close();
        fis.close();
        os.close();
        socket.close();
    }

    //服务器端
    @Test
    public void server() throws IOException {
        //1.创建服务器端的ServerSocket，指明端口号
        ServerSocket ss = new ServerSocket(8899);

        //2.调用accept()，表示接受来自于客户端的输出socket
        Socket socket = ss.accept();

        //3.获取输入流
        InputStream is = socket.getInputStream();

        //本地的输出流
        FileOutputStream fos = new FileOutputStream(new File("beauty1.jpg"));

        //4.读取输入流的数据，和读文件不一样，需要明确的客户端明确的指示，才停止读数据
        byte[] buffer = new byte[5];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer,0,len);
        }

        //服务器给予客户端反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好，照片已经收到".getBytes());


        //关闭资源
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
```





## UDP网络编程

UDP网络通信

- 类 DatagramSocket 和 DatagramPacket 实现了基于 UDP 协议网络程序。

- UDP数据报通过数据报套接字 DatagramSocket 发送和接收，系统不保证 UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。 

- DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口 号以及接收端的IP地址和端口号。

- UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接。

  如同发快递包裹一样。

```java
    //发送端
    @Test
    public void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        //本地ip
        //InetAddress inet = InetAddress.getByName("127.0.0.1");//也可
        InetAddress inet = InetAddress.getLocalHost();

        //封装一个数据报
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,8899);

        //发送出去
        socket.send(packet);

        socket.close();
    }

    //接收端
    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(8899);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        //接收数据
        socket.receive(packet);

        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }
```







## URL网络编程

URL网络编程 

- URL∶统一资源定位符，对应着互联网的某一资源地址 

- 格式∶

	```
	http://Localhost:8080/examples/beauty.jpg?username=Tom
	协议     主机名   端口号 	资源地址			参数列表  
	```



```java
//URL对象的方法
public String getProtocol() //获取该URL的协议名
public String getHost()//获取该URL的主机名
public String getPort()//获取该URL端口号
public String getPath()//获取该URL的文件路径
public String getFile()//获取该URL的文学名
public String getQuery()//获取该URL的查询名
    
URL url = new URL("http://Localhost:8080/examples/beauty.jpg?username=Tom")
```



实现从Tomcat服务器下载数据

```java

public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://Localhost:8080/examples/beauty.jpg?username=Tom");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("beautt.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }

        System.out.println("下载完成");
        
        fos.close();
        is.close();
        urlConnection.disconnect();

    }
```

