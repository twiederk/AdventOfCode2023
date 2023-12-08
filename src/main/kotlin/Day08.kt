import java.math.BigInteger
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
            node = nodes.getOrElse(node.next(instruction)) { throw IllegalArgumentException("Node not found") }
            if (node.name == "ZZZ") {
                return steps
            }
        }
    }

    fun startingNodes(nodes: List<Node>): List<Node> {
        return nodes.filter { it.isStartingNode() }
    }

    fun countStepsSimultaneously(instructions: String, nodes: Map<String, Node>): BigInteger {
        val startingNodes = startingNodes(nodes.values.toList()).toTypedArray()
        val loops = startingNodes.map { it.loop(instructions, nodes) }
        val lcm = loops.map { BigInteger.valueOf(it.zIndices[0].toLong()) }.reduce { a, b -> lcm(a, b) }
        return lcm
    }

    fun isEnd(nodes: Array<Node>): Boolean {
        return nodes.count { it.isEndNode() } == nodes.size
    }

    fun lcm(number1: BigInteger, number2: BigInteger): BigInteger {
        val gcd: BigInteger = number1.gcd(number2)
        val absProduct = number1.multiply(number2).abs()
        return absProduct.divide(gcd)
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

    fun isStartingNode(): Boolean {
        return name[2] == 'A'
    }

    fun isEndNode(): Boolean {
        return name[2] == 'Z'
    }

    fun loop(instructions: String, nodes: Map<String, Node>): Loop {
        val visited = mutableListOf<String>()
        var currentNode = this
        var index = 0
        var visitedNodeKey = "${currentNode.name}-$index"
        while (!visited.contains(visitedNodeKey)) {
            visited.add(visitedNodeKey)

            val instruction = instructions[index]
            currentNode =
                nodes.getOrElse(currentNode.next(instruction)) { throw IllegalArgumentException("Node not found") }
            visitedNodeKey = "${currentNode.name}-$index"

            index = (index + 1) % instructions.length
        }
        val loopStart = visited.indexOf(visitedNodeKey)
        val zIndices = mutableListOf<Int>()
        for (zIndex in visited.indices) {
            if (visited[zIndex][2] == 'Z') {
                zIndices.add(zIndex)
            }
        }
        return Loop(
            loopStart = loopStart,
            size = visited.size - loopStart,
            zIndices = zIndices

        )
    }
}

data class Loop(
    val loopStart: Int,
    val size: Int,
    val zIndices: List<Int>
)

fun main() {
    val day08 = Day08()
    val path = Paths.get("src", "main", "resources", "Day08_InputData.txt")
    val instructions = day08.loadInstructions(path)
    val nodes = day08.loadNodes(path)

    val part1 = day08.countSteps(instructions, nodes)
    println("Day08 part1: $part1")

    val part2 = day08.countStepsSimultaneously(instructions, nodes)
    println("Day08 part2: $part2")

}