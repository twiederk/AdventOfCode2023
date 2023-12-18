import java.nio.file.Path

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

}