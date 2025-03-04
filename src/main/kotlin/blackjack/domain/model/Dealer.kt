package blackjack.domain.model

data class Dealer(val name: String = DEALER_NAME, val cards: List<Card> = emptyList()) {
    fun accept(card: Card): Dealer {
        return this.copy(cards = cards + card)
    }

    fun getScore(): Int {
        return cards.sumOf { it.rank.score }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
