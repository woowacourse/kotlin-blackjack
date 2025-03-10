package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer

class DealerTurn(
    private val dealer: Dealer,
    private val deck: Deck,
) {
    fun play(printDealerDraw: () -> Unit) {
        while (dealer.canDraw()) {
            printDealerDraw()
            dealer.draw(deck)
        }
    }
}
