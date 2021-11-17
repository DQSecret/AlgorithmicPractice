package adventofcode.y2015.d9

import adventofcode.base.Day
import adventofcode.util.Runner
import java.util.*
import kotlin.time.ExperimentalTime

/**
 * 求最短路径的算法, 动态规划?贪心?
 *
 * @author DQ For Olivia
 * @since 2021/11/15 3:31 下午
 * @see <a href="https://adventofcode.com/2015/day/9">文章</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/104/">回溯算法讲解</a>
 */
class AllInASingleNight : Day(2015, 9) {

    data class Route(val start: String, val end: String, val distances: Int) {
        companion object {
            fun convert(original: String): Route {
                return original.split(" ").let {
                    Route(it[0], it[2], it[4].toInt())
                }
            }
        }
    }

    // 所有的已知地图数据: [用来衡量最短距离的]
    private val routes: Set<Route>

    // 所有的城市: 即[选择]
    private val cities: Set<String>

    /**
     * 前期准备工作: 解析数据
     * 将一条条信息,转化成,可用的数据类型
     */
    init {
        inputList
            .map { Route.convert(it) }.also { routes = it.toSet() }
            .flatMap { listOf(it.start, it.end) }.also { cities = it.toSet() }
    }

    /**
     * 计算最短路径
     * 猜想1: 暴力
     * 猜想2: 每一步都是最短的话, 总和是不是就是最短的?
     *
     * 看到回溯算法讲解后,认为这是一个全排列的问题
     */
    private fun getShortestDistance(): Int {
        return permute().minOf { getSum(it) }
    }

    /**
     * 计算最长路径,只需要更改一个条件即可
     */
    private fun getLongestDistance(): Int {
        return permute().maxOf { getSum(it) }
    }

    /**
     * 求总和
     */
    private fun getSum(item: List<String>): Int {
        return item.windowed(2) // 按顺序,分割size=2&step=1,分成多块
            .mapNotNull { // 转为Route, 一定不为空, 为了不使用!!, 过滤空项
                routes.find { route ->
                    (route.start == it.first() && route.end == it.last())
                            || (route.start == it.last() && route.end == it.first())
                }
            }
            .sumOf { it.distances } // 累加距离
//                    .also {
//                        // 纯粹为了看日志
//                        println(item.joinToString("->", postfix = ": $it"))
//                    }
    }

    /**
     * 获得全排列
     */
    private fun permute(): MutableList<List<String>> {
        val all: MutableList<List<String>> = mutableListOf()
        backtrack(all, mutableListOf(), this.cities)
        return all
    }

    /**
     * 暴力搜索算法(DFS)
     * 要点:
     * 1. 已选路径
     * 2. 可选择项
     * 3. 终止条件
     * 目的:
     * 是为了生成全排列组合,但是Kotlin没有直接使用的方法,所以只能用DFS自己来搞了
     */
    private fun backtrack(
        all: MutableList<List<String>>,
        routes: MutableList<String>,
        cities: Set<String>
    ) {
        // 终止条件
        if (routes.size == cities.size) {
            all.add(routes.toList()) // 这里要给一个新的list,因为routes会被不停的增删元素
            return
        }
        for (city in cities) {
            // 如果已存在, 下一个
            if (routes.contains(city)) {
                continue
            }
            // 添加至 路径
            routes.add(city)
            // 进入下一个选择
            backtrack(all, routes, cities)
            // 移除路径
            routes.remove(city)
        }
    }

    /**
     * 最开始纯暴力的想法(两层for循环)行不通, 最后发现一定需要实现全排列, 这下就没有捷径了
     * PS:没想到最终的代码写起来很少,很优雅,嘿嘿嘿~
     * 其实关于数据处理的部分,可以优化到[getSum]中
     */
    private fun native(): Int {
        val place = mutableSetOf<String>()
        val distances = mutableMapOf<String, Int>()
        inputList.flatMap { it.split(" ") }
            .chunked(5) {
                // 正向反向都存储,方便后续查找
                place.add(it[0])
                place.add(it[2])
                distances[it[0] + it[2]] = it[4].toInt()
                distances[it[2] + it[0]] = it[4].toInt()
            }
        // 全排列组合后,找最小值
        return place.toList().permutations()
            .minOf { path ->
                path.windowed(2) { it.first() + it.last() }
                    .fold(0) { acc, s -> acc + distances.getOrDefault(s, 0) }
            }
    }

    /**
     * 由于Kotlin官方没有生成全排列组合的方法, 故在网上找的方法, 其实还是递归(暴力). o(╯□╰)o
     */
    private fun <V> List<V>.permutations(): List<List<V>> {
        val retVal: MutableList<List<V>> = mutableListOf()
        fun generate(k: Int, list: List<V>) {
            // If only 1 element, just output the array
            if (k == 1) {
                retVal.add(list.toList())
            } else {
                for (i in 0 until k) {
                    generate(k - 1, list)
                    if (k % 2 == 0) {
                        Collections.swap(list, i, k - 1)
                    } else {
                        Collections.swap(list, 0, k - 1)
                    }
                }
            }
        }
        generate(this.count(), this.toList())
        return retVal
    }

    override fun partOne(): Any {
        "纯暴力算法: {${native()}}".also { println(it) }
        return "The shortest distance is: {${getShortestDistance()}}"
    }

    override fun partTwo(): Any {
        return "The longest distance is: {${getLongestDistance()}}"
    }
}

@ExperimentalTime
fun main() {
    Runner.printDay(AllInASingleNight())
}