import java.nio.file.Path
import kotlin.io.path.name

class Day01 {

    fun loadData(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.name)
    }
}

fun main() {

}

private fun part1() {
}

private fun part2() {
}
