学习笔记
	动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构），它们的共性是找到重复子问题，差异性在于动态规划能找到最优子结构、中途可以淘汰次优解。
	动态规划的关键点（子问题、状态定义、DP方程）：
	1.最优子结构 opt[n]=best_of(opt[n-1],opt[n-2],...)
	2.储存中间状态：opt[i]
	3.递推公式：（状态转移方程或者DP方程）
	Fib opt[i]=opt[n-1]+opt[n-2]
	二维路径：opt[i,j]=opt[i+1][j]+opt[i][j+1](且判断a[i,j]是否空地)
	动态规划小结：
	1.打破自己的思维惯性，形成机器思维（别凭经验感觉模棱两可）
	2.理解复杂逻辑的关键
	3.也是职业进阶的要点要领
	一个值得读的链接（一个方法团灭 6 道股票问题）https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/