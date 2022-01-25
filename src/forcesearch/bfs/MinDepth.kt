package forcesearch.bfs

import model.TreeNode

/**
 * DFS 算法练习题
 *
 * 二叉树的最小深度
 *
 * @author DQ For Olivia
 * @since 2022/1/25 11:29 上午
 * @see <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/113/">讲解</a>
 */
class MinDepth {

    /**
     * 示例 1：
     *       3
     *    9  |   20
     *  _|_     15|7
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     *
     * 示例 2：
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     */
    fun solution(root: TreeNode?): Int {
        if (root == null) return 0
        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        var step = 1
        all@ while (queue.isNotEmpty()) {
            val size = queue.size
            it@ for (i in 0 until size) {
                val curr = queue.removeFirst()
                if (curr.left == null && curr.right == null) {
                    break@all
                }
                curr.left?.let { queue.addLast(it) }
                curr.right?.let { queue.addLast(it) }
            }
            step += 1
        }
        return step
    }
}

fun main() {
    // true
    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    MinDepth().solution(root).also {
        println(it)
    }
}
