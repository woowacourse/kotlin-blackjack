package blackjack.domain

class BlackJackGame(
    private val player: List<Player>,
    private val dealer: Dealer,
) {
    private val deck = Deck()

    fun initializedHandOutCards(initializedCardCount: Int) {
        repeat(initializedCardCount) {
            player.map { it.drawCard(deck.pop()) }
            dealer.drawCard(deck.pop())
        }
    }
}
