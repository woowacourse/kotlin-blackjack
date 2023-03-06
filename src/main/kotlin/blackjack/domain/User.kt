package blackjack.domain

abstract class User(name: String) {
    val name = Name(name)
    var cards = Cards()
    val score: Int
        get() = Score(cards).score

    val isBlackJack: Boolean
        get() = Score(cards).score == BLACKJACK_NUMBER

    abstract val isContinue: Boolean

    fun draw(card: Card) { cards += card }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
