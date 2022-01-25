package forcesearch.bfs

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.reflect.jvm.internal.impl.utils.DFS

/**
 * 要说框架的话，我们先举例一下 BFS 出现的常见场景好吧，
 * 问题的本质就是让你在一幅「图」中找到从起点 start 到终点 target 的最近距离，
 * 这个例子听起来很枯燥，但是 BFS 算法问题其实都是在干这个事儿，把枯燥的本质搞清楚了，再去欣赏各种问题的包装才能胸有成竹嘛。
 *
 * 这个广义的描述可以有各种变体，
 * 比如走迷宫，有的格子是围墙不能走，从起点到终点的最短距离是多少？如果这个迷宫带「传送门」可以瞬间传送呢？
 * 再比如说两个单词，要求你通过某些替换，把其中一个变成另一个，每次只能替换一个字符，最少要替换几次？
 * 再比如说连连看游戏，两个方块消除的条件不仅仅是图案相同，还得保证两个方块之间的最短连线不能多于两个拐点。你玩连连看，点击两个坐标，游戏是如何判断它俩的最短连线有几个拐点的？
 * 再比如……
 * 净整些花里胡哨的，这些问题都没啥奇技淫巧，本质上就是一幅「图」，让你从一个起点，走到终点，问最短路径。这就是 BFS 的本质，框架搞清楚了直接默写就好。
 *
 * @author DQ For Olivia
 * @since 2022/1/19 5:03 下午
 * @see <a href="https://labuladong.gitee.io/algo/4/29/113/">讲解</a>
 */
class BFS {

    /**
     * 解题套路解析
     *
     * PS: 学习的时候偶然发现一个类:[DFS]-深度优先搜索?-里面有很相似的思想
     */
    private inline fun <reified T> bfs(start: T, end: T): Int {
        // 1. 核心数据结构
        /** 队列:可使用Java-[Queue] or Kotlin-[ArrayDeque]-双端队列 */
        val queue: ArrayDeque<T> = ArrayDeque()

        // 2. 前置工作
        // 已访问的元素:防止重复访问
        val visited: MutableSet<T> = mutableSetOf()
        // 将第一个元素加入其中
        queue.addLast(start)
        visited.add(start)
        // 步数
        var step = 0

        // 3. 核心逻辑
        // 整个队列左边取-检测,右边添加-延展
        all@ while (queue.isNotEmpty()) {
            // 这里单独取一次,是因为queue.size随时在变
            val size = queue.size
            it@ for (i in 0 until size) {
                val curr: T = queue.removeFirst()
                if (end == curr) {
                    // 到终点了
                    break@all
                }
                // 邻居元素:相连接的,如链表中的下一个元素
                val neighbors: MutableSet<T> = mutableSetOf()
                // 获取邻居,并将邻居全部添加到队列中,规则不同所以没有定式
                // neighbors = curr.getNeighbors()
                for (neighbor in neighbors) {
                    if (neighbor !in visited) {
                        queue.addLast(neighbor)
                        visited.add(neighbor)
                    }
                }
            }
            // 一层结束,步数+1
            step += 1
        }

        // 4. 总结
        /**
         * 队列 q 就不说了，BFS 的核心数据结构；
         * cur.adj() 泛指 cur 相邻的节点，比如说二维数组中，cur 上下左右四面的位置就是相邻节点；
         * visited 的主要作用是防止走回头路，大部分时候都是必须的，
         * 但是像一般的二叉树结构，没有子节点到父节点的指针，不会走回头路就不需要 visited。
         */

        // 返回结果
        return step
    }
}
