import java.nio.file.Path

typealias Pattern = List<String>

class Day13 {
    fun loadPatterns(path: Path): List<Pattern> {
        val rawData = Resources.resourceAsListOfString(path.toFile().name)

        val patterns = mutableListOf<Pattern>()
        var pattern = mutableListOf<String>()
        for (line in rawData) {
            if (line.isNotEmpty()) {
                pattern.add(line)
            } else {
                patterns.add(pattern)
                pattern = mutableListOf()
            }
        }
        patterns.add(pattern)
        return patterns
    }
}