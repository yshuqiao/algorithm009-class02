public class UniquePathsIi63 {
    //来源：https://leetcode-cn.com/problems/unique-paths-ii/solution/bu-tong-lu-jing-ii-by-leetcode-solution-2/
    //我们用f(i,j)表示从坐标（0,0）到坐标（i,j）的路径综述，u(i,j)表示坐标(i,j)是否可行，
    //如果坐标(i,j)有障碍物，u(i,j)=0,否则u(i,j)=1。
    //因为[机器人每次只能向下或者向右移动一步]，所以从坐标(0,0)到坐标(i,j)的路径总数的值
    //只取决于从坐标(0,0)到坐标(i-1,j)的路径总数和从坐标(0,0)到坐标(i,j-1)的路径总数，
    //即f(i,j)只能通过f(i-1,j)和f(i,j-1)转移得到。当坐标(i,j)本身有障碍的时候，任何路径都到不了f(i,j)，此时f(i,j)=0；
    //下面我们来讨论坐标(i,j)没有障碍的情况：如果坐标(i-1,j)没有障碍，那么就意味着从坐标(i-1,j)可以走到(i,j)，
    //即(i-1,j)位置对f(i,j)的贡献为f(i-1,j)，同理，当坐标(i,j-1)没有障碍的时候，(i,j-1)位置对f(i,j)的贡献为f(i,j-1)。
    //综上所述，我们可以得到这样的动态规划转移方程：
    //f(i,j)=0,若u(i,j)=0
    //f(i,j)=f(i-1,j)+f(i,j-1),若u(i,j)!=0
    //很显然我们可以给出一个时间复杂度O(nm)并且空间复杂度也是O(nm)的实现，由于这里f(i,j)只与f(i-1,j)和f(i,j-1)相关，
    //由于这里f(i,j)只与f(i-1,j)和f(i,j-1)相关，我们可以运用[滚动数组思想]把空间复杂度优化成O(m)。
    //[滚动数组思想]是一种常见的动态规划优化方法，在我们的题目中已经多次使用到，例如
    //[剑指Offer 46.把数字翻译成字符串]、[70.爬楼梯]等，当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，就可以考虑这种优化方法，
    //目的是给空间复杂度[降维]。
    //动态规划的题目分为两大类，一种是求最优解类，典型问题是背包问题，另一种就是计数类，比如这里的统计方案数的问题，它们都存在一定的递推性质。前者的递推性质还有一个名字，叫做 「最优子结构」 ——即当前问题的最优解取决于子问题的最优解，后者类似，当前问题的方案数取决于子问题的方案数。所以在遇到求方案数的问题时，我们可以往动态规划的方向考虑。
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int n = obstacleGrid.length, m = obstacleGrid[0].length;
            int[] f = new int[m];

            f[0] = obstacleGrid[0][0] == 0?1:0;
            for (int i=0;i<n;++i){
                for(int j=0;j<m;++j){
                    if(obstacleGrid[i][j]==1){
                        f[j]=0;
                        continue;
                    }
                    if(j-1>=0&&obstacleGrid[i][j-1]==0){
                        f[j] += f[j-1];
                    }
                }
            }
            return f[m-1];
        }
    }
}
