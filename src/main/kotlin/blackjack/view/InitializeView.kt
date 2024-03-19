package blackjack.view

object InitializeView {
    private const val MESSAGE_INPUT_PARTICIPANT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val MESSAGE_INPUT_BETTING_AMOUNT = "\n%s의 배팅 금액은?"
    private const val MESSAGE_CONTINUE_INPUT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val DELIMITER_NAMES = ","

    fun readPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PARTICIPANT_NAMES)
        return readln().split(DELIMITER_NAMES).map { name ->
            name.trim()
        }
    }

    fun readPlayersBettingAmount(names: List<String>): List<Int>? {
        return names.map { readSinglePlayerBettingAmount(it) ?: return null }
    }

    fun readContinueInput(name: String): String {
        println(MESSAGE_CONTINUE_INPUT.format(name))
        return readln().trim()
    }

    private fun readSinglePlayerBettingAmount(name: String): Int? {
        println(MESSAGE_INPUT_BETTING_AMOUNT.format(name))
        return readln().toIntOrNull()
    }
}
