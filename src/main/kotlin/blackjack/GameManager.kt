package blackjack

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun dealInitialCardWithCount(count: Int) {
        dealer.addCards(drawCards(count))
        players.forEach { player -> player.addCards(drawCards(count)) }
    }

    private fun drawCards(count: Int): List<Card> {
        return List(count) { Deck.draw() }
    }
}
