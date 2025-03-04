package blackjack.model

import java.util.LinkedList

class CardDeck {
    private val cards = LinkedList(CACHE_CARDS.shuffled())

    fun draw(): Card = cards.poll() ?: throw IllegalArgumentException("[ERROR] 더 이상 카드를 뽑을 수 없습니다.")

    companion object {
        private val CACHE_CARDS =
            CardRank.entries.flatMap { cardRank ->
                CardSuit.entries.map { cardSuit ->
                    Card(cardRank, cardSuit)
                }
            }
    }
}
