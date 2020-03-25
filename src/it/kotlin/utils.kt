import java.io.File
import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.DynamicTest

typealias TestFunction = (String, String, String) -> Unit

fun getOutputExpected(pathToOutputFile: String): String? {
    val fileOutput = File(pathToOutputFile)
    if (fileOutput.exists())
        return CharStreams.fromFileName(pathToOutputFile).toString()
    return null
}

fun makeTestsFromResourceFolder(folderPath: String, test: TestFunction): List<DynamicTest> {
    val tests = mutableListOf<DynamicTest>()
    File(folderPath).list { _, name -> name.endsWith(".in") }!!.forEach {
        // arrange

        val input = CharStreams.fromFileName(folderPath + it).toString()

        var output = input
        getOutputExpected(folderPath + it.replace(".in", ".out"))?.let { outputResult ->
            output = outputResult
        }

        output = output.trim()

        val fileMessage = File(folderPath + it.replace(".in", ".msg")).readLines()
        val testName = fileMessage.first()

        tests.add(DynamicTest.dynamicTest(testName) { test(input, output, testName) })
    }
    return tests
}
