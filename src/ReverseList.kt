import model.ListNode
import model.convertListNode

/**
 * 反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL (可以理解为               1->2->3->4->5->NULL )
 * 输出: 5->4->3->2->1->NULL (可以理解为   5->4->3->2->1->NUll             )
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
class ReverseList {

    /**
     * 方法 1: 迭代 - 改变指向即可
     * 解释:
     *      [prev] - (已经反转过的)前一个值(可以理解为 null)
     *      [curr] - 循环体的指针,类似于数组中的 index
     *      step1:  curr(1) 先保存 next 至 temp ===> temp(2345N)
     *      step2:  更新 curr(1) 的 next 为 前一个值(默认为 null) ===> curr(1N)
     *      step3:  此时的 curr(1) 已经是反转过的了 存储至 prev ===> prev(1N)
     *      step4:  curr(1) 更新为 next ===> curr(2):(2345N)
     *
     *      此时, prev = (1N) curr = (2345N)
     *
     *      step5: curr(1) 保存 temp (345N)
     *      step6: curr(2) 更新 next = (1N) 结果为 (21N)
     *      ......
     *      curr 最后一次,会指向到 N
     *      不符合 while 判断条件 -> 结束循环
     */
    fun solution1(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr: ListNode? = head
        while (curr != null) {
            val temp = curr.next
            curr.next = prev
            prev = curr
            curr = temp
        }
        return prev
    }

    /**
     * 方法 2: 递归, 这个有点骚气
     * 我们希望 n{k+1} 的下一节点指向 n{k}
    ​ * 所以, n{k}​.next.next = n{k}
     *
     * 要小心的是 n{1} ​的下一个必须指向 Null
     * 如果你忽略了这一点，你的链表中可能会产生循环。
     * 如果使用大小为 2 的链表测试代码，则可能会捕获此错误
     *
     * 解释: 1->2->null
     * ∵ 1.next = 2
     * ∴ 1.next.next = 2.next
     * 若要使 2.next = 1
     * 可以用 1.next.next = 1 来替换
     */
    fun solution2(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val reverse = solution2(head.next)
        println("前: ${head.`val`} -- ${head.next?.`val`} || ${reverse?.`val`} -- ${reverse?.next?.`val`} -- ${reverse?.next?.next?.`val`}")
        head.next?.next = head
        println("后: ${head.`val`} -- ${head.next?.`val`}  || ${reverse?.`val`} -- ${reverse?.next?.`val`} -- ${reverse?.next?.next?.`val`}")
        head.next = null
        return reverse
    }

    /**
     * 方法 3: 遍历, 头插
     */
    fun solution3(head: ListNode?): ListNode? {
        var reverse: ListNode? = null
        var temp = head
        while (temp != null) {
            val t = temp.next
            temp.next = reverse
            reverse = temp
            temp = t
        }
        return reverse
    }
}

fun main() {
    testReverseList()
}

fun testReverseList() {
    val arr = intArrayOf(1, 2, 3)
    println(ReverseList().solution1(convertListNode(arr)))
    println(ReverseList().solution2(convertListNode(arr)))
    println(ReverseList().solution3(convertListNode(arr)))
}