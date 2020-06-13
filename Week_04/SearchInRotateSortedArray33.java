//来源https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/duo-si-lu-wan-quan-gong-lue-bi-xu-miao-dong-by-swe/
public class SearchInRotateSortedArray33 {
    class Solution {
        public int search(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1, mid = 0;
            while (lo <= hi) {
                mid = lo + (hi - lo) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                //先根据nums[mid]与nums[lo]的关系判断mid是在左端还是右段
                if (nums[mid] >= nums[lo]) {
                    //再判断target是在mid的左边还是右边，从而调整左右边界
                    if (target >= nums[lo] && target < nums[mid]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    if (target > nums[mid] && target <= nums[hi]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
}
