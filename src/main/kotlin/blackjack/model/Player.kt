package blackjack.model

class Player(val name: String, val playerCards: PlayerCards) {
    fun addCard(isAdd: Boolean) {
        if (isAdd) {
            playerCards.add()
        }
    }

    fun isBust(): Boolean = playerCards.calculateCardScore() > 21

    fun getCards() = playerCards.cards
}
