package blackjack.model

data class HandCards(val cards: List<Card>) {
    init {
        require(cards.size >= 2) { "손패는 2장 이상임" }
    }
}
