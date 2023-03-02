package blackjack.domain

abstract class User(name: String) {
    val name = Name(name)
    val cards = Cards()
    val score: Int
        get() = Score(cards).score

    abstract val isContinue: Boolean
    fun draw(card: Card) {
        cards.add(card)
    }
}
