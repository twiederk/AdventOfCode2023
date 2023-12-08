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

    fun countStepsSimultaneously(instructions: String, nodes: Map<String, Node>): Int {
        val startingNodes = startingNodes(nodes.values.toList()).toTypedArray()
        var steps = 0
        while (true) {
            val instruction = instructions.nth(steps)
            steps++
            for (index in startingNodes.indices) {
                startingNodes[index] =
                    nodes.getOrElse(startingNodes[index].next(instruction)) { throw IllegalArgumentException("Not found from ${startingNodes[index]} with $instruction") }
            }
            if (isEnd(startingNodes))
                return steps
        }
    }

    fun isEnd(nodes: Array<Node>): Boolean {
        return nodes.count { it.isEndNode() } == nodes.size
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
        return Loop(
            loopStart = loopStart,
            size = visited.size - loopStart
        )
    }
}

data class Loop(
    val loopStart: Int,
    val size: Int
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