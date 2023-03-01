package blackjack.domain

data class Participants(
    val dealer: User,
    val users: List<User>,
)
