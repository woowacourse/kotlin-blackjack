package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.MultiDeck
import blackjack.domain.player.Player.Companion.CARD_SETTING_COUNT
import blackjack.domain.result.GameResult
import blackjack.domain.result.MatchResult

abstract class Player(
    val name: String,
    val cards: Cards = Cards(),
    val matchResult: MatchResult = MatchResult()
) {

    abstract fun canHit(): Boolean

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun setFirstTurnCards(multiDeck: MultiDeck) =
        repeat(CARD_SETTING_COUNT) { cards.add(multiDeck.draw()) }

    fun decideGameResult(otherPlayer: Player) {
        val otherPlayerCardsSum: Int = otherPlayer.cards.sum()
        val cardsSum = cards.sum()

        val gameResult = when {
            cardsSum > MAX_SUM_NUMBER -> GameResult.LOSE
            otherPlayerCardsSum > MAX_SUM_NUMBER -> GameResult.WIN
            otherPlayerCardsSum > cardsSum -> GameResult.LOSE
            otherPlayerCardsSum == cardsSum -> GameResult.DRAW
            isBlackjack() -> GameResult.BLACKJACK
            else -> GameResult.WIN
        }

        matchResult.count(gameResult)
    }

    private fun isBlackjack() =
        (cards.size == CARD_SETTING_COUNT) && (cards.sum() == MAX_SUM_NUMBER)

    companion object {
        const val CARD_SETTING_COUNT = 2
        const val MAX_SUM_NUMBER = 21
    }
}
