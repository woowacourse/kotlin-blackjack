package entity

import misc.GameRule
import model.CardDistributor

class Player(val name: String, cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() < GameRule.WINNING_NUMBER

    fun determineGameResult(dealerCardNumberSum: Int): Pair<Player, GameResultType> {
        val playerCardNumberSum = cardsNumberSum()
        return if (playerCardNumberSum in (dealerCardNumberSum + 1)..GameRule.WINNING_NUMBER || (GameRule.WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum)) Pair(
            this,
            GameResultType.WIN
        )
        else if (playerCardNumberSum == dealerCardNumberSum || (playerCardNumberSum > GameRule.WINNING_NUMBER && dealerCardNumberSum > GameRule.WINNING_NUMBER)) Pair(
            this,
            GameResultType.DRAW
        )
        else Pair(this, GameResultType.LOSE)
    }

    fun requestReceiveMoreCard(
        printMessage: (name: String) -> Unit,
        response: () -> String,
        printStatus: (player: Player) -> Unit,
        cardDistributor: CardDistributor
    ) {
        while (isDistributable()) {
            printMessage(name)
            val response = response()
            if (response == "y") {
                cards.addCards(cardDistributor.distribute(SINGLE_DISTRIBUTE_COUNT))
                printStatus(this)
            }
        }
    }
}
