package blackjack

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun dealInitialCardWithCount(count: Int) {
        dealer.addCards(Deck.drawWithCount(count))
        players.forEach { player -> player.addCards(Deck.drawWithCount(count)) }
    }
}
