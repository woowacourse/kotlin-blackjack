package domain

class User(
    name: String,
    _cards: MutableList<Card>,
) : Player(name, _cards) {
    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, userNameAndCard.second.toMutableList())
    }
}
