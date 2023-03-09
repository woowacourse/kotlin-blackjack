package blackjack.domain.participants

data class Participants(
    val dealer: Dealer = Dealer(),
    val guests: List<Guest> = emptyList(),
) {
    val all: List<User>
        get() = listOf(dealer) + guests
}
