package domain

class BlackJackCardDeck : CardDeck {
    private val cards: MutableList<Card> = mutableListOf()

    val size: Int
        get() = cards.size

    init {
        CardCategory.values().forEach { cardCategory ->
            CardNumber.values().forEach { cardNumber ->
                cards.add(Card(cardCategory, cardNumber))
            }
        }
    }

    fun contains(card: Card): Boolean {
        return cards.contains(card)
    }

    override fun draw(): Card {
        val drawCard = cards.shuffled()[0]
        cards.remove(drawCard)
        return drawCard
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() }.toSet())
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
    }
}
