package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray.RemoveDuplicates
import model.ListNode
import model.convertListNode2

/**
 * 属于[RemoveDuplicates]的扩展
 * 删除排序链表中的重复元素
 *
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */
class DeleteDuplicates {

    /**
     * 在不使用额外空间的情况下，使用双指针来实现
     */
    fun solution(head: ListNode?): ListNode? {
        val dummy = ListNode(Int.MIN_VALUE)
        var slow = dummy
        var fast = head
        while (fast != null) {
            if (slow.`val` == fast.`val`) {
                fast = fast.next
            } else {
                // 删除重复元素
                slow.next = fast
                slow = slow.next!!
                fast = fast.next
                // slow?.next = null // 放在这里，也可以
            }
        }
        // 再把后面的切掉
        slow.next = null
        return dummy.next
    }

    /**
     * 在不使用额外空间的情况下，使用双指针来实现
     * 即，原地修改
     */
    fun solution2(head: ListNode?): ListNode? {
        var slow = head
        var fast = head?.next
        while (fast != null) {
            if (slow?.`val` == fast.`val`) {
                fast = fast.next
            } else {
                // 注意这里3、4行的顺序，
                slow?.next = fast
                slow = slow?.next
                fast = fast.next
                // slow?.next = null // 放在这里，也可以
            }
        }
        // 断开后面的值，因为链表的特性，每次更新是一整条链的后续都改了，所以需要注意这种，末尾
        slow?.next = null
        return head
    }
}

fun main() {
    DeleteDuplicates().run {
        solution(convertListNode2(1, 1, 2)).also { println(it) }
        solution2(convertListNode2(1, 1, 2)).also { println(it) }
        solution(convertListNode2(1, 1, 2, 3, 3)).also { println(it) }
        solution2(convertListNode2(1, 1, 2, 3, 3)).also { println(it) }
        solution(convertListNode2(-1, 0, 0, 0, 0, 3, 3)).also { println(it) }
        solution2(convertListNode2(-1, 0, 0, 0, 0, 3, 3)).also { println(it) }
    }
}