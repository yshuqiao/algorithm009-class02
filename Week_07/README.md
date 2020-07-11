学习笔记

一、实战位运算的要点
（1）判断奇偶：
x%2==1 --> (x&1)==1
x%2==0 --> (x&1)==0

（2）x>>1 --> x=x>>1;
mid=(left+right)/2; --> mid=(left+right)>>1;

（3）X=X&(X-1)清零最低位的1

（4)X&-X => 得到最低位的1

（5）X&~X => 0

（6）八皇后代码

def solveNQueens(self,n):
	if n<1:return []
	self.result = []
	self.cols = set();self.pie = set();self.na = set()
	self.DFS(n,0,[])
	return self,_generate_result(n)
	
def DFS(self,n,row,cur_state):
	# recursion terminator
	if row>=n:
		self.result.append(cur_state)
		return
		
	for col in range(n):
		if col in self.cols or row+col in self.pie or row-col in self.na:
			#go die!
			continue
			
		#update the flags
		self.cols.add(col)
		self.pie.add(row+col)
		self.na.add(row-col)
		
		self.DFS(n,row+1,cur_state+[col])
		
		self.cols.remove(col)
		self.pie.remove(row+col)
		self.na.remove(row-col)
		
N皇后的位运算解法-python

def totalNQueens(self,n):
	if n<1:return []
	self.count = 0
	self.DFS(n,0,0,0,0)
	return self.count
	
def DFS(self,n,row,cols,pie,na):
	# recursion terminator
	if row>=n:
		self.count += 1
		return
		
	bits = (~(cols|pie|na))&((1<<n)-1) #得到当前所有的空位
	
	while bits:
		p = bits & -bits #取到最低位的1
		bits = bits & (bits-1) #表示在p位置上放入皇后
		self.DFS(n,row+1,cols|p,(pie|p)<<1,(na|p)>>1)
		# 不需要revert cols,pie,na的状态
		
Java版

class Solution{
	private int size;
	private int count;
	
	private void solve(int row,in ld,int rd){
		if(row==size){
			count++;
			return;
		}
		int pos = size&(~(row|ld|rd));
		while (pos!=0){
			int p = pos&(-pos);
			pos -= p; //pos&=pos-1;
			solve(row|p,(ld|p)<<1,(rd|p)>>1);
		}
	}
	
	public int totalNQueens(int n){
		count = 0;
		size = (1<<n)-1;
		solve(0,0,0);
		return count;
	}
}

二、布隆过滤器和LRU Cache
（1）布隆过滤器是一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。
优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。
它在现实中的应用有比特币网络；分布式系统（Map-Reduce）--Hadoop、search engine；Redis缓存；垃圾邮件、评论等的过滤。

python实现

from bitarray import bitarray
import mmh3

class BloomFilter:
	def __init__(self,size,hash_num):
		self.size = size
		self.hash_num = hash_num
		self.bit_array = bitarray(size)
		self.bit_array.setall(0)
		
	def add(self,s):
		for seed in range(self,hash_num):
			result = mmh3.hash(s,seed) % self.size
			self.bit_array(result) = 1
			
	def lookup(self,s):
		for seed in range(self.hash_num):
			result = mmh3.hash(s,seed)%self.size
			if self.bit_array[result]==0:
				return "Nope"
		return "Probably"
		
	bf = BloomFilter(500000,7)
	bf.add("dantezhao")
	print(bf.lookup("dantezhao"))
	print(bf.lookup("yy"))
	
（2）LRU Cache是HashTable+DoubleLinkedList实现的，查询和修改、更新的时间复杂度都是O(1)

其python实现

class LRUCache(object):

	def __init__(self,capacity):
		self.dic = collection.OrderedDict()
		self.remain = capacity
		
	def get(self,key):
		if key not in self,dic:
			return --1
		v = self.dic.pop(key)
		self.dic[key] = v # key as the newest one
		return v
		
	def put(self,key,value):
		if key in self.dic:
			self.dic.pop(key)
		else:
			if self.remain>0:
				self.remain==1
			else: # self.dic is full
				self.dic.popitem(last=False)
			self.dic[key] = value
			
Java版

public class LRUCache{
	
	private Map<Integer,Integer> map;
	
	public LRUCache(int capacity){
		map = new LinkedCappedHashMap<>(capacity);
	}
	
	public int get(int key){
		if(!map.containsKey(key) {return -1};}
		return map.get(key);
	}
	
	public void put(int key,int value){
		map.put(key,value);
	}
	
	private static class LinkedCappedHashMap<K,V> extends LinkedHashMap<K,V>{
		int maximumCapacity;
		
		LinkedCappedHashMap(int maximumCapacity){
			super(16,0.75f,true);
			this.maximumCapacity = maximumCapacity;
		}
		
		protected boolean removeEldestEntry(Map.Entry eldest){
			return size() > maximumCapacity;
		}
	}
}

三、排序
1、初级排序-O(n^2)
（1）选择排序(Selection Sort)
每次找最小值，然后放到待排序数组的起始位置。
（2）插入排序(Insertion Sort)
从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
（3）冒泡排序（Bubble Sort）
嵌套循环，每次查看相邻的元素如果逆序，则交换。
2、高级排序-O(N*LogN)
（4）快速排序(Quick Sort)
数组取标杆pivot，将小元素放pivot左边，大元素放右侧，然后依次对右边和右边的子数组继续快排，以达到整个序列有序。

public static void quickSort(int[] array,int begin,int end){
	if(end<=begin) return;
	int pivot = partition(array,begin,end);
	quickSort(array,begin,pivot-1);
	quickSort(array,pivot+1,end);
}

static int partition(int[] a,int begin,int end){
	//pivot:标杆位置，counter:小于pivot的元素的个数
	int pivot = end, counter = begin;
	for (int i = begin;i<end;i++){
		if(a[i]<a[pivot]){
			int temp = a[counter];a[counter]=a[i];a[i]=temp;
			counter++;
		}
	}
	int temp = a[pivot]; a[pivot] = a[counter] ; a[counter] = temp;
	return counter;
}
调用方式：quickSort(a,0,a.length-1)

（5）归并排序（Merge Sort） -- 分治
把长度为n的输入序列分成两个长度为n/2的子序列；
对这两个子序列分别采用归并排序；
将两个排序好的子序列合并成一个最终的排序序列。

public static void mergeSort(int[] array, int left,int right){
	if(right<=left) return;
	int mid = (left+right)>>1; //(left+right)/2
	
	mergeSort(array,left,mid);
	mergeSort(array,mid+1,right);
	merge(array,left,mid,right);
	
public static void merge(int[] arr,int left,int mid,int right){
	int[] temp  = new int[right-left+1]; //中间数组
	int i = left, j = mid + 1, k=0;
	
	while(i<=mid && j<=right){
		temp[k++]=arr[i]<=arr[j]?arr[i++]:arr[j++];
	}
	
	while (i<=mid) temp[k++]=arr[i++];
	while(j<=right) temp[k++]=arr[j++];
	
	for(int p=0;p<temp.length;p++){
		arr[left+p]=temp[p];
	}
	//也可以用System.arraycopy(a,start1,b,start2,length)
}

归并和快排具有相似性，但步骤顺序相反
归并：先排序左右数组，然后合并两个有序子数组
快排：先调配出左右子数组，然后对于左右子数组进行排序

（6）堆排序（Heap Sort）-- 堆插入O(logN)，取最大/小值O(1)
数组元素依次建立小顶堆；
依次取堆顶元素，并删除。

static void heapify(int[] array,int length,int i){
	int left = 2*i+1,right = 2*i+2;
	int largest = i;
	
	if(left<length && array[left]>array[largest]){
		largest=left;
	}
	if(right<length && array[right]>array[largest]){
		largest = right;
	}
	
	if(largest!=i){
		int temp = array[i];array[i] = array[largest];array[largest]=temp;
		heapify(array,length,largest);
	}
}

public static void heapSort(int[] array){
	if(array.length==0) return;
	
	int length = array.length;
	for(int i = length/2-1;i>=0;i--){
		heapify(array,length,i);
	}
	
	for(int i = length-1;i>=0;i--){
		int temp = array[0];array[0]=array[i];array[i]=temp;
		heapify(array,i,0);
	}
}

3、特殊排序-O(n)
（7）计数排序（Counting Sort）
计数排序要求输入的数据必须是有确定范围的整数。将输入的数据值转化为键存储在额外开辟的数组空间中；
然后依次把计数大于1的填充回原数组。
（8）桶排序（Bucket Sort）
桶排序（Bucket Sort）的工作原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。
（9）基数排序（Radix Sort）
基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。