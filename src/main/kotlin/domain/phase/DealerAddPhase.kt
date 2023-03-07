package domain.phase

import domain.CardDeck
import domain.Dealer
import domain.Participants

class DealerAddPhase(val printDealerAddCard: (Dealer) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        dealerSelectPhase(participants.dealer, deck)
    }

    private fun dealerSelectPhase(dealer: Dealer, deck: CardDeck) {
        if (dealer.isPossibleDrawCard()) {
            printDealerAddCard(dealer)
            dealer.addCard(deck.draw())
            dealerSelectPhase(dealer, deck)
        }
    }
}
