package adventofcode.y2015.d9

import adventofcode.base.Day
import adventofcode.util.Runner
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

    override fun partOne(): Any {
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