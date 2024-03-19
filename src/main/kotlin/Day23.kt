import java.nio.file.Path

class Day23 {
    fun loadMapOfHikingTrails(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }
}