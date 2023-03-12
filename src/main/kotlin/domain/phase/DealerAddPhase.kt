package domain.phase

import domain.Dealer
import domain.Participants
import domain.card.CardDeck

class DealerAddPhase(val printDealerAddCard: (Dealer) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        dealerSelectPhase(participants.dealer, deck)
    }

    private fun dealerSelectPhase(dealer: Dealer, deck: CardDeck) {
        if (dealer.isPossibleDrawCard()) {
            printDealerAddCard(dealer)
            dealer.addCard(deck.draw())
            dealerSelectPhase(dealer, deck)
        } else if (dealer.cardsStateIsFinished.not()) {
            dealer.stay()
        }
    }
}
