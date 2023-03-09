package view

class ConsoleInput : Input {
    override fun readln(): String = kotlin.io.readln()
}
