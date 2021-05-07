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



<img src=".\Java集合.assets\image-20210422205658860.png" alt="image-20210422205658860" style="zoom: 40%;" />

### Collection接口常用方法

> Collection是单列数据,区别于Map【key-value键值对】【双列数据】

> <font color='red'>如果添加的元素有自定义类，需要重写其equals方法</font>

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

>  <font color='red'>向List中添加的自定义数据，其所在的类一定要重写`equals()`。</font>



### `ArrayList`、`LinkedList`、`Vector`三者的异同

相同：三个类都实现了List接口，都是存储有序、可重复的数据

不同：

- `ArrayList`：List接口的主要实现类；线程不安全，效率高；底层使用Object[ ]存储。
- `LinkedList`：对于频繁的插入、删除操作，使用此类比`ArrayList`高；底层使用双层链表存储。
- `Vector`：List接口的古老实现类；线程安全，效率低；底层使用Object[ ]存储。





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



- 无序性：根据哈希值来添加存储，而不是根据索引顺序添加

- 不可重复性：保证添加的元素，按照equals方法判断时，不能返回true

  

### `HashSet`、`LinkedHashSet`、`TreeSet`三者的异同

- `HashSet`：作为Set接口的主要实现类；线程不安全；可以存储Null值
  - `LinkedHashSet`：作为`HashSet`的子类；遍历内部数据时，可以按照添加的顺序遍历；对于频繁的遍历操作，`LinkedHashSet`高于`HashSet`
- `TreeSet`：可以按照添加元素的指定属性，进行排序



### 实现类-HashSet

> <font color='red'>向HashSet中添加的数据，其所在的类一定要重写`hashCode()`和`equals()`。</font>

添加元素的过程：

> HashSet底层为HashMap：数组+链表的结构。

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
> 在`HashSet`的基础上，`LinkedHashSet`提供了双向链表，存储的元素多了前节点和后节点，从而实现有顺序。

<img src=".\Java集合.assets\image-20210429172315141.png" alt="image-20210429172315141" style="zoom:33%;" />





### 实现类-TreeSet

> `TreeSet`是`SortedSet`接口的实现类，底层使用红黑树结构存储数据。
>
> `TreeSet`两种排序方法：自然排序和定制排序，默认自然排序。添加元素时自动排序。



> <font color='red'>向TreeSet中添加的数据，其所在的类一定要继承Comparable或者Comparator。</font>



`TreeSet`方法通过排序方法实现去重。

- 如果自定义类里`comparaTo`方法只定义了姓名的比较，没有定义年龄的比较，则当两个对象姓名一样年龄不一样时，会进行去重。
- 如果姓名和年龄都定义了比较，那么则不会去重



> 向`TreeSet`中添加的对象，要求是==相同类==的对象。

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

> 在自然排序中，比较两个对象是否相同的标准为：compareTo返回0。不再是equals方法

```java
//需要Person继承Comparable接口，重写compareTo方法 
//如在new TreeSet()中不加参数，则默认自然排序，即调用compareTo方法
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

```java
@Test
public void test2(){
    Comparator com = new Comparator(){
        @Override
        public int compare(Object o1,Object o2){
            if(o1 instanceof Person && o2 instanceof Person){
				Person p1 = (Person)o1;
                Person p2 = (Person)o2;
                return p1.name.compareTo(p2.name)
        	}
        	//return 0;
            throw new RunTimeException("传入数据类型不一致");
        }
    }
    //加了参数，按参数进行排序
    TreeSet set = new TreeSet(com);
    
    set.add(new Person("Tim",13));
    set.add(new Person("Jerry",23));
    set.add(new Person("amy",32));
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```







# Map接口

> 保存具有映射关系"key-value"的集合

<img src=".\Java集合.assets\image-20210422205621284.png" alt="image-20210422205621284" style="zoom:40%;" />



#### Map结构

<img src=".\Java集合.assets\image-20210505002333154.png" alt="image-20210422205621284" style="zoom:40%;" />

- Map中的`key`：无序的、不可重复的，故使用Set存储所有的key
	- HashMap：<font color='red'>key所在的类要重写`equals()`和`hashCode()`方法</font>
	- TreeMap：<font color='red'>向TreeMap中添加的数据，其所在的类一定要继承Comparable或者Comparator。</font>
- Map中的`value`：无序的、可重复的，故使用Collection存储所有的value
	- <font color='red'>value所在的类要重写`equals()`方法</font>

一个键值对：key-value构成了一个Entry对象

- Map中的`Entry`：无序的、不可重复的，故使用Set存储所有的`Entry`



## Map接口的方法



```java
//添加、删除、修改操∶
Object put(Object key，0bject value)∶将指定key-value添加到（或修改）当前map对象中 
void putAll(Map m)∶将m中的所有key-value对存放到当前map 中 
Object remove(Object key)∶移除指定key的key-value对，并返回value 
void clear()∶，清空当前imap 中的所有数据元素查询的操∶
    
//元素查询
Object get(Object key)∶获取指定key对应的value 
boolean containsKey(Object key)∶ 是否包含指定的key 
boolean containsValue（Object value）∶ 是否包含指定的value 
int size()∶返回map key-value对的个数 
boolean isEmpty()∶ 判/断当前map是否为空
boolean equals(Object obj)∶ 判断当前map和参数对象obj是否相等元视图操作的方法∶
    
//元视图操作方法
Set keySet()∶ 返回所有key构成的Set集合
Collection values()∶ 返回所有value构成的Collection集合 
Set entrySet()∶ 返回所有entry对象key-value对构成的Set集合

    //转为Set和Collection后，可以用iterator进行遍历
    //Entry类有getKey()和getValue()
```



```java
        //遍历所有的key-value
        //方式一
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
```





### 实现类—HashMap

> 作为Map的主要实现类；线程不安全，效率高；可以存储`key为null`或者`value为null`的数据





### 实现类—LinkedHashMap

> 保证在遍历map元素时，可以按照添加的顺序实现遍历。

```java
@Test
public void test(){
    LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
    map.put(123,"AA");
    map.put(456,"BB");
    map.put(789,"CC");
    System.out.println(map);
}
```





### 实现类—TreeMap

> `TreeMap`是`SortedSet`接口的实现类，底层使用红黑树结构存储数据。
>
> `TreeMap`两种排序方法：自然排序和定制排序，默认自然排序。添加元素时自动排序。



### 实现类—Hashtable（根本不用）

> 子类—Properties





























# 题目







## Map中各类的异同

- `HashMap`：作为Map的主要实现类；线程不安全，效率高；可以存储`key为null`或者`value为null`的数据

  - `LinkedHashMap`：保证在遍历map元素时，可以按照添加的顺序实现遍历。

    ​						原因：在原有HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。

    ​						对于频繁的遍历，效率高于HashMap

- `TreeMap`：可以按照添加的key-value进行排序，实现排序遍历。

  ​					此时考虑key的自然排序或者定制排序。

- `HashTable`：作为古老的实现类；线程安全，效率低；不可以存储`key为null`或者`value为null`的数据

  - Properties



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





## HashMap的底层实现原理

 **以JDK-7为例说明**

```java
HashMap map = new HashMap();
```

在实例化以后，底层创建了长度是16的一维数组 `Entry[] table`

【可能经过了多次put】

```java
map.put(key1,value1);
```

首先，调用key1所在类的`hashCode()`计算key1的哈希值。

然后，使用该哈希值经过某种算法计算以后，得到在`Entry`数组中的存放位置。

- 如果此位置上的数据为空，此时的`key1-value1`添加成功
- 如果此位置上的数据不为空【此位置存在一个或多个数据（以链表形式存在）】，需要比较key1和已经存在数据的哈希值
	- 如果`key1`的哈希值和已存在数据的哈希值都不相同，此时`key1-value1`添加成功
	- 如果`key1`的哈希值和某个数据的哈希值相同，则继续比较，调用`key1`所在类的`equals()`方法
		- 如果`equals()`返回`false`，此时的`key1-value1`添加成功
		- 如果`equals()`返回`true`，则使用`value1`替换此`相同key`的value值【即修改value值】

> 当超出临界值（且要存放的位置非空时），扩容。
>
> 默认扩容方式：扩容为原来的两倍，并将原本的数据复制过来



 **相较JDK-7，JDK-8的区别**

- `new HashMap()`：底层还没有创建一个长度为16的数组

- JDK-8底层的数组是` Node[]`，而非`Entry[]`

- 首次调用`put()`方法时，底层创建长度为16的数组

- JDK-7底层结构只有数组+链表。JDK-8底层结构：数组+链表+红黑树

	- 当数组的某一个索引位置的元素，以链表形式存在的数据个数 > 8且当前数组的长度 > 64时，

		此时此索引位置上所有数据改为使用红黑树存储
	
- JDK-7是头插法，JDK-8是尾插法





### HashMap底层参数

- DEFAULT_INITTAL_CAPACITY∶HashMap的默认容量，16 

- DEFAULT_LOAD_FACTOR∶ HashMap的默认加载因子∶ 0.75 

- threshold∶扩容的临界值，=容量*填充因子∶ 16 *0.75 =>12 

- TREEIFY_THRESHOLD∶ Bucket 中链表长度大于该默认值，转化为红黑树∶8 

- MIN_TREEIFY_CAPACITY∶桶中的Node被树化时最小的hash表容量∶64



- table∶存储元素的数组，总是2的n次幂 entrySet∶存储具体元素的集

- size∶HashMap中存储的键值对的数量 

- modCount∶ HashMap扩容和结构改变的次数 

- threshold∶扩容的临界值，=容量*填充因子 

- loadFactor∶ 填充因子