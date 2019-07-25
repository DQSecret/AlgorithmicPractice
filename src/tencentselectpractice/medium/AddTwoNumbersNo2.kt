package tencentselectpractice.medium

import model.ListNode
import model.convertListNode2


/**
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 难度：中等
 * tips：单列表
 */
class AddTwoNumbers {

    fun solution(l1: ListNode?, l2: ListNode?): ListNode? {
        // 声明返回值
        val dummyHead = ListNode(0) // 假的头部
        var curr: ListNode? = dummyHead
        // 需要的临时参数
        var temp1: ListNode? = l1
        var temp2: ListNode? = l2
        var carry = 0 // 进位
        // 循环
        while (temp1 != null || temp2 != null) {
            // 计算当前位的和
            val sum = (temp1?.`val` ?: 0) + (temp2?.`val` ?: 0) + carry
            // 计算进位
            carry = sum / 10
            // 赋值低位, 即 next
            curr?.next = ListNode(sum % 10)
            // 更新引用值
            curr = curr?.next
            // 更新 temp
            temp1 = temp1?.next
            temp2 = temp2?.next
        }
        // 最后一次进位的值, 循环中回遗漏
        if (carry > 0) curr?.next = ListNode(carry)
        // 返回
        return dummyHead.next
    }
}

fun main() {
    val l1 = convertListNode2(2, 4, 3)
    val l2 = convertListNode2(5, 6, 7)
    println("l1: $l1")
    println("l2: $l2")
    println("sum: " + AddTwoNumbers().solution(l1, l2))
}