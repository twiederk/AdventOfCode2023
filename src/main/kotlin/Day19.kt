import java.nio.file.Path

class Day19 {

    fun loadWorkflows(path: Path): List<String> {
        val lines = Resources.resourceAsListOfString(path.toFile().name)
        return lines.subList(0, lines.indexOf(""))
    }

    fun loadParts(path: Path): List<String> {
        val lines = Resources.resourceAsListOfString(path.toFile().name)
        return lines.subList(lines.indexOf("") + 1, lines.size)
    }

}