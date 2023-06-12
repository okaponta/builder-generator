import java.io.File
import java.util.Scanner

data class Field(
    val name: String,
    val type: String,
    val nullable: Boolean,
) {
    fun default(): String {
        return when {
            nullable -> "null"
            type.contains("String") -> """"""""
            type.contains("List") -> "listOf()"
            else -> "0"
        }
    }
}

val fileName = args[1]

var className = ""
val classNameRegex = "data class (.*)\\(".toRegex()

val fields = mutableListOf<Field>()
val fieldRegex = ".*val (.*):.*(Byte|Byte\\?|Int|Int\\?|String|String\\?).*".toRegex()

val sc = Scanner(File(fileName))
while (sc.hasNextLine()) {
    val line = sc.nextLine()
    if (classNameRegex.matches(line)) {
        className = line.replace(classNameRegex, "$1")
    }
    if (fieldRegex.matches(line)) {
        val fieldName = line.replace(fieldRegex, "$1")
        val fieldType = line.replace(fieldRegex, "$2")
        val isNullable = fieldType.contains('?')
        fields.add(Field(fieldName, fieldType, isNullable))
    }
}

println("class " + className + "Builder {")
for (field in fields) {
    println("    private var " + field.name + ": " + field.type + " = " + field.default())
}
println()
for (field in fields) {
    println("    fun ${field.name}(${field.name}: ${field.type}): $className" + "Builder {")
    println("        this.${field.name} = ${field.name}")
    println("        return this")
    println("    }")
    println()
}
println("    fun build(): $className {")
println("        return $className (")
for (field in fields) {
    println("            ${field.name}, ")
}
println("        )")
println("    }")
println("}")
