package blackjack.model

class Player(val name: String, val handCards: HandCards) {
    fun addCard(playerInput: Boolean): Boolean =
        if (isCanAddCard(playerInput)) {
            handCards.add()
            true
        } else {
            false
        }

    private fun isCanAddCard(isAdd: Boolean) = isAdd && !isBust()

    fun isBust(): Boolean = handCards.calculateCardScore() > 21

    fun getCards() = handCards.cards.joinToString(", ") { "${it.cardNumber.value}${it.pattern.shape}" }

    fun getScore() = handCards.calculateCardScore()
}
