package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardBunch
import blackjack.domain.CardNumber

class OutputView {
    private fun makeCardString(card: Card): String = CardNumber.valueOf(card.cardNumber) + card.shape.korean

    fun printDealerInitialCard(cardBunch: CardBunch) {
        println(DEALER_INITIAL_CARD_SCRIPT.format(makeCardString(cardBunch.cards.first())))
    }

    companion object {
        private const val DEALER_INITIAL_CARD_SCRIPT = "딜러: %d"
    }
}
