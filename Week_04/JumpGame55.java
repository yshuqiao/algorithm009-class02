public class JumpGame55 {
    class Solution {
        //贪心，时间复杂度O(n)，空间复杂度O(1)
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int rightmost = 0;
            for(int i=0;i<n;++i){
                if(i<=rightmost){
                    rightmost=Math.max(rightmost,i+nums[i]);
                    if(rightmost>=n-1){
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
