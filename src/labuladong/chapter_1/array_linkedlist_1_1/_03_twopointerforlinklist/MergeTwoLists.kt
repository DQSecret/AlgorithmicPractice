package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 合并两个有序链表
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
class MergeTwoLists {

    /**
     * 来吧，老规矩，按照直觉写一个
     */
    fun solution(list1: ListNode?, list2: ListNode?): ListNode? {
        // 0.特殊情况
        list1 ?: return list2
        list2 ?: return list1
        // 1.建立 head
        val head = ListNode(-1)
        var point: ListNode = head
        var p1: ListNode? = list1
        var p2: ListNode? = list2
        // 2. 执行各种操作
        while (p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                point.next = p1
                p1 = p1.next
            } else {
                point.next = p2
                p2 = p2.next
            }
            point = point.next!!
        }
        if (p1 == null) point.next = p2
        if (p2 == null) point.next = p1
        // 3.返回 head.next
        return head.next
    }
}

fun main() {
    MergeTwoLists().run {
        solution(
            convertListNode2(1, 2, 4),
            convertListNode2(1, 3, 4),
        ).also { println(it) }
        solution(
            convertListNode2(),
            convertListNode2(),
        ).also { println(it) }
        solution(
            convertListNode2(),
            convertListNode2(0),
        ).also { println(it) }
    }
}