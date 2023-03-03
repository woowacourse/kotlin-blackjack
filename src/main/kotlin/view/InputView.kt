package view

import model.Name

class InputView {
    fun readName(): List<Name> {
        val input = readln().replace(" ", "")
        require(input.isNotBlank()) { NULL_ERROR }
        val names = input.split(",")
        require(names.size <= 8) { PLAYER_COUNT_ERROR }
        return names.map { Name(it) }
    }

    fun readYesOrNo(): Boolean {
        val input = readln()
        require(input.isNotBlank()) { NULL_ERROR }
        require(Regex("[yYnN]").matches(input)) { IS_NOT_YES_OR_NO_ERROR }
        if (input == "y" || input == "Y") {
            return true
        }
        return false
    }

    companion object {
        private const val NULL_ERROR = "입력 값이 비었습니다"
        private const val IS_NOT_YES_OR_NO_ERROR = "입력 값은 y 혹은 n이 아닙니다"
        private const val PLAYER_COUNT_ERROR = "플레이어는 8명까지 가능합니다"
    }
}
