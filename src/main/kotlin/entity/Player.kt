package entity

import misc.GameRule
import model.CardFactory

class Player(val name: Name, cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() < GameRule.WINNING_NUMBER

    fun addMoreCards(cardFactory: CardFactory, condition: (player: Player) -> Boolean): Boolean {
        if (isDistributable() && condition(this)) {
            addCards(cardFactory.generate(SINGLE_DISTRIBUTE_COUNT))
            return true
        }
        return false
    }

    fun determineGameResult(dealerCardNumberSum: Int): Pair<Player, GameResultType> {
        val playerCardNumberSum = cardsNumberSum()

        return when {
            playerCardNumberSum in (dealerCardNumberSum + 1)..GameRule.WINNING_NUMBER ||
                GameRule.WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum
            -> Pair(this, GameResultType.WIN)

            playerCardNumberSum == dealerCardNumberSum ||
                playerCardNumberSum > GameRule.WINNING_NUMBER && dealerCardNumberSum > GameRule.WINNING_NUMBER
            -> Pair(this, GameResultType.DRAW)

            else
            -> Pair(this, GameResultType.LOSE)
        }
    }
}
