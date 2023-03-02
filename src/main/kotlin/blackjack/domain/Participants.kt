package blackjack.domain

data class Participants(
    val dealer: Dealer,
    val guests: List<Guest>,
) {
    fun all() = listOf(dealer) + guests
}
