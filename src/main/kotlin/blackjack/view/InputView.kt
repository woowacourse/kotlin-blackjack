package blackjack.view

class InputView : BlackJackInputView {
    override fun getNames(): List<String> {
        println(ENTER_PLAYER_NAMES_MESSAGE)
        val input = readLineWithValidate()
        val names = input.split(DELIMITER).map { it.trim() }
        names.forEach { name -> require(name.isNotBlank()) { BLACK_PLAYER_NAME_MESSAGE } }
        return names
    }

    override fun getBetAmount(name: String): Int {
        println("\n" + ASK_BET_AMOUNT_MESSAGE.format(name))
        val input = readLineWithValidate()
        return requireNotNull(input.toIntOrNull()) { INVALID_BET_AMOUNT_MESSAGE }
    }

    override fun getIsHit(name: String): Boolean {
        println(ASK_DRAW_CARD_MESSAGE.format(name))
        val input = readLineWithValidate()
        require(input == YES || input == NO) { INVALID_FLAG_MESSAGE }
        return input == YES
    }

    private fun readLineWithValidate(): String {
        val input = readlnOrNull() ?: ""
        validateInput(input)
        return input
    }

    private fun validateInput(input: String) {
        require(input.isNotBlank()) { INVALID_INPUT_MESSAGE }
    }

    companion object {
        private const val ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ASK_DRAW_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val ASK_BET_AMOUNT_MESSAGE = "%s의 배팅 금액은?"
        private const val DELIMITER = ","
        private const val YES = "y"
        private const val NO = "n"
        private const val INVALID_FLAG_MESSAGE = "$YES 혹은 ${NO}을 입력해주세요."
        private const val INVALID_INPUT_MESSAGE = "입력이 비어있습니다."
        private const val INVALID_BET_AMOUNT_MESSAGE = "배팅 금액은 숫자여야 합니다."
        private const val BLACK_PLAYER_NAME_MESSAGE = "플레이어의 이름은 비어있을 수 없습니다."
    }
}
