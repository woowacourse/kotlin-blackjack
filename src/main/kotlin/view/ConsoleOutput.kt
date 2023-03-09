package view

class ConsoleOutput : Output {
    override fun print(message: Any?) {
        kotlin.io.print(message)
    }

    override fun println(message: Any?) {
        kotlin.io.println(message)
    }
}
