import java.util.*
import kotlin.math.min

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
class MinStack1 {

    /*
    方法 1: 利用辅助栈的额外容量,来实现最小值的记录
     */

    private val vStack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(x: Int) {
        vStack.push(x)
        if (minStack.empty() || x <= minStack.peek()) {
            minStack.push(x)
        }
    }

    fun pop() {
        if (vStack.isNotEmpty() && minStack.isNotEmpty()) {
            val top = vStack.pop()
            if (top == minStack.peek()) {
                minStack.pop()
            }
        }
        throw RuntimeException("栈中元素为空，此操作非法")
    }

    fun top(): Int {
        if (vStack.isNotEmpty()) return vStack.peek()
        throw RuntimeException("栈中元素为空，此操作非法")
    }

    fun getMin(): Int {
        if (minStack.isNotEmpty()) return minStack.peek()
        throw RuntimeException("栈中元素为空，此操作非法")
    }
}

class MinStack2 {

    /*
    方法 2: 栈中存储的是 v=x-min, 是一种 x 和 min 的关系值 相当于 f(x), 这样方便推导
     */

    private val vStack = Stack<Long>()
    private var min: Long = 0

    fun push(x: Int) {
        // 第一个值
        if (vStack.isEmpty()) {
            vStack.push(0)
            min = x.toLong()
        }
        // 之后的每个值,都需要判断
        else {
            val v = x.toLong() - min
            vStack.push(v)
            if (v < 0) min = x.toLong()
        }
    }

    fun pop() {
        val v = vStack.pop()
        if (v < 0) { // x < las_min, 更新后 min = x
            val x = min
            min = x - v
        } else { // x > las_min, min 不变更
        }
    }

    fun top(): Int {
        val v = vStack.peek()
        return if (v < 0) { // x < las_min, 更新后 min = x
            Math.toIntExact(min)
        } else { // x > las_min, min 不变更
            Math.toIntExact(v + min)
        }
    }

    fun getMin(): Int {
        return Math.toIntExact(min)
    }
}

class MinStack3 {
    /*
    方法 3: 利用两个 LinkedList 来处理, 数据是同步的, minList 会存很多和重复最小值
    */

    private val vList = LinkedList<Int>()
    private val minList = LinkedList<Int>()

    fun push(x: Int) {
        vList.push(x)
        val min = if (minList.isEmpty()) {
            x
        } else {
            min(minList.first, x)
        }
        minList.push(min)
    }

    fun pop() {
        vList.pop()
        minList.pop()
    }

    fun top(): Int {
        return vList.first
    }

    fun getMin(): Int {
        return minList.first
    }
}

fun main() {
    test3()
}

fun test1() {
    val obj = MinStack1()
    obj.push(-2)
    obj.push(0)
    obj.push(-3)

    println("当前最小值: ${obj.getMin()}")
    obj.pop()
    println("当前栈顶值: ${obj.top()}")
    println("当前最小值: ${obj.getMin()}")
}

fun test2() {
    val obj = MinStack2()
    obj.push(1)
    obj.push(2)

    println("当前栈顶值: ${obj.top()}")
    println("当前最小值: ${obj.getMin()}")
    obj.pop()
    println("当前最小值: ${obj.getMin()}")
    println("当前栈顶值: ${obj.top()}")
}

fun test3() {
    val obj = MinStack3()
    obj.push(1)
    obj.push(3)
    obj.push(2)
    obj.push(4)
    obj.push(5)
    obj.push(2)

    println("当前栈顶值: ${obj.top()}")
    println("当前最小值: ${obj.getMin()}")
    obj.pop()
    println("当前最小值: ${obj.getMin()}")
    println("当前栈顶值: ${obj.top()}")
    obj.pop()
    println("当前最小值: ${obj.getMin()}")
    println("当前栈顶值: ${obj.top()}")
}