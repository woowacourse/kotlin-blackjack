package blackjack.model

class DeckManager {
    private val deck: Deck = Deck()

    fun initGame(dealer: Dealer, players: List<Player>) {
        players.forEach { player ->
            player.initCard()
        }
        dealer.initCard()
    }

    infix fun giveCardTo(participant: Participant) {
        participant.addCard(deck.pick())
    }

    private fun Participant.initCard() {
        this.addCard(deck.pick())
        this.addCard(deck.pick())
    }
}


