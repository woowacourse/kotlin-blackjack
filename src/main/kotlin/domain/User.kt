package domain

class User(
    name: String,
    cards: MutableList<Card>,
) : Player(name, cards) {
    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, userNameAndCard.second.toMutableList())
    }
}
