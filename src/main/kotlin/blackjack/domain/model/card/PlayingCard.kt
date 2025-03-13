package blackjack.domain.model.card

class PlayingCard(private val deck: ArrayDeque<Card>) {
    fun spreadCard(count: Int): List<Card> {
        return List(count) { deck.removeFirst() }
    }
}
