package view

class FakeOutput(private val fakeConsole: StringBuilder) : Output {
    override fun print(message: Any?) {
        fakeConsole.append(message)
    }

    override fun println(message: Any?) {
        fakeConsole.append(message)
        fakeConsole.append("\n")
    }
}
