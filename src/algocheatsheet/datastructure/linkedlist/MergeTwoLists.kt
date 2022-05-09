package algocheatsheet.datastructure.linkedlist

import model.ListNode
import model.convertListNode2

/**
 * 合并两个有序链表
 *
 * @author DQ For Olivia
 * @since 2022/5/9 15:53
 * @see <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">力扣题目</a>
 * @see <a href="https://labuladong.github.io/algo/2/17/16/">讲解</a>
 */
class MergeTwoLists {

    /**
     * 示例:
     *
     * 输入: l1 = [1,2,4], l2 = [1,3,4]
     * 输出: [1,1,2,3,4,4]
     *
     * 输入: l1 = [], l2 = []
     * 输出: []
     *
     * 输入: l1 = [], l2 = [0]
     * 输出: [0]
     *
     * 提示:
     *   1. 两个链表的节点数目范围是 [0, 50]
     *   2. -100 <= Node.val <= 100
     *   3. l1 和 l2 均按 非递减顺序 排列
     *
     * 重点:
     * *代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，也就是 dummy 节点。*
     */
    fun solution(l1: ListNode?, l2: ListNode?): ListNode? {
        // 创建一个虚拟头结点,并用一个指针指向它
        val dummy = ListNode(-1)
        var point: ListNode? = dummy
        // 用两个指针,分别记录每一个链表的节点
        var p1 = l1
        var p2 = l2
        while (p1 != null && p2 != null) {
            // 将小值追加到point
            if (p1.`val` < p2.`val`) {
                point?.next = p1
                p1 = p1.next
            } else {
                point?.next = p2
                p2 = p2.next
            }
            // point 更新至下一个
            point = point?.next
        }
        if (p1 != null) {
            point?.next = p1
        }
        if (p2 != null) {
            point?.next = p2
        }
        return dummy.next
    }
}

fun main() {
    val l1 = convertListNode2(1, 2, 4)
    val l2 = convertListNode2(1, 3, 4)
    MergeTwoLists().solution(l1, l2).also {
        println(it)
    }
}