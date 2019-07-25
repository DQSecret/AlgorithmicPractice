package tencentselectpractice.easy

import model.TreeNode
import java.util.*
import kotlin.math.max
import kotlin.system.measureTimeMillis

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7

 * 返回它的最大深度 3 。
 */
class MaxDepth {

    /**
     * DFS（深度优先搜索)
     * 递归
     */
    fun solution(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            val lh = solution(root.left)
            val rh = solution(root.right)
            1 + max(lh, rh)
        }
    }

    /**
     * 层级遍历
     * 迭代: 利用队列的特性,先入先出
     */
    fun solution2(root: TreeNode?): Int {
        if (root == null) return 0

        var height = 0
        val q = LinkedList<TreeNode>()
        q.offer(root)

        while (q.isNotEmpty()) {
            height++
            for (i in 0..q.lastIndex) {
                val t = q.poll()
                t.left?.let { q.offer(it) }
                t.right?.let { q.offer(it) }
            }
        }

        return height
    }
}

fun main() {
    val tn = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    val time = measureTimeMillis {
        val depth = MaxDepth().solution(tn)
        println("DFS: 最大深度为: $depth")
        val depth2 = MaxDepth().solution2(tn)
        println("层级遍历-Queue: 最大深度为: $depth2")
    }
    println("solution in $time ms")
}