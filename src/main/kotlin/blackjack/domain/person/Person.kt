package blackjack.domain.person

import blackjack.domain.card.Deck

abstract class Person(val name: String) {
    val hand: Hand = Hand()

    abstract fun draw(
        deck: Deck,
        amount: Int,
    )
}
