>  数组存储的特点：
>
> - 一旦初始化，其长度也就确定了
> - 数组一旦定义好，其元素类型也就确定了。只能操作指定类型的数据

> 数组存储的弊端：
>
> - 一旦初始化，长度不可修改
> - 数组提供的方法非常有限
> - 数组只能存储有序、可重复的数据







# Collection接口



<img src="D:\ForLife\Learning\JavaLearning\Java集合.assets\image-20210422205658860.png" alt="image-20210422205658860" style="zoom: 40%;" />

### Collection接口常用方法

> 如果添加的元素有自定义类，需要重写其equals方法

```java
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //1. add(Object e)
        coll.add("AA");
        coll.add(LocalDateTime.now());

        //2. size()
        System.out.println(coll.size());//4
        //2.1 addALL(Collection coll)
        Collection coll1 = new ArrayList();
        coll1.add("BB");
        coll1.add(456);
        coll.addAll(coll1);
        System.out.println(coll.size());//6

        //3. isEmpty(),判断当前集合是否为空
        System.out.println(coll.isEmpty());//false

        //4. clear 清空集合元素
        coll.clear();

        //5. contains(Obj obj)判断当前集合中是否包含obj
        coll.add("123");
        coll.add(new String("Tom"));

        boolean contains = coll.contains("123");
        System.out.println(contains);//true
        //注意，以下String对象是内容相同，虽然是两个对象，但仍然是true
        //调用equals()方法判断，故如果是自定义类，需要重写equals方法
        System.out.println(coll.contains(new String("Tom")));//true

        //5.1 containsAll(Collection coll1)
        //判断形参coll1中所有元素是否都存在于当前集合中
        Collection coll2 = Arrays.asList(123,456);//List对象
        System.out.println(coll.containsAll(coll2));//false
    }
```



```java
    @Test
    public void test2(){
        //6. remove(Obj obj),从当前集合中删除指定元素，返回boolean值
        //也调用equals方法
        Collection coll = new ArrayList();
        coll.add(111);
        coll.add(222);
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);
        System.out.println(coll.remove(111));//true

        //6.1 removeAll(Collection coll1),从当前集合中移除coll1中所有的元素
        Collection coll1 =Arrays.asList(new String("Tom"),789);
        System.out.println("removeALL:"+coll.removeAll(coll1));//true
        System.out.println("coll:"+coll);//[222, 123, 456]

        //7. retainAll(Collection coll2),
        //获取当前集合和coll2集合的交集，并返回给当前集合
        Collection coll2 =Arrays.asList(123,222);
        coll.retainAll(coll2);
        System.out.println("coll:"+coll);//coll:[222, 123]

        //8.equals()
        //逐个相比，即使内容一样，如果位置不一样，也是false
        //因为List是有序的，如果是set则不比有序，结果为true
        System.out.println(coll.equals(coll2));//false
    }
```



```java
    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);

        //9.集合 ----> 数组,toArray()
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        //数组 ----> 集合,调用Arrays的静态方法asList
        List<Object> list = Arrays.asList(arr);
        System.out.println(list);//[123, 456, Tom, 789]

        /*
        注意:
        如果直接传递数组，基本类型数组将被视为一个对象而不会被解析成数组
        但是如果直接传递参数将能正常解析。
        因此传递基本类型数组时强烈建议转为其封装类对象的数组
        */
        //情况1:只要传递的基本类型的数组，生成List的元素个数均为1
        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());//1
        //情况2:传递对象数组，元素个数正确。
        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());//2
        //情况3:传递参数，元素个数正确。
        List arr1 = Arrays.asList(123, 456);
        System.out.println(arr1.size());//2
    }
```



```java
        //10.iterator,返回Iterator接口实例，用于遍历集合元素
        //见本文Iterator
```



### Iterator 遍历

> 集合-collection接口的遍历操作，使用迭代器Iterator接口。
>
> Collection接口继承了`java.lang.Iteratble`接口，该接口有一个Iterator()方法，用以返回一个实现了Iterator接口的对象。

- 迭代器对象内部的方法`hasNext()`，`next()`

- 集合对象每次调用`iterator()`方法，都得到一个全新的迭代器对象

  - 默认游标都在集合的第一个元素之前

- 迭代器对象内部定义了`remove()`，可以删除当前元素。

  - 此`remove()`方法不同于直接调用集合的`remove()`方法

  - 如果还未调用`next()`方法，或者上次调用`next()`方法后已经调用`remove()`方法，再调用`remove()`都会报`IllegalStateExcepton`

```java
 	
	@Test
    public void iteratorTest(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);
		
        Iterator iterator1 = coll.iterator();
        //迭代器遍历集合元素 
        while (iterator.hasNext()){
            System.out.println(iterator1.next());
        }
        //删除集合中的“Tom”数据
        Iterator iterator2 = coll.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            if ("Tom".equals(obj)){
                iterator2.remove();
            }
        }
    }
```







###  `foreach`遍历

> JDK5.0提供了foreach循环迭代访问`Collection`或者`数组`。
>
> 遍历操作不需要获取`Collection`或者`数组`的长度，无需使用索引访问元素。
>
> 底层调用的是Iterator接口完成操作。

```java
    @Test
    public void test(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("456");
        coll.add(new String("Tom"));
        coll.add(789);

        //集合：for(集合中元素的类型 局部变量名:集合对象)
        for (Object obj:coll){
            System.out.println(obj);
        }
        //数组：for(数组中元素的类型 局部变量名:数组对象)
        int[] arr = new int[]{1,2,3,4,5};
        for (int i:arr){
            System.out.println(i);
        }
    }
```





## 1、List接口

> 鉴于Java中数组用来存储数据的局限性，通常用List替代数组。
>
> List集合类中元素有序、且可重复，集合中每个元素都有其对应的索引。
>
> 相对静态数组而言可以扩展长度，称作”动态数组“。



### 实现类—ArrayList

`ArrayList`源码分析：

```java
jdk 7的情况下
ArrayList list = new ArrayList();//底层创建了长度为10的Object[]数组elementData
list.add(123);//elementData[0] = new Integer(123);
...
list.add(11);//如果此次添加导致底层elementData数组容量不够，则扩容。
//默认情况下，扩容为原本容量的1.5倍，同时将原本数组复制到新数组中。
//如果扩容后大小仍不够，则直接以需求的大小作为容量。
```

故，事先应该估计容量，使用带参的ArrayList构造器。

```java
jdk 8的变化
ArrayList list = new ArrayList();//底层Object[]数组elementData，初始化为{}，并没有创建数组
list.add(123);//第一次调用add()时，底层才创建了长度为10的数组，并将123添加到elementData
...
后续添加和扩容与jdk7无异
```



### 实现类—LinkedList

> 双向链表

`LinkedList`源码分析：

```java
LinkedList list = new LinkedList();//内部声明了Node类型的first和last属性，默认值为null
list.add(123);//将123封装到Node，创建了Node对象
```



### 实现类—Vector（根本不用）

`Vector`源码分析：底层创建了长度为10的Object[]数组，扩容时默认为两倍扩容



### List接口额外定义的方法

List三种遍历方式

> iterator
>
> foreach
>
> 根据索引遍历



```java
    @Test
    public void test(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(new String("Person"));

        //1. void add()(int index,Object obj) 在索引index处插入obj元素
        list.add(1,"456");
        System.out.println(list);

        //2. void addAll(int index,Collection coll) 从索引index开始将coll的所有元素插入
        List coll = Arrays.asList("AA", "BB", "CC");
        list.addAll(2,coll);//将coll中的元素逐个插入
        System.out.println(list); //[123, 456, AA, BB, CC, Person]
        list.add(2,coll); //将整个coll作为一个对象插入
        System.out.println(list); //[123, 456, [AA, BB, CC], AA, BB, CC, Person]

        //3. int indexOf(Object obj) 返回obj对象在集合中首次出现的位置。若不存在，则返回-1
        System.out.println("index:"+list.indexOf(123));//index:0

        //4. int lastIndexOf(Object obj) 返回obj对象在集合中最后一次出现的位置。若不存在，则返回-1
        System.out.println("index:"+list.lastIndexOf(123));//index:0

        //5. Object remove(int index) 移除指定index位置的元素，并返回此元素
        //注意，和collection接口中remove方法构成重载。【remove(Obj obj),从当前集合中删除指定元素，返回boolean值】
        System.out.println("remove:"+list.remove(0));//remove:123

        //6. void set(int index,Object obj) 设置index位置上的元素为obj
        list.set(0,789);
        System.out.println(list);//[789, [AA, BB, CC], AA, BB, CC, Person]

        //7. List subList(int fromIndex, int toIndex)
        //返回从fromIndex到toIndex，左闭右开区间的子集
        List listTemp=list.subList(0,2);
        System.out.println(listTemp);//[789, [AA, BB, CC]]
        
        //8. get(int index) 获得某个索引处的值
        System.out.println(list.get(0));
    }
```





## 2、Set接口

> 元素无序、不可重复的集合。
>
> Set接口没有新的方法，即与Collection接口的方法一致.
>
> - 向Set中添加的数据，其所在的类一定要重写`hashCode()`和`equals()`。



- 无序性：根据哈希值来添加存储，而不是根据索引顺序添加
- 不可重复性：保证添加的元素，按照equals方法判断时，不能返回true



### 实现类-HashSet

添加元素的过程：

> HashSet底层：数组+链表的结构。

- 我们向HashSet中添加元素a

- 首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，通过某种算法得到索引位置。

- 判断数组此位置上是否有元素
  - 如果没有其他元素，则元素a添加成功
  - 如果有元素b（或者以链表形式存在的多个元素），则比较元素a和元素b的hash值：
    - 如果hash值不相同，则元素a添加成功
    - 如果hash值相同，则调用元素a的equals方法，与元素b相比较：
      - equals方法返回true，则元素a与元素b相同，元素a添加失败
      - equals方法返回false，则元素a与元素b不同，元素a添加成功

```java
    @Test
    public void test(){
        HashSet set = new HashSet();
        set.add(123);
        set.add("ABC");
        set.add("789");
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
```





### 实现类-LinkedHashSet

> `LinkedHashSet`是`HashSet`的子类。
>
> 在`HashSet`的基础上，`LinkedHashSet`提供了双向链表，存储的元素多了前节点和后节点，从而有顺序。

<img src="D:\ForLife\Learning\JavaLearning\Java集合.assets\image-20210429172315141.png" alt="image-20210429172315141" style="zoom:33%;" />





### 实现类-TreeSet

> `TreeSet`是`SortedSet`接口的实现类，底层使用红黑树结构存储数据。
>
> `TreeSet`两种排序方法：自然排序和定制排序，默认自然排序



> 向TreeSet中添加对象，要求是==相同类==的对象。

```java
    @Test
    public void test() {
        TreeSet set = new TreeSet();
        set.add("123");
        set.add("ABC");
        set.add("789");
        Iterator iterator = set.iterator();
        //输出的顺序是比较大小后的结果
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
```



#### 自然排序

> 在自然排序中，比较两个对象是否相同的标准为：compareTo返回0，不再是equals方法

```java
//需要Person继承Comparable接口，重写compareTo方法 
	@Test
    public void test() {
        TreeSet set = new TreeSet();
        set.add(new Person("Tim",13));
        set.add(new Person("Jerry",23));
        set.add(new Person("amy",32));
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
```





#### 定制排序

> 与常用类的Comparable接口和Comparator接口用法一致

# Map接口

> 保存具有映射关系"key-value"的集合

<img src="D:\ForLife\Learning\JavaLearning\Java集合.assets\image-20210422205621284.png" alt="image-20210422205621284" style="zoom:40%;" />



- HashMap
- LinkedHashMap
- TreeMap
- Hashtable
- Properties

























# 题目



## `ArrayList`、`LinkedList`、`Vector`三者的异同

相同：三个类都实现了List接口，都是存储有序、可重复的数据

不同：

- `ArrayList`：List接口的主要实现类；线程不安全，效率高；底层使用Object[ ]存储。
- `LinkedList`：对于频繁的插入、删除操作，使用此类比`ArrayList`高；底层使用双层链表存储。
- `Vector`：List接口的古老实现类；线程安全，效率低；底层使用Object[ ]存储。





## `HashSet`、`LinkedHashSet`、`TreeSet`三者的异同

- `HashSet`：作为Set接口的主要实现类；线程不安全；可以存储Null值
  - `LinkedHashSet`：作为`HashSet`的子类；遍历内部数据时，可以按照添加的顺序遍历；对于频繁的遍历操作，`LinkedHashSet`高于`HashSet`
- `TreeSet`：可以按照添加元素的指定属性，进行排序







## List 的小题



> 区分List中`remove(int index)` 和 `remove(Object obj)`

```java
//list输出什么？
public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//[1,2]
    }

private static void updateList(List list) {
        list.remove(2);//此处把2看为索引,结果------->[1,2]
    	list.remove(new Integer(2));//此处视为对象2,结果------>[1,3]
    }
```

