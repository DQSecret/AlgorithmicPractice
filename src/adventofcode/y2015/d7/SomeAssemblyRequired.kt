package adventofcode.y2015.d7

import adventofcode.base.Day
import adventofcode.util.Runner
import kotlin.time.ExperimentalTime

/**
 * 看完题目,是一个链路回溯的问题,第一感觉是没有思路 o(╯□╰)o
 *
 * 想法1: 建立一个网状图,一条条回溯即可
 *   |-- 以" -> value" 为目标搜索, 同时记录 Map<Key,Value> 其中Key是结果, Value是参数和计算方式
 *         |-- 一遍循环之后, 能得出一个覆盖全部参数的 Map 图
 *   |-- 当题目要求计算a时, 先 Map.get(a) 得出需要的参数和计算方式
 *         |-- 再递归,去求参数的[计算方式, 所需参数]
 *         |-- 一步步往下找, 直到有值, 再一步步向上返, 最终得出 a 的值
 *   小结: 该想法,消耗的点,在于需提前先遍历一次,生成所有节点的Map图+递归查询,才能得出结果
 *
 * 想法2: 从已知参数开始计算, 一步步扩散, 求出所有节点的值, 最后直接找 a
 *   |-- 先建立一个 map<k,v> 其中 k 为所有节点, v 为所有已经求得的值
 *         |-- 一道题目,至少有一个输入值,然后才能往后推演
 *         |-- 以此初始值开始计算, 每得出一个值, 就往map里存一个, 同时删掉input, 减少下次计算
 *   |-- 最好情况, 很快得到结果, 最差情况, 最后一个才计算到 a, 即计算出了所有值
 *         |-- 缺点就是要循环 input 很多遍
 *   小结: 该想法, 相较于想法1, 一个正向推演(穷举-循环很多遍), 一个反向推演(需要递归), 都有冗余, 有没有什么思路可以兼得?
 *
 * 想法3: 到这里的时候,就感觉是一个动态规划的题目了 ε=(´ο｀*)))唉
 *
 * @author DQ For Olivia
 * @since 2021/9/26 12:11 下午
 * @see <a href="https://adventofcode.com/2015/day/7">文章</a>
 */
class SomeAssemblyRequired : Day(2015, 7) {

    // 所有的线路图
    private val wires = mutableMapOf<String, Element>()

    /**
     * 有效值: 直接保存
     * 无效值: 需要计算
     */
    private fun isValidValue(value: String, map: MutableMap<String, Element>): Element {
        return if (value.matches(Regex("\\d+"))) {
            Node(value.toInt())
        } else {
            LazyNode(value, map)
        }
    }

    /**
     * 生成线路图
     */
    private fun createWires() {
        inputList.map { wire ->
            wire.split(" ")
                .let {
                    wires[it.last()] = when (it.size) {
                        3 -> {
                            // 长度为 3 一定是赋值操作
                            Operation(input1 = isValidValue(it[0], wires))
                        }
                        4 -> {
                            // 长度为 4 一定是取反操作
                            Operation(op = it[0], input1 = isValidValue(it[1], wires))
                        }
                        5 -> {
                            // 长度为 5 一定是其他操作
                            Operation(
                                op = it[1],
                                input1 = isValidValue(it[0], wires),
                                input2 = isValidValue(it[2], wires)
                            )
                        }
                        else -> {
                            throw RuntimeException("构建线路图异常, 请确认是否合法: $wire")
                        }
                    }
                }
        }
    }

    override fun partOne(): Any {
        createWires()
        return wires["a"]?.getValue() ?: throw RuntimeException("没有找到符合的线路!")
    }

    override fun partTwo(): Any {
        createWires()
        wires["b"] = Operation(input1 = isValidValue("46065", wires))
        return wires["a"]?.getValue() ?: throw RuntimeException("没有找到符合的线路!")
    }
}

interface Element {
    fun getValue(): Int
}

/**
 * 纯数字
 */
class Node(private val value: Int) : Element {
    override fun getValue() = value
}

/**
 * 懒加载用
 */
class LazyNode(private val key: String, private val map: Map<String, Element>) : Element {

    // 这里加缓存, 是为了防止循环的线路图重复计算, 导致效率变低
    private var cached: Int = -1

    override fun getValue(): Int {
        if (cached == -1) {
            cached = map[key]?.getValue()
                ?: throw RuntimeException("线路图中没有匹配项, 请重新检查: $key")
        }
        return cached
    }
}

/**
 * 需要进行操作符计算的
 */
class Operation(
    private val op: String = "SET",
    private val input1: Element,
    private val input2: Element = input1,
) : Element {

    // 所有的操作符
    private val operations = mapOf(
        "AND" to { x: Int, y: Int -> x and y },
        "OR" to { x: Int, y: Int -> x or y },
        "LSHIFT" to { x: Int, y: Int -> x shl y },
        "RSHIFT" to { x: Int, y: Int -> x shr y },
        "NOT" to { x: Int, _: Int -> x.inv() and 0xFFFF }, // 这里 and 0xFFFF 是为了防止溢出变成负数
        "SET" to { x: Int, _: Int -> x },
    )

    override fun getValue(): Int {
        return operations[op]?.invoke(input1.getValue(), input2.getValue())
            ?: throw RuntimeException("异常操作符, 请确认操作符是否合法: $op")
    }
}

@ExperimentalTime
fun main() {
    Runner.printDay(SomeAssemblyRequired())
}
