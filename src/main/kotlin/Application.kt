import controller.BlackjackController

fun main() {
    runCatching {
        val game = BlackjackController()
        game.process()
    }.onFailure {
        print("[ERROR] : ")
        println(it.message)
        println(it.stackTraceToString())
    }
}
