package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.MultiDeck
import blackjack.domain.result.GameResult

abstract class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    abstract fun canHit(): Boolean

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun setFirstTurnCards(multiDeck: MultiDeck) =
        repeat(CARD_SETTING_COUNT) { cards.add(multiDeck.draw()) }

    fun matchGameResult(otherPlayer: Player): GameResult {
        val otherPlayerCardsSum: Int = otherPlayer.cards.sum()
        val cardsSum = cards.sum()

        return when {
            cardsSum > MAX_SUM_NUMBER -> GameResult.LOSE
            otherPlayerCardsSum > MAX_SUM_NUMBER -> GameResult.WIN
            otherPlayerCardsSum > cardsSum -> GameResult.LOSE
            otherPlayerCardsSum == cardsSum -> GameResult.DRAW
            cards.isBlackjack() -> GameResult.BLACKJACK
            else -> GameResult.WIN
        }
    }

    companion object {
        const val CARD_SETTING_COUNT = 2
        const val MAX_SUM_NUMBER = 21
    }
}
