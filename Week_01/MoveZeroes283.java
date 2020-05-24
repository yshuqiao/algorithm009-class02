import java.util.Arrays;
//参考：https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
public class MoveZeroes283 {
    public static void main(String[] args){

        MoveZeroes283.Solution solution = new MoveZeroes283().new Solution();
        int[] nums={1,0,2,0,3,0};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    class Solution {
        public void moveZeroes(int[] nums) {
            if (nums == null) {
                return;
            }
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }
    }

}
