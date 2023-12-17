import java.nio.file.Path

class Day16 {

    fun loadContraption(path: Path): Contraption {
        return Contraption(Resources.resourceAsListOfString(path.toFile().name))
    }

}

data class Beam(
    val point2D: Point2D,
    val heading: Heading
) {

    enum class Heading {
        RIGHT, LEFT, UP, DOWN
    }

}

data class Contraption(
    val data: List<String>
)