package blackjack.model

class Deck(cards: List<Card> = emptyList()) {
    private var _cards: List<Card> = cards
    val cards: List<Card>
        get() = _cards

    operator fun plus(other: Card): Deck {
        _cards += other
        return Deck(cards)
    }
}
