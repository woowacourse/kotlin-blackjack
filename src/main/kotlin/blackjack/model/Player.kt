package blackjack.model

class Player(val name: String, val handCards: HandCards) {
    fun addCard(isAdd: Boolean) {
        if (isAdd) {
            handCards.add()
        }
    }

    fun isBust(): Boolean = handCards.calculateCardScore() > 21

    fun getCards() = handCards.cards
}
