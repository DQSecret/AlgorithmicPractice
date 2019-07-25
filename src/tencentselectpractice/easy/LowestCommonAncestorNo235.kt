package tencentselectpractice.easy

import model.TreeNode

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“
 *  对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 * ”

 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。

 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
class LowestCommonAncestor {

    /*

    我们来复习一下二叉搜索树（BST）的性质：
    节点 N 左子树上的所有节点的值都小于等于节点 N 的值
    节点 N 右子树上的所有节点的值都大于等于节点 N 的值
    左子树和右子树也都是 BST

     */

    /**
     * 方法 1: 递归
     * 1. 从根节点开始遍历树
     * 2. 如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
     * 3. 如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
     * 4. 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 pp 和节点 qq 的 LCA 了
     */
    fun solution1(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        root ?: return null

        val parent = root.`val`
        val leftSub = p.`val`
        val rightSub = q.`val`

        if (leftSub < parent && rightSub < parent) {
            return solution1(root.left, p, q)
        }
        if (leftSub > parent && rightSub > parent) {
            return solution1(root.right, p, q)
        }
        return root
    }

    /**
     * 方法 2: 迭代
     * 其实就是把 递归 转化成 迭代
     */
    fun solution2(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode? {
        val pVal = p.`val`
        val qVal = q.`val`

        var lca: TreeNode? = root
        while (lca != null) {
            val parentVal = lca.`val`
            lca = if (pVal < parentVal && qVal < parentVal) {
                lca.left
            } else if (pVal > parentVal && qVal > parentVal) {
                lca.right
            } else {
                return lca
            }
        }
        return null
    }
}

fun main() {
    testLowestCommonAncestor()
}

fun testLowestCommonAncestor() {
    val root =
            TreeNode(6).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(0)
                    right = TreeNode(4).apply {
                        left = TreeNode(3)
                        right = TreeNode(5)
                    }
                }
                right = TreeNode(8).apply {
                    left = TreeNode(7)
                    right = TreeNode(9)
                }
            }

    var p = TreeNode(2)
    var q = TreeNode(8)
    var common = LowestCommonAncestor().solution1(root, p, q)
    println("节点 ${p.`val`} 和节点 ${q.`val`} 的最近公共祖先是 ${common?.`val`}。")

    p = TreeNode(2)
    q = TreeNode(4)
    common = LowestCommonAncestor().solution2(root, p, q)
    println("节点 ${p.`val`} 和节点 ${q.`val`} 的最近公共祖先是 ${common?.`val`}。")

    p = TreeNode(3)
    q = TreeNode(5)
    common = LowestCommonAncestor().solution2(root, p, q)
    println("节点 ${p.`val`} 和节点 ${q.`val`} 的最近公共祖先是 ${common?.`val`}。")
}