package blackjack.model.domain.card

class PlayingCard(private val deck: ArrayDeque<Card>) {
    fun spreadCard(): Card {
        return deck.removeFirst()
    }
}
