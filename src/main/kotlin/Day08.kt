import java.nio.file.Path

class Day08 {

    fun loadInstructions(path: Path): String {
        return Resources.resourceAsListOfString(path.toFile().name)[0]
    }

    fun loadNodes(path: Path): List<Node> {
        val nodesList = Resources.resourceAsListOfString(path.toFile().name).drop(2)
        return nodesList.map {
            Node(
                name = it.substring(0, 3),
                left = it.substring(7, 10),
                right = it.substring(12, 15)
            )
        }
    }
}

data class Node(
    val name: String,
    val left: String,
    val right: String
)