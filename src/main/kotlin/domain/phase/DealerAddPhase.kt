package domain.phase

import domain.Dealer
import domain.Participants
import domain.card.CardDeck

class DealerAddPhase(val printDealerAddCard: (Dealer) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        participants.dealer.dealerSelectPhase(deck)
    }

    private fun Dealer.dealerSelectPhase(deck: CardDeck) {
        when (isPossibleDrawCard()) {
            true -> {
                printDealerAddCard(this)
                addCard(deck.draw())
                dealerSelectPhase(deck)
            }

            false -> stay()
        }
    }
}
