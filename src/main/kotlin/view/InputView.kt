package view

class InputView {
    fun readName(): List<String> {
        println(MESSAGE_INPUT_NAME)
        val input = readln().replace(" ", "")
        require(input.isNotBlank()) { NULL_ERROR }
        val players = input.split(",")
        require(players.size <= PLAYER_MAX) { PLAYER_COUNT_ERROR }
        return players
    }

    fun readYesOrNo(name: String): Boolean {
        println(MESSAGE_INPUT_YES_OR_NO.format(name))
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
        private const val MESSAGE_INPUT_NAME = "게임에 참여할 플레이어의 이름을 입력하세요. (쉼표 기준으로 분리)"
        private const val MESSAGE_INPUT_YES_OR_NO = "%s는/은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PLAYER_COUNT_ERROR = "플레이어는 8명까지 가능합니다"
        private const val NULL_ERROR = "입력 값이 비었습니다"
        private const val IS_NOT_YES_OR_NO_ERROR = "입력 값은 y 혹은 n이 아닙니다"
    }
}
