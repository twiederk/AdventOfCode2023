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
    val digger: Digger = Digger()
}

class Digger(
    var position: Point2D = Point2D(0, 0),
    var facing: Point2D = Point2D.NORTH
) {
    fun dig(digInstruction: DigInstruction): List<Point2D> {
        val trench = mutableListOf<Point2D>()
        for (index in 1..digInstruction.meters) {
            position += facing
            trench.add(position)
        }
        return trench
    }

}

