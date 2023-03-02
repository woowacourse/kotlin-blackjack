package view

import model.Name

class InputView {
    fun readName(): List<Name> {
        val input = readln().replace(" ", "")
        require(!input.isNullOrBlank()) { NULL_ERROR }
        val names = input.split(",")
        return names.map { Name(it) }
    }

    fun readYesOrNo(): Boolean {
        val input = readln()
        require(!input.isNullOrBlank()) { NULL_ERROR }
        require(Regex("[yYnN]").matches(input)) { IS_NOT_YES_OR_NO_ERROR }
        if (input == "y" || input == "Y") {
            return true
        }
        return false
    }

    companion object {
        private const val ERROR_MESSAGE = "잘못된 입력 : "
        private const val NULL_ERROR = "입력 값이 비었습니다"
        private const val IS_NOT_YES_OR_NO_ERROR = "입력 값은 y 혹은 n이 아닙니다"
    }
}
