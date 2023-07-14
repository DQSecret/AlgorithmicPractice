package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 */
class MergeKLists {

    /**
     * 来吧，老规矩，直觉
     */
    fun solution(lists: Array<ListNode?>): ListNode? {
        // 1. 虚拟头节点
        val dummy = ListNode(-1)
        var p: ListNode = dummy
        // 2. 各种操作
        while (true) {
            // 每一轮的起始值
            var min = ListNode(Int.MAX_VALUE)
            var index = -1 // 需要用这个，记录最后哪一个是最小的
            // 遍历，找到最小值
            for ((i, node) in lists.withIndex()) {
                node ?: continue
                if (node.`val` < min.`val`) {
                    min = node
                    index = i
                }
            }
            // 终止条件
            if (index == -1) break
            // 更新最终链表
            p.next = min
            lists[index] = min.next
            p = p.next!!
        }
        // 3. 返回结果
        return dummy.next
    }

    /**
     * // TODO by DQ 2023.7.14: 这里需要等学习了[二叉树和优先级二叉堆],之后再来搞！！！
     */
    fun solution2(lists: Array<ListNode?>): ListNode? {
        return null
    }
}

fun main() {
    MergeKLists().run {
        solution(
            arrayOf(
                convertListNode2(1, 4, 5),
                convertListNode2(1, 3, 4),
                convertListNode2(2, 6),
            )
        ).also {
            println(it)
        }
    }
}