package blackjack.model

import blackjack.model.Card.Companion.SINGLE_DECK
import java.util.LinkedList

interface ShuffleStrategy {
    fun shuffle(cardList: List<Card>): LinkedList<Card>
}

class RandomShuffle : ShuffleStrategy {
    override fun shuffle(cardList: List<Card>) = LinkedList(cardList.shuffled())
}


class CardDeck(val shuffleStrategy: ShuffleStrategy = RandomShuffle()) {
    private var cards = shuffleStrategy.shuffle(SINGLE_DECK)

    fun draw(): Card {
        return cards.poll() ?: run {
            cards = shuffleStrategy.shuffle(SINGLE_DECK)
            return cards.poll()
        }
    }
}
