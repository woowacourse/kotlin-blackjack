package view

class FakeInput(private val fakeStrings: List<String>) : Input {
    private var index = 0
    override fun readln(): String {
        require(index < fakeStrings.size) {
            MESSAGE_TOO_MANY_INPUT_REQUEST
        }
        return fakeStrings[index++]
    }

    companion object {
        const val MESSAGE_TOO_MANY_INPUT_REQUEST = "주어진 입력보다 많은 입력을 요구했습니다."
    }
}
