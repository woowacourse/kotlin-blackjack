package blackjack.domain

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val result: List<Outcome>
        get() = participants.users.map { user -> Outcome.of(participants.dealer, user) }

    val dealer: User
        get() = participants.dealer

    val users: List<User>
        get() = participants.users
}
