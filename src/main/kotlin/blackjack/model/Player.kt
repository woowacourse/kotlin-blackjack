package blackjack.model

class Player(val name: String, private val handCards: HandCards) {
    fun addCard(playerInput: Boolean): Boolean =
        if (isCanAddCard(playerInput)) {
            handCards.add()
            true
        } else {
            false
        }

    private fun isCanAddCard(isAdd: Boolean) = isAdd && !isBust()

    fun isBust(): Boolean = handCards.calculateCardScore() > BLACKJACK_NUMBER

    fun getCards() = handCards.cards.joinToString(SPLIT_DELIMITER) { "${it.cardNumber.value}${it.pattern.shape}" }

    fun getScore() = handCards.calculateCardScore()

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val BLACKJACK_NUMBER = 21
    }
}
