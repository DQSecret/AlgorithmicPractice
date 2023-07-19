package labuladong.chapter_1.array_linkedlist_1_1._03_twopointerforlinklist

import model.ListNode
import model.convertListNode2

/**
 * 给你两个单链表的头节点 headA 和 headB ，
 * 请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 请注意相交节点的值不为 1，因为在链表 A 和链表 B 之中值为 1 的节点 (A 中第二个节点和 B 中第三个节点) 是不同的节点。
 * 换句话说，它们在内存中指向两个不同的位置，
 * 而链表 A 和链表 B 中值为 8 的节点 (A 中第三个节点，B 中第四个节点) 在内存中指向相同的位置。
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例3:
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 */
class GetIntersectionNode {

    /**
     * 示例1有点不理解
     *
     * 先凭直觉，去试试吧
     * 还是利用hashset去做，结论是，开辟了额外的空间
     */
    fun solution(headA: ListNode?, headB: ListNode?): ListNode? {
        val sets = hashSetOf<ListNode>()
        var pA = headA
        var pB = headB
        while (pA != null || pB != null) {
            pA?.let {
                if (!sets.add(it)) {
                    return it
                } else {
                    pA = it.next
                }
            }
            pB?.let {
                if (!sets.add(it)) {
                    return it
                } else {
                    pB = it.next
                }
            }
        }
        return null
    }

    /**
     * 如果是双指针的话，问题的难点，在于如何让两个指针，同时走到相交结点上
     *
     * 自己的思路，把2个链表前后，拼接起来，
     * 原：A、B
     * 现：A+B、B+A
     * 然后，双指针同步往后走，相同时，即为相交节点。
     * 题目中要求：【注意，函数返回结果后，链表必须保持其原始结构。】！！！
     * 所以，不能对原有链表直接进行更改，除非，最后还原回去，哈哈哈～
     * 但是，有点难做到... 囧
     *
     * 所以，突然想到，让指针从另一个链表头，开始遍历，就好～
     *
     * 本地自己跑没问题，但是，力扣上提交，运行失败。。。
     */
    fun solution2(headA: ListNode?, headB: ListNode?): ListNode? {
        var pA = headA
        var pAIsOver = false
        var pB = headB
        var pBIsOver = false
        while (true) {
            // 终止条件
            if (pA === pB) return pA
            // pA到头了
            if (pA == null) {
                if (!pAIsOver) { // 换pB
                    pAIsOver = true
                    pA = headB
                } else { // 又到头了，那就结束了
                    return null
                }
            } else {
                pA = pA.next
            }
            // pB到头了
            if (pB == null) {
                if (!pBIsOver) { // 换pA
                    pBIsOver = true
                    pB = headA
                } else { // 又到头了，那就结束了
                    return null
                }
            } else {
                pB = pB.next
            }
        }
    }

    /**
     * 在[solution3]的基础上优化，思维逻辑都一样，只是更加优雅，少了一些判断.
     * 可恶啊，竟然没有考虑到：null==null是true！！！
     */
    fun solution3(headA: ListNode?, headB: ListNode?): ListNode? {
        var pA = headA
        var pB = headB
        while (pA != pB) {
            pA = if (pA != null) pA.next else headB
            pB = if (pB != null) pB.next else headA
        }
        return pA
    }
}

fun main() {

    GetIntersectionNode().run {
        fun test(headA: ListNode?, headB: ListNode?, common: ListNode?) {
            var pa = headA
            while (pa?.next != null) pa = pa.next
            pa?.next = common
            var pb = headB
            while (pb?.next != null) pb = pb.next
            pb?.next = common
            solution(headA, headB).also { println(it) }
            solution2(headA, headB).also { println(it) }
            solution3(headA, headB).also { println(it) }
        }
        test(convertListNode2(4, 1), convertListNode2(5, 6, 1), convertListNode2(8, 4, 5))
        test(convertListNode2(1, 9, 1), convertListNode2(3), convertListNode2(2, 4))
        test(convertListNode2(2, 6, 4), convertListNode2(1, 5), null)
    }
}