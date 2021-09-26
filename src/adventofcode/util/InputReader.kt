package adventofcode.util

import java.io.File

object InputReader {

    fun getInputAsString(year: Int, day: Int): String {
        return fromResources(year, day).readText()
    }

    fun getInputAsList(year: Int, day: Int): List<String> {
        return fromResources(year, day).readLines()
    }

    private fun fromResources(year: Int, day: Int): File {
        val filename = "../res/input_${year}_${day}.txt"
        return javaClass.getResource(filename)
            ?.let { File(it.toURI()) }
            ?: throw RuntimeException("目标文件不存在, 请检查: $filename")
    }
}