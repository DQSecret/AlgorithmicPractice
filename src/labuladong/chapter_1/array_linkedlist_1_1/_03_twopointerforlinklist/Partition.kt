package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
 * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 */
class Partition {

    /**
     * 老规矩，直觉
     */
    fun solution(head: ListNode?, x: Int): ListNode? {
        // 0. 特殊情况
        head ?: return null
        // 1. 虚拟2个头节点
        val low = ListNode(-1)
        val high = ListNode(-1)
        var pL = low
        var pH = high
        // 2. 执行操作
        var pHead = head
        while (pHead != null) {
            if (pHead.`val` < x) {
                pL.next = pHead
                pL = pL.next!!
            } else {
                pH.next = pHead
                pH = pH.next!!
            }
            // P1的逻辑
//            pHead = pHead.next
            // P2: 也可以每次，都把pHead(当前节点)扔掉
            val temp = pHead.next // 把之后节点，先临时存起来
            pHead.next = null // 把之后的值，都清空。顺带的会把上面的pL和pH也同步修改
            pHead = temp // 把之后的，赋值给当前节点
        }
        // P1: 自己的想法，可以最后把剩下的全扔掉，防止重复
//        pL.next = null
//        pH.next = null
        // 把高低两端拼起来
        pL.next = high.next
        // 3. 输出结果
        return low.next
        // 总结一下，我个人觉得，P1比P2的逻辑，好理解，而且，仅执行一次，效率高。
    }
}

fun main() {
    Partition().run {
        solution(
            convertListNode2(1, 4, 3, 2, 5, 2),
            3,
        ).also {
            println(it)
        }
        solution(
            convertListNode2(2, 1),
            2,
        ).also {
            println(it)
        }
    }
}