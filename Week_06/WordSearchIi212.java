
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//来源：https://leetcode-cn.com/problems/word-search-ii/solution/dan-ci-sou-suo-ii-by-leetcode/

//java:单词搜索 II
//时间复杂度：O(M(4*3^(L-1))，其中M是二维网格中的单元格数，L是单词的最大长度。
//计算回溯算法将执行的确切步数是一个棘手的问题。我们为这个问题的最坏情况提供了该步骤的上限。
//该算法循环遍历二维网格中的所有单元，因此在复杂度公式中我们有M作为因子。然后将其归结为每个启动单元所需的最大步骤数（即4*3^(L-1))。
//假设单词的最大长度是L，从一个单元格开始，最初我们最多可以探索4个方向。假设每个方向都是有效的（最坏情况），
//在接下来的探索中，我们最多有3个相邻的单元（不包括我们来的单元）要探索。
//因此，在回溯探索期间，我们最多遍历4*3^(L-1)个单元格。
//你可能会想最坏的情况是什么样子。这里有一个例子。想象一下，二维网格中的每个单元都包含字母a，
//单词词典包含一个单词['aaaa']。这是算法将遇到的最坏情况之一。
//注意，上述时间复杂性是在 Trie 数据结构一旦构建就不会改变的假设下估计的。如果采用优化策略逐步删除 Trie 中的节点，则可以大大提高时间复杂度，因为一旦匹配词典中的所有单词，即 Trie 变为空，回溯的成本就会降低到零。
//
//空间复杂度：O(N)，其中N是字典中的字母总数
//算法消耗的主要空间是我们构建的Trie数据结构。在最坏的情况下，如果单词之间没有前缀重叠，
//则Trie将拥有与所有单词的字母一样多的节点。也可以选择在Trie中保留单词的副本。
// 因此，我们可能需要2N的空间用于Trie。

public class WordSearchIi212 {
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;

        public TrieNode() {
        }
    }

    class Solution {
        char[][] _board = null;
        ArrayList<String> _result = new ArrayList<String>();

        public List<String> findWords(char[][] board, String[] words) {
            //Step 1).Construct the Trie
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word; //store words in Trie
            }

            this._board = board;
            // Step 2).Backtracking starting for each cell in the board
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    if (root.children.containsKey(board[row][col])) {
                        backtracking(row, col, root);
                    }
                }
            }
            return this._result;
        }

        private void backtracking(int row, int col, TrieNode parent) {
            Character letter = this._board[row][col];
            TrieNode currNode = parent.children.get(letter);

            // check if  there is any match
            if (currNode.word != null) {
                this._result.add(currNode.word);
                currNode.word = null;
            }

            // mark the current letter before the EXPLORATION
            this._board[row][col] = '#';

            //explore neighbor cells in around-clock directions:up,right,down,left
            int[] rowOffset = {-1, 0, 1, 0};
            int[] colOffset = {0, 1, 0, -1};
            for (int i = 0; i < 4; ++i) {
                int newRow = row + rowOffset[i];
                int newCol = col + colOffset[i];
                if (newRow < 0 || newRow >= this._board.length || newCol < 0
                        || newCol >= this._board[0].length) {
                    continue;
                }
                if (currNode.children.containsKey(this._board[newRow][newCol])) {
                    backtracking(newRow, newCol, currNode);
                }
            }

            //End of EXPLORATION,restore the original letter in the board.
            this._board[row][col] = letter;

            // Optimization:incrementally remove the leaf nodes
            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }
        }
    }
}