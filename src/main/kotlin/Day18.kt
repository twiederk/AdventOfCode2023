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

    val movement = mapOf(
        (Point2D.NORTH to 'R') to Point2D.EAST,
        (Point2D.NORTH to 'L') to Point2D.WEST,
        (Point2D.SOUTH to 'R') to Point2D.WEST,
        (Point2D.SOUTH to 'L') to Point2D.EAST,
        (Point2D.EAST to 'U') to Point2D.NORTH,
        (Point2D.EAST to 'D') to Point2D.SOUTH,
        (Point2D.EAST to 'U') to Point2D.SOUTH,
        (Point2D.EAST to 'D') to Point2D.NORTH,
    )

    fun dig(digInstruction: DigInstruction): List<Point2D> {
        val trench = mutableListOf<Point2D>()
        facing = movement[(facing to digInstruction.direction)]
            ?: throw IllegalArgumentException("Can't turn [facing=$facing, direction=${digInstruction.direction}] $digInstruction")
        for (index in 1..digInstruction.meters) {
            position += facing
            trench.add(position)
        }
        return trench
    }

}

