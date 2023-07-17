package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 */
class MiddleNode {

    /**
     * 老规矩，先上直觉
     * 1. 两次遍历，很容易想到
     * 2. 直接上，双指针，一个走1步，另一个走2步，不就完了？
     */
    fun solution(head: ListNode?): ListNode? {
        // 多一个虚拟头节点，可以使得单数时，包含中间结点
        val dummy = ListNode(-1)
        dummy.next = head
        var p1: ListNode? = dummy
        var p2: ListNode? = dummy
        while (p2 != null) {
            p1 = p1?.next // p1走1步
            p2 = p2.next?.next // p2走2步
        }
        return p1
    }

    /**
     * 不使用，虚拟头结点的话，怎么处理？
     */
    fun solution2(head: ListNode?): ListNode? {
        var p1: ListNode? = head
        var p2: ListNode? = head
        while (p2?.next != null) {
            p1 = p1?.next // p1走1步
            p2 = p2.next?.next // p2走2步
        }
        return p1
    }
}

fun main() {
    MiddleNode().run {
        solution(convertListNode2(1, 2, 3, 4, 5)).also { println(it) }
        solution2(convertListNode2(1, 2, 3, 4, 5)).also { println(it) }
        solution(convertListNode2(1, 2, 3, 4, 5, 6)).also { println(it) }
        solution2(convertListNode2(1, 2, 3, 4, 5, 6)).also { println(it) }
    }
}