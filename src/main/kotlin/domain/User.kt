package domain

import domain.card.Card

class User(val name: String, val cards: Cards) {
    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, Cards(userNameAndCard.second))
    }
}
