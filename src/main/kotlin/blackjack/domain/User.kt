package blackjack.domain

abstract class User(name: String) {
    val name = Name(name)
    val cards = Cards()
    val score: Int
        get() = Score(cards).score

    abstract val isContinue: Boolean

    val isBlackJack: Boolean
        get() = Score(cards).score == BLACKJACK_NUMBER

    fun draw(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
