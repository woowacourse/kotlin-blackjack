package blackjack.view

import blackjack.domain.player.Player

object InputView {

    private const val REQUEST_PLAYERS_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요."
    private const val TOKENIZER = ","
    private const val REQUEST_ADDITIONAL_CARDS_MSG = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"
    private const val ADDITIONAL_DRAW_ERROR_MSG = "[ERROR] y또는 n 이외의 입력은 받지 않습니다."
    private const val REQUEST_PLAYERS_BATTING_MONEY = "%s의 배팅 금액은?"
    private const val NUMERIC_ERROR_MSG = "[ERROR] 숫자가 아닌 입력은 허용하지 않습니다.\n다시 입력해주세요."

    private fun <T> requestSpecificInput(errorMessage: String, toSomeTypeOrNull: (String) -> T?): T {

        while (true) {
            toSomeTypeOrNull(readln())?.let { someType -> return someType }
            println(errorMessage)
        }
    }

    private fun requestNumericInput(message: String): Int {
        println(message)

        return requestSpecificInput(NUMERIC_ERROR_MSG, String::toIntOrNull)
    }

    fun requestPlayersName(): List<String> {
        println(REQUEST_PLAYERS_NAME_MSG)

        return readln().split(TOKENIZER).map(String::trim)
    }

    fun requestPlayersBattingMoney(playerNames: List<String>): List<Int> {
        val battingMoneys = playerNames.map { playerName ->

            requestNumericInput(REQUEST_PLAYERS_BATTING_MONEY.format(playerName))
        }

        return battingMoneys
    }

    fun requestAdditionalDraw(player: Player): Boolean {
        println(REQUEST_ADDITIONAL_CARDS_MSG.format(player.name.value))

        return requestSpecificInput(ADDITIONAL_DRAW_ERROR_MSG, ::toBooleanOrNull)
    }

    private fun toBooleanOrNull(input: String): Boolean? = when (input) {
        YES -> true
        NO -> false
        else -> null
    }
}
