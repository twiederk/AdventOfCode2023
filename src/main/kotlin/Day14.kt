import java.nio.file.Path

class Day14 {

    fun loadPlatform(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

}