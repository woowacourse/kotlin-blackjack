package entity.users

import entity.result.GameResultType
import entity.card.Cards
import misc.GameRule

class Player(val userInformation: UserInformation, cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() < GameRule.WINNING_NUMBER

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
