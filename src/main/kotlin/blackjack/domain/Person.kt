package blackjack.domain

abstract class Person(val name: String) {
    val hand: Hand = Hand()

    abstract fun draw(
        deck: Deck,
        amount: Int,
    )
}
