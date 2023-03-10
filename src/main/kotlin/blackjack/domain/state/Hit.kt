package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.result.Outcome

class Hit(cards: Cards) : InTurn(cards) {
    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.calculateScore().isBlackJack -> Stay(newCards)
            newCards.calculateScore().isBust -> Bust(newCards)
            else -> Hit(cards + card)
        }
    }

    override fun matchWith(dealer: Dealer): Outcome =
        when (dealer.state) {
            is BlackJack -> Outcome.LOSE
            is Bust -> Outcome.WIN
            is Stay -> compareScore(dealer)
            is Hit -> compareScore(dealer)
            else -> {
                throw IllegalStateException("Dealer's state is not valid") }
        }

    private fun compareScore(dealer: Dealer) = when {
        dealer.score > score -> Outcome.LOSE
        dealer.score < score -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
