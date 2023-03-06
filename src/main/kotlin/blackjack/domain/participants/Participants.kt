package blackjack.domain.participants

data class Participants(
    val dealer: Dealer,
    val guests: List<Guest>,
) {
    fun all(): List<User> = listOf(dealer) + guests
}
