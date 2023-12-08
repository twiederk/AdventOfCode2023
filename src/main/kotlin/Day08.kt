import java.nio.file.Path
import java.nio.file.Paths

class Day08 {

    fun loadInstructions(path: Path): String {
        return Resources.resourceAsListOfString(path.toFile().name)[0]
    }

    fun loadNodes(path: Path): Map<String, Node> {
        val nodesList = Resources.resourceAsListOfString(path.toFile().name).drop(2)
        return nodesList.associate {
            it.substring(0, 3) to
                    Node(
                        name = it.substring(0, 3),
                        left = it.substring(7, 10),
                        right = it.substring(12, 15)
                    )
        }
    }

    fun countSteps(instructions: String, nodes: Map<String, Node>): Int {
        var node: Node = nodes.getOrElse("AAA") { throw IllegalArgumentException("Node not found: AAA") }
        var steps = 0
        while (true) {
            val instruction = instructions.nth(steps)
            steps++
            node = nodes.getOrElse(node.next(instruction)) { throw IllegalArgumentException("Node not found: AAA") }
            if (node.name == "ZZZ") {
                return steps
            }
        }
        return -1
    }
}

data class Node(
    val name: String,
    val left: String,
    val right: String
) {
    fun next(instruction: Char): String {
        return when (instruction) {
            'L' -> left
            else -> right
        }
    }
}

fun main() {
    val day08 = Day08()
    val path = Paths.get("src", "main", "resources", "Day08_InputData.txt")
    val instructions = day08.loadInstructions(path)
    val nodes = day08.loadNodes(path)

    val part1 = day08.countSteps(instructions, nodes)
    println("Day08 part1: $part1")
}