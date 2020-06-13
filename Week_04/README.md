学习笔记
这一周学习了深度优先搜索和广度优先搜索、贪心算法（可以是倒排贪心/局部贪心）、二分查找。
其中深度优先搜索的模板递归写法：
visited = set()
def dfs(nod,visited):
	if node in visited: #terminator
		#already visited
		return
		
	visited.add(node)
	
	# process current node here
	...
	for next_node in node.children():
		if not next_node in visited:
			dfs(next_node,visited)
			
非递归写法：
def DFS(self,tree):
	if tree.root is None:
		return []
		
	visited,stack = [],[tree.root]
	
	while stack:
		node = stack.pop()
		visited.add(node)
		
		process(node)
		node = generate_related_nodes(node)
		stack.push(nodes)
		
	#other prosessing work

广度优先搜索模板为：
def BFS(graph,start,end):
	queue = []
	queue.append([start])
	visited.add(start)
	
	while queue:
		node = queue.pop()
		visited.add(node)
		
		process(node)
		nodes = generate_related_nodes(node)
		queue.push(nodes)
		
	#other prosessing work

二分法模板为：
left,right = 0,len(array)-1
while left<=right:
	mid=(left+right)/2
	if array[mid]==target:
		#find the target!
		break or return result
	elif array[mid]<target:
		left=mid+1;
	else:
		right=mid-1;

至于使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方，联想到剑指offer题11.旋转数组的最小数字。
将旋转数组对半分可以得到一个包含最小元素的新旋转数组，以及一个非递减排序的数组。
此时问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。
我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
通过修改二分查找算法进行求解（l 代表 low，m 代表 mid，h 代表 high）：
当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，此时令 h = m；
否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1。

参考：https://github.com/CyC2018/CS-Notes/blob/master/notes/11.%20%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97.md