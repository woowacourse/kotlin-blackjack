package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.WinningResultStatus

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun canDraw(): Boolean

    fun addInitialCards(cardDeck: CardDeck) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(cardDeck.draw())
        }
    }

    fun draw(card: Card) {
        cardHand.addNewCard(card)
    }

    fun match(other: Role): WinningResultStatus {
        val myScore = cardHand.calculateScore()
        val otherScore = other.cardHand.calculateScore()

        return when {
            myScore > BLACK_JACK_SCORE && this is Player -> WinningResultStatus.DEFEAT
            myScore > BLACK_JACK_SCORE && this is Dealer && otherScore < BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            otherScore > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            myScore > otherScore -> WinningResultStatus.VICTORY
            myScore == otherScore -> WinningResultStatus.PUSH
            else -> WinningResultStatus.DEFEAT
        }
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
        private const val BLACK_JACK_SCORE = 21
    }
}
