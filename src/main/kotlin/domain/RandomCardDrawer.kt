package domain

class RandomCardDrawer : CardDrawer {
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

    override fun draw(): Card? {
        val drawCard = cards.shuffled().getOrNull(0) ?: return null
        cards.remove(drawCard)
        return drawCard
    }
}
