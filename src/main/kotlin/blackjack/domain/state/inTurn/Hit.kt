package blackjack.domain.state.inTurn

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Score
import blackjack.domain.state.State
import blackjack.domain.state.endTurn.BlackJack
import blackjack.domain.state.endTurn.Bust
import blackjack.domain.state.endTurn.Stay

class Hit(cards: Cards) : InTurn(cards) {
    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.calculateScore().isBlackJack -> Stay(newCards)
            newCards.calculateScore().isBust -> Bust(newCards)
            else -> Hit(cards + card)
        }
    }

    override fun matchWith(otherState: State): Outcome =
        when (otherState) {
            is BlackJack -> Outcome.LOSE
            is Bust -> Outcome.WIN
            is Stay -> compareScore(otherState.score)
            is Hit -> compareScore(otherState.score)
            else -> {
                throw IllegalStateException("Dealer's state is not valid") }
        }

    private fun compareScore(otherScore: Score) = when {
        otherScore > score -> Outcome.LOSE
        otherScore < score -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
