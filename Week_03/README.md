学习笔记
这周主要学习了递归、分治、回溯。
递归的代码模板是：
public void recur(int level,int param){
	//terminator，递归终止条件
	if(level>MAX_LEVEL){
		//process result
		return;
	}
	
	//process current logic，处理当前层逻辑
	process(level,param);
	
	//drill down，下探到下一层
	recur(level+1,newParam)
	
	//reverse the current status if needed，清理当前层
}

分治的代码模板是：
def divide_conquer(problem,param1,param2,...)
	# recursion terminator，递归终止条件
	if problem is None:
		print_result
		return
	# prepare data
	data = prepare_data(problem)
	subproblems = split_problem(problem,data)
	# conquer subproblems
	subresult1 = self,divide_conquer(subproblems[0],p1,...)
	subresult2 = self,divide_conquer(subproblems[1],p1,...)
	subresult3 = self,divide_conquer(subproblems[2],p1,...)
	...
	# process and generate the final result
	result = process_result(subresult1,subresult2,subresult3,...)