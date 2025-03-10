package blackjack.model

import blackjack.model.Card.Companion.SINGLE_DECK


interface ShuffleStrategy {
    fun shuffle(cardList: List<Card>): MutableList<Card>
}

class RandomShuffle : ShuffleStrategy {
    override fun shuffle(cardList: List<Card>) = cardList.shuffled().toMutableList()
}


class CardDeck(val shuffleStrategy: ShuffleStrategy = RandomShuffle()) {
    private var cards = shuffleStrategy.shuffle(SINGLE_DECK)

    fun draw(): Card {
        return cards.removeFirstOrNull() ?: run {
            cards = shuffleStrategy.shuffle(SINGLE_DECK)
            return cards.removeFirst()
        }
    }
}
