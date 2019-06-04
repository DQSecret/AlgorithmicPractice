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

    fun solution(l1: ListNode, l2: ListNode): ListNode {
        println("l1: $l1 - l2: $l2")
        // TODO("有点难，以后再说")
        return l1
    }
}

data class ListNode(var value: Int, var next: ListNode? = null) {
    override fun toString(): String {
        return "${next?.toString() ?: ""}$value"
    }
}

fun main() {
    val l1 = ListNode(2, ListNode(4, ListNode(3)))
    val l2 = ListNode(5, ListNode(6, ListNode(4)))
    val result = AddTwoNumbers().solution(l1, l2)
    println(result)
}