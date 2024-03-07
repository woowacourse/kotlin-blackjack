package blackjack.view

class IsAddCardInputView {
    fun readIsAddCard(playerName: String): Boolean {
        println("${playerName}는 한 장의 카드를 더 받겠습니까?(예는 ${YES}, 아니오는 ${NO})")
        val input = readln()
        validateChoice(input)
        return input == YES
    }

    private fun validateChoice(input: String) {
        require(input == YES || input == NO) {
            "$YES 또는 ${NO}만 입력 가능합니다."
        }
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
    }
}
