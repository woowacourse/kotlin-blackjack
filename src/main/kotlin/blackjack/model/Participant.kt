package blackjack.model

abstract class Participant(val name: String) {

    protected val cards = Cards()

    fun isBusted(): Boolean {
        val score = cards.sum()
        val threshold = 21
        return threshold < score
    }

    fun isMaxScore(): Boolean {
        val score = cards.sum()
        val threshold = 21
        return threshold == score
    }

    fun isBlackJack(): Boolean = cards.size == 2 && isMaxScore()
    fun addCard(card: Card) = cards.addCard(card)
    fun getCardSum(): Int = cards.sum()
    fun showCard() = cards.showCard()
    abstract fun isHitable(): Boolean
}
