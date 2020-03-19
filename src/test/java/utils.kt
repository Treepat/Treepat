import org.antlr.v4.runtime.CharStreams
import java.io.File

typealias TestFunction = (String, String, String) -> Unit

private fun getOutputExpected(pathToOutputFile: String): String? {
    val fileOutput = File(pathToOutputFile)
    if (fileOutput.exists())
        return CharStreams.fromFileName(pathToOutputFile).toString()
    return null
}

fun runAllTestInFolder(folderPath: String, test: TestFunction) {
    File(folderPath).list { _, name -> name.endsWith(".in") }!!.forEach {
        // arrange

        val input = CharStreams.fromFileName(folderPath + it).toString()

        var output = input
        getOutputExpected(folderPath + it.replace(".in", ".out"))?.let { outputResult ->
            output = outputResult
        }

        output = output.trim()

        val fileMessage = File(folderPath + it.replace(".in", ".msg")).readLines()
        val error = fileMessage.first()

        test(input, output, error)
    }
}