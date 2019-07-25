package tencentselectpractice.easy

import model.ListNode
import kotlin.system.measureTimeMillis

/**
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */
class HasCycle {

    /**
     * 利用 hash 存访问记录
     */
    fun solution(head: ListNode?): Boolean {
        val keys: HashSet<ListNode?> = hashSetOf()
        var temp: ListNode? = head
        while (temp != null) {
            if (keys.contains(temp)) {
                return true
            } else {
                keys.add(temp)
            }
            temp = temp.next
        }
        return false
    }

    /**
     * 利用快慢指针
     */
    fun solution2(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        }
        var slow: ListNode? = head
        var fast: ListNode? = head.next
        while (slow != fast) {
            if (fast?.next == null) {
                return false
            }
            slow = slow?.next
            fast = fast.next?.next
        }
        return true
    }
}

fun main() {
    val list = ListNode(3).apply {
        next = ListNode(2).apply {
            next = ListNode(0).apply {
                next = ListNode(-4).apply {
                    next = ListNode(2).apply {
                        next = ListNode(0).apply {
                            next = ListNode(4)
                        }
                    }
                }
            }
        }
    }
    val time = measureTimeMillis {
        val cycle = HasCycle().solution2(null)
        println("$list 是否存在环: $cycle")
    }
    println("solution in $time ms")
}