import java.nio.file.Path

class Day18 {

    fun loadDigPlan(path: Path): List<DigInstruction> {
        val lines = Resources.resourceAsListOfString(path.toFile().name)
        return lines.map {
            DigInstruction(
                direction = it[0],
                meters = it.substringAfter(" ").substringBefore(" ").toInt(),
                colorCode = it.substringAfterLast(" ")
            )
        }
    }
}

data class DigInstruction(
    val direction: Char,
    val meters: Int,
    val colorCode: String
)

class Lagoon {
    val digger: Digger = Digger(Point2D(0, 0))
}

class Digger(
    var position: Point2D,
    val facing: Point2D = Point2D.EAST
)

