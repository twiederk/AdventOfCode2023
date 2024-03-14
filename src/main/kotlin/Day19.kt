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

// qqz{s>2770:qs,m<1801:hdj,R}
data class Workflow(val line: String) {

    val key: String
    val rules = mutableListOf<Rule>()

    init {
        key = line.substringBefore("{")
        line.substringAfter("{").substringBefore("}").split(',')
            .forEach { rules.add(Rule(it)) }
    }

    fun next(part: Part): String {
        rules.forEach {
            if (it.field == null) {
                return it.next
            }
            when (it.operator) {
                '>' -> if (part.getValueOf(it.field!!) > it.value!!) return it.next
                '<' -> if (part.getValueOf(it.field!!) < it.value!!) return it.next
            }
        }
        throw IllegalStateException("No rule found for part $part")
    }
}

// {x=787,m=2655,a=1222,s=2876}
data class Part(val line: String) {

    val x: Int
    val m: Int
    val a: Int
    val s: Int

    init {
        val regex = Regex("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}")
        val matchResult = regex.find(line)
        val (x, m, a, s) = matchResult!!.destructured
        this.x = x.toInt()
        this.m = m.toInt()
        this.a = a.toInt()
        this.s = s.toInt()
    }

    fun getValueOf(field: String): Int {
        when (field) {
            "x" -> return x
            "m" -> return m
            "a" -> return a
            "s" -> return s
        }
        throw IllegalArgumentException("Unknown field $field")
    }

}

// s>2770:qs
// m<1801:hdj
// R
data class Rule(val line: String) {

    var field: String? = null
    var operator: Char? = null
    var value: Int? = null
    val next: String

    init {
        if (line.contains(':')) {
            field = line[0].toString()
            operator = line[1]
            value = line.substring(2, line.indexOf(':')).toInt()
            next = line.substringAfter(':')
        } else {
            next = line
        }
    }

}

