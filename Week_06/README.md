学习笔记

一、Trie树模板

class Trie(object):
	
	def __init__(self):
		self.root = {}
		self.end_of_word = "#"
		
	def insert(self,word):
		node = self.root
		for char in word:
			node = node.setdefault(char,{})
		node[self.end_of_word] = self.end_of_word
		
	def search(self,word):
		node = self.root
		for char in word:
			if char not in node:
				return False
			node = node[char]
		return self.end_of_word in node
	
	def startsWith(self,prefix):
		node = self.root
		for char in prefix:
			if char not in node:
				return False
			node = node[char]
		return True

二、并查集模板

# Java
class UnionFind{
	private int count = 0;
	private int[] parent;
	public UnionFind(int n){
		count = n;
		parent = new int[n];
		for(int i=0;i<n;i++){
			parent[i] = i;
		}
	}
	
	public int find(int p){
		while (p!=parent[p]){
		parent[p] = parent[parent[p]];
		p = parent[p];
		}
		return p;
	}
	
	public void union(int p,int q){
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP==rootQ) return;
		parent[rootP] = rootQ;
		count--;
		
# Python
def init(p):
	# for i = 0 .. n: p[i] = i;
	p =  [i for i in range(n)]
	
def union(self,p,i,j):
	p1 = self.parent(p,j)
	p2 = self.parent(p,j)
	p[p1] = p2
	
def parent(self,p,i):
	root = i
	while p[root] != root:
		root = p[root]
	while p[i] != i: #路径压缩？
		x = i;i = p[i];p[x] = root
	return root


三、A*启发式搜索模板

def AstarSearch(graph,start,end):

	pq = collections.priority_queue() #优先级->估价函数
	pd.append([start])
	visited.add(start)
	
	while pq:
		node = pq.pop() #can we add more intelligence here?
		visited.add(node)
		
		process(node)
		nodes = generate_related_nodes(node)
		unvisited = [node for node in nodes if node not in visited]
		pq.push(unvisited)

四、树的三种遍历模板

def preorder(self,root):
	
	if root:
		self.traverse_path.append(root.val)
		self.preorder(root.left)
		self.preorder(root.right)
		
def inorder(self,root):

	if root:
		self.inorder(root.left)
		self.traverse_path.append(root.val)
		self.inorder(root.right)
		
def postorder(self,root):

	if root:
		self.postorder(root.left)
		self.postorder(root.right)
		self.traverse_path.append(root.val)
