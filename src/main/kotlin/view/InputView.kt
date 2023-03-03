package view

class InputView {
    fun readName(): List<String> {
        val input = readln().replace(" ", "")
        require(input.isNotBlank()) { NULL_ERROR }
        val names = input.split(",")
        require(names.size <= PLAYER_MAX) { PLAYER_COUNT_ERROR }
        return names
    }

    fun readYesOrNo(): Boolean {
        val input = readln()
        require(input.isNotBlank()) { NULL_ERROR }
        require(Regex("[$Y$y$N$n]").matches(input)) { IS_NOT_YES_OR_NO_ERROR }
        if (input == Y || input == y) return true
        return false
    }

    companion object {
        private const val Y = "Y"
        private const val y = "y"
        private const val N = "N"
        private const val n = "n"

        private const val PLAYER_MAX = 8
        private const val NULL_ERROR = "입력 값이 비었습니다"
        private const val IS_NOT_YES_OR_NO_ERROR = "입력 값은 y 혹은 n이 아닙니다"
        private const val PLAYER_COUNT_ERROR = "플레이어는 8명까지 가능합니다"
    }
}
