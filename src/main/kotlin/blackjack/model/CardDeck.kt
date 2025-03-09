package blackjack.model

import java.util.LinkedList

class CardDeck {
    private val cards = LinkedList(CACHE_CARDS.shuffled())

    fun draw(count: Int): List<Card> =
        List(count) {
            cards.poll() ?: throw IllegalArgumentException("[ERROR] 더 이상 카드를 뽑을 수 없습니다.")
        }

    companion object {
        private const val DECK_COUNT = 6
        private val SINGLE_DECK = CardRank.entries.flatMap { cardRank ->
            CardSuit.entries.map { cardSuit ->
                Card(cardRank, cardSuit)
            }
        }
        private val CACHE_CARDS = (1..DECK_COUNT).flatMap { SINGLE_DECK }
    }
}
