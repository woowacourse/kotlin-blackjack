package domain

import domain.card.Card

class User private constructor(
    name: String,
    deck: Deck,
) : Player(name, deck) {

    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, Deck(userNameAndCard.second.toMutableList()))
    }
}
