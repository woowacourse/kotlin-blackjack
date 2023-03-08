package view

class InputView {
    private val regexName = Regex("[가-힣a-zA-Z]+")
    private val regexAnswer = Regex("[$Y$y$N$n]")
    private val regexNumber = Regex("\\d+")
    fun readName(): InputState<List<String>> {
        val input = readln().replace(" ", "").split(",")
        input.forEach {
            if (it.isBlank()) return InputState.Error(NULL_ERROR)
            if (!regexName.matches(it)) return InputState.Error(IS_NOT_WORD)
        }
        return InputState.Success(input)
    }

    fun readBet(): InputState<Int> {
        val input = readln()
        if (input.isBlank()) return InputState.Error(NULL_ERROR)
        if (!regexNumber.matches(input)) return InputState.Error(IS_NOT_NUMBER)
        return InputState.Success(input.toInt())
    }

    fun readYesOrNo(): InputState<Boolean> {
        val input = readln()
        if (input.isBlank()) return InputState.Error(NULL_ERROR)
        if (!regexAnswer.matches(input)) return InputState.Error(IS_NOT_YES_OR_NO)
        if (input == Y || input == y) return InputState.Success(true)
        return InputState.Success(false)
    }

    companion object {
        private const val Y = "Y"
        private const val y = "y"
        private const val N = "N"
        private const val n = "n"

        private const val NULL_ERROR = "입력 값이 비었습니다"
        private const val IS_NOT_YES_OR_NO = "입력 값은 y 혹은 n이 아닙니다"
        private const val IS_NOT_NUMBER = "입력 값이 숫자가 아닙니다"
        private const val IS_NOT_WORD = "입력 값이 문자가 아닙니다"
    }
}
