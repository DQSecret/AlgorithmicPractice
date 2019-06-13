package model

class ListNode(var `val`: Int) {

    var next: ListNode? = null

    override fun toString(): String {
        return "$`val`" + if (next == null) {
            " -> null"
        } else {
            " -> " + next.toString()
        }
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