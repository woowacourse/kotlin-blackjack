import controller.BlackjackController
import model.RandomCardFactory
import view.ConsoleInput
import view.ConsoleOutput
import view.GameView
import view.InitView
import view.ResultView

fun main() {
    runCatching {
        val input = ConsoleInput()
        val output = ConsoleOutput()
        val game = BlackjackController(
            InitView(input, output),
            GameView(input, output),
            ResultView(output),
            RandomCardFactory()
        )
        game.process()
    }.onFailure {
        print("[ERROR] : ")
        println(it.message)
        println(it.stackTraceToString())
    }
}
