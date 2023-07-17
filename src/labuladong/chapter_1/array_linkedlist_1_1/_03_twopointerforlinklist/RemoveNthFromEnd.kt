package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
class RemoveNthFromEnd {

    /**
     * 在删除之前，简化版本，就是先找到，倒数第n个结点
     *
     * 1. 直觉就是两次循环，一次找到size，第二次找到目标值
     */
    fun test(head: ListNode?, n: Int): Int? {
        var size = 0
        var p = head
        while (p != null) {
            size += 1
            p = p.next
        }
        println("总共有${size}个结点")
        p = head
        val aim = size - n + 1
        var index = 1
        while (true) {
            if (index == aim) break
            index += 1
            p = p?.next
        }
        return p?.`val`.also { println("倒数第${n}个结点是：$it") }
    }

    /**
     * 有没有只遍历1次，就可以的方案？
     *
     * 1. 利用双指针，让2个指针，相隔n和间距，就行
     */
    fun test2(head: ListNode?, n: Int): Int? {
        var p = head
        var gap = 0
        var pAim = head
        while (p != null) {
            p = p.next
            if (gap >= n) {
                pAim = pAim?.next
            } else {
                gap += 1
            }
        }
        return pAim?.`val`
    }

    /**
     * 有了[test2]的基础，就可以来写算法了
     */
    fun solution(head: ListNode?, n: Int): ListNode? {
        // 找到倒数第n个的上一个，才能进行剔除操作
        var pHead = head
        var count = 0
        var gap = 0
        var pAim = head
        while (pHead != null) {
            pHead = pHead.next
            count += 1
            if (gap < n + 1) { // 倒数第n+1个
                gap += 1
            } else {
                pAim = pAim!!.next
            }
        }
        // 特殊情况
        if (count == n) {
            return head!!.next
        }
        // 找到之后，开始剔除下一个，把之后的元素拼接起来
        val after = pAim!!.next?.next
        pAim.next = after
        return head
    }

    /**
     * 和[test2]一个逻辑，只是代码方式不一样
     * 效率更高，因为[test2]中if判断，在while循环中，执行了很多次
     */
    fun test3(head: ListNode?, n: Int): Int? {
        var p = head
        for (i in 1..n) {
            p = p?.next
        }
        var pAim = head
        while (p != null) {
            p = p.next
            pAim = pAim?.next
        }
        return pAim?.`val`
    }

    /**
     * 在[test3]的基础上，优化算法
     */
    fun solution2(head: ListNode?, n: Int): ListNode? {
        var size = 0
        var p = head
        // 找到上一个，才能剔除，所以，n+1
        for (i in 1..n + 1) {
            if (p != null) size += 1
            p = p?.next
        }
        // 已经到最后了，证明，n>=size，即剔除到第一个...
        if (n >= size) {
            return head?.next
        }
        var pAim = head
        while (p != null) {
            p = p.next
            pAim = pAim?.next
        }
        // 把下一个，即lastX，剔除掉
        pAim?.next = pAim?.next?.next
        return head
    }

    /**
     * 继续精简代码，关于剔除第一个这部分，利用虚拟头结点，来优化
     */
    fun solution3(head: ListNode?, n: Int): ListNode? {
        fun findLastX(head: ListNode?, n: Int): ListNode {
            var p = head
            for (i in 1..n) {
                p = p?.next
            }
            var pAim = head
            while (p != null) {
                p = p.next
                pAim = pAim!!.next
            }
            return pAim!!
        }
        // 虚拟头节点
        val dummy = ListNode(-1)
        dummy.next = head
        // 找到倒数n+1个
        val node = findLastX(dummy, n + 1)
        // 剔除lastX
        node.next = node.next?.next
        // 返回
        return dummy.next
    }
}

fun main() {
    RemoveNthFromEnd().run {
        test(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        test2(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        test3(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        solution(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        solution2(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        solution3(convertListNode2(1, 2, 3, 4, 5), 2).also { println(it) }
        solution(convertListNode2(1), 1).also { println(it) }
        solution2(convertListNode2(1), 1).also { println(it) }
        solution3(convertListNode2(1), 1).also { println(it) }
        solution(convertListNode2(1, 2), 1).also { println(it) }
        solution2(convertListNode2(1, 2), 1).also { println(it) }
        solution3(convertListNode2(1, 2), 1).also { println(it) }
    }
}