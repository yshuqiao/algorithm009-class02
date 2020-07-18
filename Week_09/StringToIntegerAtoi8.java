public class StringToIntegerAtoi8 {

    //来源：https://leetcode-cn.com/problems/string-to-integer-atoi/solution/san-chong-fang-fa-zheng-chang-bian-li-you-xian-zhu/

    class Solution {
        public int myAtoi(String str) {
            int i = 0, n = str.length();
            while (i < n && str.charAt(i) == ' ') i++;  //前面是空格的时候不处理，继续往前推进
            if (i == n) return 0;
            int flag = 1;
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                if (str.charAt(i) == '-') flag = -1;
                i++;
            }
            int ans = 0;
            while (i < n && Character.isDigit(str.charAt(i))) {
                int temp = str.charAt(i) - '0';
                if (flag == 1 && (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && temp > 7)))
                    return Integer.MAX_VALUE;
                //以正数为例，考虑越界问题
                if (flag == -1 && (ans > -(Integer.MIN_VALUE / 10) || (ans == -(Integer.MIN_VALUE / 10) && temp > 8)))
                    return Integer.MIN_VALUE;
                ans = ans * 10 + temp;
                i++;

            }
            return ans * flag;

        }
    }
}
