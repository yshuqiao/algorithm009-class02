import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal94 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> aStack = new Stack<>();
            TreeNode curr = root;

            while(curr!=null || !aStack.isEmpty()){
                while(curr!=null){
                    aStack.push(curr);
                    curr = curr.left;
                }
                curr = aStack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
            return res;
        }
    }
}
