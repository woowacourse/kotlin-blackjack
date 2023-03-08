package blackjack.domain.participants

data class Participants(
    val dealer: Dealer = Dealer(),
    val guests: List<Guest> = emptyList(),
) {
    fun all(): List<User> = listOf(dealer) + guests
}
