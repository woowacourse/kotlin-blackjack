package blackjack.model

class CardDeck {
    private val cards = ArrayDeque(BLACKJACK_CACHE_CARDS.shuffled())

    fun draw(count: Int): List<Card> =
        List(count) {
            cards.removeFirstOrNull()
                ?: throw IllegalArgumentException("[ERROR] 더 이상 카드를 뽑을 수 없습니다.")
        }

    companion object {
        private val CACHE_CARDS =
            CardRank.entries.flatMap { cardRank ->
                CardSuit.entries.map { cardSuit ->
                    Card(cardRank, cardSuit)
                }
            }
        private val BLACKJACK_CACHE_CARDS = List(8) { CACHE_CARDS }.flatten()
    }
}
