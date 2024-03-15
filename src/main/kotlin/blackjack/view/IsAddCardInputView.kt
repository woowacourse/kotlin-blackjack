package blackjack.view

class IsAddCardInputView {
    fun readIsAddCard(playerName: String): Boolean {
        println(PLAYER_ADD_CARDS_MESSAGE.format(playerName))
        val input = readln()
        runCatching {
            validateChoice(input)
        }.onFailure {
            println(it.message)
            readIsAddCard(playerName)
        }
        return input == YES
    }

    private fun validateChoice(input: String) {
        require(input == YES || input == NO) {
            INVALID_INPUT_MESSAGE
        }
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
        private const val PLAYER_ADD_CARDS_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 $YES, 아니오는 $NO)"
        private const val INVALID_INPUT_MESSAGE = "${YES}또는 ${NO}만 입력 가능합니다.."
    }
}
