package blackjack.model

class GameTable {
    private val dealer: Dealer = Dealer()
    private val deck: Deck = Deck()

    fun initGame(players: List<Player>) {
        players.forEach {
            it.pickCard()
            it.pickCard()
        }
        dealer.pickCard()
        dealer.pickCard()
    }

    private fun Participant.pickCard() {
        this.addCard(deck.pick())
    }
}


