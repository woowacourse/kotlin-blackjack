package blackjack.model

class Cards(
    val cards: List<Card>,
    private val pointCalculator: PointCalculator = DefaultPointCalculator(),
) {
    constructor(vararg cards: Card) : this(cards.toList())

    operator fun plus(other: Cards): Cards {
        return Cards(cards + other.cards)
    }

    operator fun plus(card: Card): Cards {
        return Cards(cards + card)
    }

    // TODO : 안쓰면 지우기
    operator fun minus(card: Card): Cards {
        return Cards(cards - card)
    }

    // TODO : 안쓰면 지우기 2
    operator fun minus(other: Cards): Cards {
        return Cards(cards - other.cards.toSet())
    }

    fun sumOrNull(): Int? = pointCalculator.sumOrNull(cards)

    companion object {
        private val DECK: Cards = createDeck()

        @JvmStatic
        fun createDeck(size: Int = 1): Cards {
            require(size > 0)
            if (size == 1) return DECK
            return Cards(
                List(size) { createDeck().cards }.flatten(),
            )
        }

        @JvmStatic
        private fun createDeck(): Cards {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: List<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }
            return Cards(deck)
        }
    }
}
