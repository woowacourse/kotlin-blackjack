package blackjack.model

abstract class Role {
    abstract val burstCondition: Int
    var cardSum = 0
        private set

    val handCard = HandCard()

    fun receiveCard(card: Card) {
        handCard.add(card)
        addCardSum(card.getValue())
    }

    fun addCardSum(value: Int) {
        cardSum += value
    }

    fun isBurst() = cardSum >= burstCondition
}
