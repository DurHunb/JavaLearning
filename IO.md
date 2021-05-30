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
        //2.1 造节点流
        FileInputStream fis = new FileInputStream((srcFile));
        FileOutputStream fos = new FileOutputStream(destFile);
        //2.2 造缓冲流
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

作用：

- 提供了字节流和字符流的转换

- 使用转换流可以在一定程度上避免乱码，还可以指定输入输出所使用的字符集，实现编码和解码功能。

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



