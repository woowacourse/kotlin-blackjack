package blackjack.model

class DeckManager {
    private val deck: Deck = Deck()

    fun initGame(dealer: Dealer, players: List<Player>) {
        players.forEach {
            it.pickCard()
            it.pickCard()
        }
        dealer.pickCard()
        dealer.pickCard()
    }

    infix fun giveCardTo(participant: Participant) {
        participant.addCard(deck.pick())
    }

    private fun Participant.pickCard() {
        this.addCard(deck.pick())
    }
}


