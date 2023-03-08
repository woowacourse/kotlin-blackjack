package domain

import domain.card.Card

class User private constructor(
    name: String,
    hand: Hand,
) : Player(name, hand) {

    companion object {
        fun create(name: String, cards: List<Card>): User =
            User(name, Hand(cards.toMutableList()))
    }
}
