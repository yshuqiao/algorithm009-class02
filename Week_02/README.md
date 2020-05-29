学习笔记

HashMap小总结（Java 8）

HashMap底层的数据结构主要是：数组+链表+红黑树（数组的元素可能是单个Node，也可能是个链表，也可能是个红黑树）。

一、主要属性

//初始容量为16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

//最大容量
static final int MAXIMUM_CAPACITY = 1 << 30;

//负载因子默认值
static final float DEFAULT_LOAD_FACTOR = 0.75f;

//桶上的红黑树大小大于等于8时，链表转化成红黑树
static final int TREEIFY_THRESHOLD = 8;

//桶上的红黑树大小小于等于6时，红黑树转化成链表
static final int UNTREEIFY_THRESHOLD = 6;

//当数组容量大于64时，链表才会转化成红黑树
static final int MIN_TREEIFY_CAPACITY = 64;

//记录迭代过程中HashMap结构是否发生变化，如果有变化，迭代时会fail-fast
transient int modCount;

//HashMap的实际大小，可能不准（因为当你拿到这个值的时候，可能又发生了变化）
transient int size;

//存放数据的数组
transient Node<K,V>[] table;

//扩容的门槛，有两种情况
//如果初始化时，给定数组大小的话，通过tableSizeFor方法计算，数组大小永远接近于2的幂次方，比如你给定初始化大小为19，实际上初始化大小为32，为2的5次方
//如果是通过resize方法进行扩容，大小=数组容量*0.75
int threshold;

//链表的节点
static class Node<K,V> implements Map.Entry<K,V> {

//红黑树的节点
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {

二、主要方法
1、put新增
新增key,value大概的步骤：
（1）判断空数组有无初始化，没有的话初始化；
（2）如果通过key的hash能够直接找到值，跳转到（6），否则到（3）；
（3）如果hash冲突，两种解决方案：链表or红黑树；
（4）如果是链表，递归循环，把新元素追加到队尾；
（5）如果是红黑树，调用红黑树新增的方法；
（6）通过（2）、（4）、（5）将新元素追加成功，再根据onlyifAbsent判断是否需要覆盖；
（7）判断是否需要扩容，需要扩容进行扩容，结束。
其中红黑树新增节点过程是：
（1）首先判断新增的节点在红黑树上是不是已经存在，判断手段有如下两种：
如果节点没有实现Comparable接口，使用equals进行判断；
如果节点自己实现了Comparable接口，使用compareTo进行判断。
（2）新增的节点如果已经在红黑树上，直接返回；不在的话，判断新增节点是在当前节点的左边还是右边，左边值小，右边值大；
（3）自旋递归1和2步，直到当前节点的左边或者右边的节点为空时，停止自旋，当前节点即为我们新增节点的父节点；
（4）把新增节点放到当前节点的左边或右边为空的地方，并与当前节点建立父子节点关系；
（5）进行着色和旋转，结束。
2、get查找
查找主要分为以下三步：
（1）根据hash算法定位数组的索引位置，equals判断当前节点是否是我们需要寻找的key，是的话直接返回，不是的话往下；
（2）判断当前节点有无next节点，有的话判断是链表类型，还是红黑树类型；
（3）分别走链表和红黑树不同类型的查找方法。
其中红黑树的查找步骤为：
（1）从根节点递归查找；
（2）根据hashcode，比较查找节点、左边节点、右边节点之间的大小，根据红黑树左小右大的特性进行判断；
（3）判断查找节点有无定位节点位置，有的话返回，没有的话重复2,3两步；
（4）一直自旋到定位到节点位置为止。


参考：http://www.imooc.com/read/47

