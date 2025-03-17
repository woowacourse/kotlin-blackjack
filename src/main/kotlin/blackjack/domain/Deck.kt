package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        initializeDeck()
        shuffleDeck()
    }

    private fun initializeDeck() {
        Suit.entries.forEach { suit ->
            Rank.entries.forEach { rank ->
                cards.add(Card(rank, suit))
            }
        }
    }

    fun shuffleDeck() {
        cards.shuffle()
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱에 남아있는 카드가 없습니다.")
        }
        return cards.removeAt(0)
    }

    fun remainingCards(): Int = cards.size
}
