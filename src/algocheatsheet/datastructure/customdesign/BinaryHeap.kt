package algocheatsheet.datastructure.customdesign

/**
 * 二叉堆 - Binary Heap
 *
 * @author DQ For Olivia
 * @since 2022/5/10 15:40
 * @see <a href="https://time.geekbang.org/column/article/69913">堆的定义</a>
 * @see <a href="https://labuladong.github.io/algo/2/21/63/">讲解</a>
 */
interface BinaryHeap {

    /**
     * Q: 首先定义什么是堆(Heap)?
     *
     * A: 堆是一种特殊的树,须符合两个条件:
     *  1. 堆是完全二叉树 -> 要求最后一层的子节点都靠左,其余层必须满
     *  2. 堆中每一个节点的值都必须 >= 或者 <= 其左右子节点的值.
     *
     * PS: 最常用的存储方式就是数组
     */
    private fun definition() {}

    // 父节点的索引
    fun parent(root: Int): Int {
        return root / 2
    }

    // 左孩子的索引
    fun left(root: Int): Int {
        return root * 2
    }

    // 右孩子的索引
    fun right(root: Int): Int {
        return root * 2 + 1
    }
}

/**
 * 优先级队列 - 基于二叉堆
 */
abstract class PriorityQueue<Key : Comparable<Key>>(cap: Int) : BinaryHeap {

    // 存储元素的数组
    @Suppress("UNCHECKED_CAST")
    protected var pq: Array<Key?> = arrayOfNulls<Comparable<Key>>(cap + 1) as Array<Key?>

    // 当前 Priority Queue 中的元素个数
    protected var size = 0

    fun isEmpty(): Boolean {
        return size <= 0
    }

    /**
     * 返回顶部的第一支元素
     */
    fun getTop(): Key {
        return pq[1] ?: throw RuntimeException("堆下标越界")
    }

    /**
     * 插入一个元素
     */
    fun insert(key: Key) {
        size += 1
        // 先把新元素加到最后
        pq[size] = key
        // 然后让它上浮到正确的位置
        swim(size)
    }

    /**
     * 精选: 移除最大(小)值,并返回
     *
     * 这里跟巧妙,特别备注一下:
     *  1. 关于移除
     *      - 第一反应很麻烦,相当于把一个堆,顶去掉,拆成俩堆,然后再重新排序合成.
     *  2. 但实际呢
     *      1. 只需要将最后一个元素,和顶交换,
     *      2. 然后移除最后一个元素(原顶),
     *      3. 将现顶(原最后一个元素)下沉到对应位置即可.
     */
    fun cullTop(): Key {
        // 获取最大值
        val top: Key = getTop()
        // 交换
        swap(1, size)
        // 移除
        pq[size] = null
        size -= 1
        // 下沉
        sink(1)
        return top
    }

    /**
     * 交换数组的两个元素
     */
    private fun swap(i: Int, j: Int) {
        val temp = pq[i]
        pq[i] = pq[j]
        pq[j] = temp
    }

    /**
     * 上浮第 x 个元素，以维护最(大/小)堆性质
     */
    private fun swim(x: Int) {
        var node = x
        // 只有当 node>1 即非堆顶时, 并且 不符合堆关系(即!compare()==true) 时, 才会触发上浮
        while (node > 1 && !compare(parent(node), node)) {
            swap(parent(node), node)
            node = parent(node)
        }
    }

    /**
     * 下沉第 x 个元素，以维护最(大/小)堆性质
     */
    private fun sink(x: Int) {
        var node = x
        // left(node) 左子节点小于等于 size, 证明其存在, 才能证明有下沉的空间(可能性)
        while (left(node) <= size) {
            // 获取子节点中的较大(小)值, 取决于 compare() 所定义的堆关系
            val order = if (right(node) <= size && !compare(left(node), right(node))) {
                right(node)
            } else {
                left(node)
            }
            // node 和 order 符合堆关系, 则结束; 否则下沉.
            if (compare(node, order)) {
                break
            } else {
                swap(node, order)
                node = order
            }
        }
    }

    /**
     * 定义堆中,节点之间的关系,是大于还是小于
     */
    abstract fun compare(i: Int, j: Int): Boolean

    override fun toString(): String {
        return "${this::class.simpleName}(pq=${pq.contentToString()}, size=$size)"
    }
}

/**
 * 大顶堆
 */
class MaxPQ<Key : Comparable<Key>>(cap: Int) : PriorityQueue<Key>(cap) {

    override fun compare(i: Int, j: Int): Boolean {
        val one = pq[i] ?: throw RuntimeException("堆下标越界")
        val two = pq[j] ?: throw RuntimeException("堆下标越界")
        return one > two
    }
}

/**
 * 小顶堆
 */
class MinPQ<Key : Comparable<Key>>(cap: Int) : PriorityQueue<Key>(cap) {

    override fun compare(i: Int, j: Int): Boolean {
        val one = pq[i] ?: throw RuntimeException("堆下标越界")
        val two = pq[j] ?: throw RuntimeException("堆下标越界")
        return one < two
    }
}

fun main() {
    // 初始化 10 8 6 4 2
    val maxPQOfInt = MaxPQ<Int>(5).also {
        for (i in 10 downTo 2 step 2) {
            it.insert(i)
        }
        println(it)
    }
    // 获取最大值
    maxPQOfInt.getTop().also {
        println("max = $it")
    }
    // 移除 10
    maxPQOfInt.run {
        cullTop().also {
            println("del Max($it) => $this")
        }
    }
    // 添加 5
    maxPQOfInt.run {
        val value = 5
        insert(value)
        println("insert $value => $this ")
    }
}
