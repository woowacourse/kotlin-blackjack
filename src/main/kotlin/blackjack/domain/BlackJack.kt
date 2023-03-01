package blackjack.domain

data class BlackJack(
    val dealer: User,
    val users: List<User>,
    val cardDeck: CardDeck,
) {
    val result: List<Outcome>
        get() = users.map { user -> Outcome.of(dealer, user) }
}
