package domain

import domain.card.Card

class User(
    name: String,
    cards: List<Card>,
) : Player(name, cards) {
    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, userNameAndCard.second)
    }
}
