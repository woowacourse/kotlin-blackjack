package blackjack.model

import blackjack.model.Card.Companion.SINGLE_DECK
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

    companion object {
        private const val DECK_COUNT = 6
        private val CACHE_CARDS = (1..DECK_COUNT).flatMap { SINGLE_DECK }
    }
}
