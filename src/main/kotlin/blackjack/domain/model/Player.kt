package blackjack.domain.model

data class Player(val name: String, val cards: List<Card>) {
    fun accept(card: Card): Player {
        return this.copy(cards = cards + card)
    }
}
