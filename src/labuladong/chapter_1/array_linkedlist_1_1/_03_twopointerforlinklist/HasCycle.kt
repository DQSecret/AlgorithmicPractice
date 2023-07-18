package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2,1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 */
class HasCycle {

    /**
     * 标准的双指针(快慢)题目
     */
    fun solution(head: ListNode?): Boolean {
        var p1 = head
        var p2 = head
        while (p2?.next != null) {
            p1 = p1?.next
            p2 = p2.next?.next
            if (p1 == p2) return true
        }
        return false
    }

    /**
     * 进阶版，不仅判断是否有环，还得确定，环的起始点？
     * 快慢指针，无法准确判断，环，的起始点，但是，hashset可以啊...
     */
    fun detectCycle(head: ListNode?): ListNode? {
        val set = hashSetOf<ListNode>()
        var p = head
        while (p != null) {
            if (set.contains(p)) {
                return p
            } else {
                set.add(p)
                p = p.next
            }
        }
        return null
    }

    /**
     * 利用快慢指针的思路去解题
     */
    fun detectCycle2(head: ListNode?): ListNode? {
        // 1. 首先判断是否有环
        var hasCycle = false
        var p1 = head
        var p2 = head
        while (p2?.next != null) {
            p1 = p1?.next
            p2 = p2.next?.next
            if (p1 == p2) {
                hasCycle = true
                break
            }
        }
        // 2.1. 无环的情况下，直接返回null
        if (!hasCycle) return null
        // 2.2. 有环的情况下，一定是，快的追上了慢的，(并且快的多走了一圈)
        // 即，快的走了2n步，慢的走了n步，则，快的比慢的多走了n步，n也就是，环的长度
        //
        // 提出2个概念，相遇点，环的起始点
        //
        // 假设，环的起始点<->相遇点，距离k步，
        // 已知：相遇时，慢的走了n步，即，头节点<->相遇点，距离n步
        // 则：头节点<->环的起始点，(n-k)步
        //
        // 而，环的长度，为n步，且，环的起始点<->相遇点，距离k步
        // 则，当从相遇点，继续往前走，下一次回到环的起始点时，
        // 即，相遇点<->下一轮的环的起始点，距离(n-k)步
        //
        // 综上所述：
        // 1. 相遇时，快的比慢的，夺走了：2n-n=n步，且环的长度为n步
        // 2. 假设，环的起始点<->相遇点，距离k步，
        // 3. 则，头节点<->环的起始点，(n-k)步，
        // 4. 且，相遇点<->下一轮的环的起始点，距离(n-k)步
        // 5. 故，当相遇时，使双指针速度相同，并一起往前走(n-k)步，必定会再次，在【环的起始点】相遇！！！
        //
        // eg：
        // 链表：12 34567 34567 34567，开头2个节点，之后3..7成环
        // 则相遇时，双指针，所走线路和步数，如下：
        // slow 1234【5】6734567... = 5步
        // fast 2463【5】463567...  = 10步(包含一圈)
        // 观察可得，环是：【34567】长度为：5
        // 计算可得，快-慢：10-5=5步
        p2 = head
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1
            }
            p1 = p1.next
            p2 = p2.next
        }
        return null
    }
}

fun main() {
    HasCycle().run {
        convertListNode2(3, 2, 0, -4, 2, 0, -4, 2, 0, -4, 2, 0, -4, 2, 0, -4).run {
            solution(this).also { println(it) }
            detectCycle(this).also { println(it) }
            detectCycle2(this).also { println(it) }
        }
        convertListNode2(1, 2, 1, 2, 1, 2, 1, 2).run {
            solution(this).also { println(it) }
            detectCycle(this).also { println(it) }
            detectCycle2(this).also { println(it) }
        }
        convertListNode2(1).run {
            solution(this).also { println(it) }
            detectCycle(this).also { println(it) }
            detectCycle2(this).also { println(it) }
        }
    }
}