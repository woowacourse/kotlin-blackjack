package blackjack.view

import blackjack.model.BettingAmount

class BettingAmountInputView {
    fun readBettingAmount(playerName: String): BettingAmount {
        println(PLAYERS_INPUT_MESSAGE.format(playerName))
        val input = readln()
        return runCatching {
            BettingAmount.bettingAmountOf(input)
        }.onFailure {
            println(it.message)
            return readBettingAmount(playerName)
        }.getOrThrow()
    }

    companion object {
        private const val PLAYERS_INPUT_MESSAGE = "\n%s의 배팅 금액은?"
    }
}
