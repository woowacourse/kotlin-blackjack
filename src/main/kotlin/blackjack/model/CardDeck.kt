package blackjack.model

import java.util.LinkedList

interface ShuffleStrategy{
    fun shuffle(cardList: List<Card>) : LinkedList<Card>
}

class RandomShuffle: ShuffleStrategy{
    override fun shuffle(cardList : List<Card>) = LinkedList(cardList.shuffled())
}


class CardDeck(val shuffleStrategy :ShuffleStrategy = RandomShuffle()) {
    private val cards = shuffleStrategy.shuffle(CACHE_CARDS)

    fun draw(): Card =
        cards.poll() ?: throw IllegalArgumentException("[ERROR] 더 이상 카드를 뽑을 수 없습니다.")

    fun shuffleCard(cardList : List<Card>,):List<Card> = shuffleStrategy.shuffle(cardList)

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
