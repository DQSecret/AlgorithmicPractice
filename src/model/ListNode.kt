package model

import java.util.Objects

class ListNode(var `val`: Int) : Comparable<ListNode> {

    var next: ListNode? = null

    override fun toString(): String {
        return "$`val`" + if (next == null) {
            " -> null"
        } else {
            " -> " + next.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListNode

        if (`val` != other.`val`) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hashCode(`val`)
    }

    override fun compareTo(other: ListNode): Int {
        return this.`val` - other.`val`
    }
}

fun convertListNode2(vararg nums: Int): ListNode? {
    val node = ListNode(-1)
    var temp: ListNode? = node
    nums.forEach {
        temp?.next = ListNode(it)
        temp = temp?.next
    }
    return node.next
}

fun convertListNode(nums: IntArray): ListNode {
    val node = ListNode(-1)
    convertListNode(node, nums, 0)
    return node.next!!
}

fun convertListNode(nums: MutableList<Int>): ListNode {
    val node = ListNode(-1)
    convertListNode(node, nums.toIntArray(), 0)
    return node.next!!
}

private fun convertListNode(node: ListNode, nums: IntArray, i: Int) {
    if (i >= nums.size) {
        return
    } else {
        val temp = ListNode(nums[i])
        node.next = temp
        convertListNode(temp, nums, i + 1)
    }
}

fun main() {
    val l = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(3)
        }
    }
    println(l)

    val arr = intArrayOf(2, 4, 3)
    val node = convertListNode(arr)
    println(node)
}