package blackjack.domain.model

abstract class Cards {
    protected abstract val cards: MutableCollection<Card>

    fun accept(cards: List<Card>) {
        this.cards.addAll(cards)
    }
}
