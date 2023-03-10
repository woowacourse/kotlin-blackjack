package view

import model.InputState
import model.participants.Bet
import model.participants.Name
import model.participants.Names

class InputView(private val onError: (String) -> Unit) {
    private val regexName = Regex("[가-힣a-zA-Z]+")
    private val regexAnswer = Regex("[$Y$y$N$n]")
    private val regexNumber = Regex("\\d+")

    fun readName(): Names {
        val input = readln().replace(" ", "").split(",")
        input.forEach {
            verifyName(it) ?: return readName()
        }
        return Names(input.map(::Name))
    }

    private fun verifyName(name: String): String? {
        val isError = when {
            name.isBlank() -> InputState.Error(NULL_ERROR)
            !regexName.matches(name) -> InputState.Error(IS_NOT_YES_OR_NO)
            else -> InputState.Success(name)
        }
        if (isError is InputState.Error) {
            onError(isError.error)
            return null
        }
        return name
    }

    fun readBet(): Bet {
        val input = readln()
        if (verifyBet(input) == null) return readBet()
        return Bet(input.toInt())
    }

    private fun verifyBet(bet: String): String? {
        val isError = when {
            bet.isBlank() -> InputState.Error(NULL_ERROR)
            !regexNumber.matches(bet) -> InputState.Error(IS_NOT_NUMBER)
            bet.toInt() !in MIN_VALUE..MAX_VALUE -> InputState.Error(AMOUNT_RANGE_ERROR)
            else -> InputState.Success(bet)
        }
        if (isError is InputState.Error) {
            onError(isError.error)
            return null
        }
        return bet
    }

    fun readYesOrNo(): Boolean {
        val input = readln()
        return verifyAnswer(input) ?: readYesOrNo()
    }

    private fun verifyAnswer(answer: String): Boolean? {
        val isError = when {
            answer.isBlank() -> InputState.Error(NULL_ERROR)
            !regexAnswer.matches(answer) -> InputState.Error(IS_NOT_YES_OR_NO)
            else -> InputState.Success(answer == y || answer == Y)
        }
        if (isError is InputState.Error) {
            onError(isError.error)
            return null
        }
        return answer == y || answer == Y
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
        private const val MIN_VALUE = 1000
        private const val MAX_VALUE = 1000000
        private const val AMOUNT_RANGE_ERROR = "배팅 금액은 1000원부터 1000000원까지 가능합니다."
    }
}
