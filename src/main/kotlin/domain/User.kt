package domain

import domain.card.Card

class User(
    name: String,
    cards: Cards,
) : Player(name, cards) {
    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, Cards(userNameAndCard.second))
    }
}
