package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.Betting
import blackjack.model.winning.WinningResultStatus

abstract class Role(open val name: PlayerName, open val cardHand: CardHand, open val betting: Betting) {
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
            otherScore > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            myScore > BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            myScore > otherScore -> WinningResultStatus.VICTORY
            myScore == otherScore -> WinningResultStatus.PUSH
            else -> WinningResultStatus.DEFEAT
        }
    }

    fun calculateProfit(winningResultStatus: WinningResultStatus): Double {
        return when (winningResultStatus) {
            WinningResultStatus.PUSH -> PUSH_MULTIPLIER
            WinningResultStatus.DEFEAT -> betting.amount * DEFEAT_MULTIPLIER
            WinningResultStatus.VICTORY ->
                if (isBlackjack()) {
                    betting.amount * BLACK_JACK_MULTIPLIER
                } else {
                    betting.amount * VICTORY_MULTIPLIER
                }
        }
    }

    private fun isBlackjack() = cardHand.getPlayerState() == CardHandState.BLACKJACK

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
        private const val BLACK_JACK_SCORE = 21
        private const val BLACK_JACK_MULTIPLIER = 1.5
        private const val VICTORY_MULTIPLIER = 1.0
        private const val DEFEAT_MULTIPLIER = -1.0
        private const val PUSH_MULTIPLIER = 0.0
    }
}
