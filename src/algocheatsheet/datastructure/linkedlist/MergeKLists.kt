package algocheatsheet.datastructure.linkedlist

import model.ListNode
import model.convertListNode2

/**
 * 合并K个升序链表 - [MergeTwoLists]的升级版
 *
 * @author DQ For Olivia
 * @since 2022/5/9 17:09
 * @see <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">力扣题目</a>
 * @see <a href="https://labuladong.github.io/algo/2/17/16/">讲解</a>
 */
class MergeKLists {

    /**
     * 示例:
     *
     * 输入: lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出: [1,1,2,3,4,4,5,6]
     *
     * 解释: 链表数组如下:
     * [
     *  1->4->5,
     *  1->3->4,
     *  2->6
     * ]
     *
     * 将它们合并到一个有序链表中得到:
     * 1->1->2->3->4->4->5->6
     *
     * 输入: lists = []
     * 输出: []
     *
     * 输入: lists = [[]]
     * 输出: []
     *
     * 提示:
     *   1. k == lists.length
     *   2. 0 <= k <= 10^4
     *   3. 0 <= lists.[i].length <= 500
     *   4. -10^4 <= lists.[i].[j] <= 10^4
     *   5. lists.[i] 按 升序 排列
     *   6. lists.[i].length 的总和不超过 10^4
     */
    @Suppress("KDocUnresolvedReference")
    fun solution(lists: Array<ListNode?>): ListNode? {
        // 创建一个虚拟头结点,并用一个指针指向它
        val dummy = ListNode(-1)
        var point: ListNode? = dummy
        // 用K个指针,分别记录每一个链表的节点
        val ps: Array<ListNode?> = lists
        while (true) {
            // 找到最小值
            var index = -1
            var min = ListNode(Int.MAX_VALUE)
            for ((i, node) in ps.withIndex()) {
                node ?: continue
                if (node.`val` < min.`val`) {
                    index = i
                    min = node
                }
            }
            // 如果没找到,即全都是空,也就是结束的时机了
            if (index == -1) break
            // 移动指针到下一节点
            point?.next = min
            ps[index] = min.next
            point = point?.next
        }
        return dummy.next
    }
}

fun main() {
    val l1 = convertListNode2(1, 4, 5)
    val l2 = convertListNode2(1, 3, 4)
    val l3 = convertListNode2(2, 6)
    MergeKLists().solution(arrayOf(l1, l2, l3)).also {
        println(it)
    }
}