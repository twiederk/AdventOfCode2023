import java.nio.file.Path

class Day16 {

    fun loadContraption(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

}