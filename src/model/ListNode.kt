package model

import java.util.*

class ListNode(var `val`: Int) {

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
}

fun main() {
    val l = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(3)
        }
    }
    println(l)
}