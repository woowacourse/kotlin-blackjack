package blackjack.domain.model

abstract class Participant {
    abstract val cards: MutableList<Card>

    open fun showCards(count: Int = cards.size): List<Card> {
        return this.cards.take(count).map { it.copy() }
    }

    open fun accept(cards: List<Card>) {
        this.cards.addAll(cards)
    }
}
