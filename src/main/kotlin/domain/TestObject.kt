package domain

object TestObject {
    val createdCard = HashMap<String, TrumpCard>()

    init {
        createdCard["ACE"] = TrumpCard(TrumpCardNumber.ACE, TrumpCardPattern.CLOVER)
        createdCard["TWO"] = TrumpCard(TrumpCardNumber.TWO, TrumpCardPattern.CLOVER)
    }

    fun getCard(cardName: String): TrumpCard {
        return createdCard[cardName] ?: throw IllegalArgumentException()
    }
}