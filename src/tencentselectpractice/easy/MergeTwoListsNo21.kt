package tencentselectpractice.easy

import model.ListNode
import kotlin.system.measureTimeMillis

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
class MergeTwoLists {

    /**
     * 递归
     */
    fun solution(l1: ListNode?, l2: ListNode?): ListNode? {
        return when {
            l1 == null -> l2
            l2 == null -> l1
            l1.`val` < l2.`val` -> {
                l1.next = solution(l1.next, l2)
                l1
            }
            else -> {
                l2.next = solution(l2.next, l1)
                l2
            }
        }
    }

    /**
     * 迭代
     */
    fun solution2(l1: ListNode?, l2: ListNode?): ListNode? {
        // kotlin 参数不能修改
        var ln1 = l1
        var ln2 = l2
        // 结果
        val result = ListNode(-1)
        // 用于承载 next 的临时变量
        var prev: ListNode? = result
        while (ln1 != null && ln2 != null) {
            if (ln1.`val` <= ln2.`val`) {
                prev?.next = ln1
                ln1 = ln1.next
            } else {
                prev?.next = ln2
                ln2 = ln2.next
            }
            prev = prev?.next
        }
        // 最后一个, 其中一个为 null, 另一个可能还有值存在
        prev?.next = ln1 ?: ln2
        // 返回结果
        return result.next
    }
}

fun main() {
    val l1 = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(4)
        }
    }
    val l2 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    }
    val time = measureTimeMillis {
        val result = MergeTwoLists().solution2(l1, l2)
        println(result)
    }
    println("solution in $time ms")
}