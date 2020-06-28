public class SplitArrayLargestSum410 {
    class Solution {
        public int splitArray(int[] nums, int m) {
            //动态规划法：
            //这个问题满足无后向性的特点。我们可以用动态规划来解决它。
            //无后向性的特点意味着，一旦当前状态确定了，它就不会被之后的状态影响。
            //在这个问题里面，如果我们将nums[0...i]分成j份时得到了当前最小的分割数组的最大值，
            //不论后面的部分怎么分割这个值不会受到影响
            //算法：
            //首先我们把f[i][j]定义为nums[0...i]分成j份时能得到的最小的分割子数组最大值。
            //对于第j个子数组，它为数组中下标k+1到i的这一段。因此,
            //f[i][j]可以从max(f[k][j-1],nums[k+1]+...+nums[i])这个公式中得到。
            //遍历所有可能的k，会得到f[i][j]的最小值。
            //整个算法的最终答案为f[n][m]，其中n为数组的大小。
            //复杂度分析：
            //时间复杂度：O(n^2*m)
            //总状态数为O(n*m)。为了计算每个状态f[i][j]，需要遍历整个数组去找到那个最优的k，
            //这里会产生O(n)次循环。所以总时间复杂度为O(n^2*m).
            //空间复杂度：O(n*m)
            //空间复杂度为状态总数，也就是O(n*m)
            int n = nums.length;
            int[][] f = new int[n + 1][m + 1];
            int[] sub = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    f[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < n; i++) {
                sub[i + 1] = sub[i] + nums[i];
            }
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    for (int k = 0; k < i; k++) {
                        f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                    }
                }
            }
            return f[n][m];
        }
    }
}
