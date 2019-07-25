package tencentselectpractice.easy

import model.ListNode
import model.convertListNode

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 示例 1：
 * 输入：
 *      intersectVal = 8,
 *      listA = [4,1,8,4,5], listB = [5,0,1,8,4,5],
 *      skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 *
 * 输入解释：
 *      相交节点的值为 8 。
 *      从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 *      在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 *
 * 示例 2：
 * 输入：
 *      intersectVal = 2,
 *      listA = [0,9,1,2,4], listB = [3,2,4],
 *      skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 *
 * 输入解释：
 *      相交节点的值为 2 。
 *      从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 *      在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 * 示例 3：
 * 输入：
 *      intersectVal = 0,
 *      listA = [2,6,4], listB = [1,5],
 *      skipA = 3, skipB = 2
 * 输出：null
 *
 * 输入解释：
 *      从各自的表头开始算起，
 *      链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 *      由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 * 注意：
 *      如果两个链表没有交点，返回 null.
 *      在返回结果后，两个链表仍须保持原有的结构。
 *      可假定整个链表结构中没有循环。
 *      程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
class GetIntersectionNode {

    /**
     * 方法 1: 两次 for 循环
     * 方法 2: hash 存储 headA, 遍历 headB 当某一元素, 存在于 hash 中, 即为相交节点
     * 方法 3: 双指针,类似于[HasCycle]找环,两个指针指向同一地址,即为相交节点
     *
     * 方法 4: 不巧妙,但是很实用.
     *      相交节点后的元素必然一致,个数相同,
     *      所以,只要找到同一下标的起始点,一次往后遍历,相同即为相交节点
     *
     *      [4, 8, 4, 5]        size = 4
     *      [5, 0, 1, 8, 4, 5]  size = 6 提现走(6-4=2)个
     *      变成了, 以下两个单链表判断相交
     *      [4, 8, 4, 5]
     *      [1, 8, 4, 5]
     *      同步往后遍历即可,相同即为相交节点
     *
     */

    fun solution(headA: ListNode?, headB: ListNode?): ListNode? {
        // 参数判断
        if (headA == null || headB == null) return null
        // 双指针(快慢指针)
        var itemA = headA
        var itemB = headB
        // 循环
        while (itemA != itemB) {
            itemA = if (itemA == null) headB else itemA.next
            itemB = if (itemB == null) headA else itemB.next
        }
        return itemA
    }
}

fun main() {
    testGetIntersectionNode()
}

fun testGetIntersectionNode() {
    val headA = convertListNode(intArrayOf(4, 1, 8, 4, 5))
    val headB = convertListNode(intArrayOf(5, 0, 1, 8, 4, 5))
    val intersectionNode = GetIntersectionNode().solution(headA, headB)
    println(intersectionNode?.`val`)
}