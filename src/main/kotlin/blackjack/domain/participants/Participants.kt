package blackjack.domain.participants

import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.User

data class Participants(
    val dealer: Dealer = Dealer(),
    val guests: List<Guest> = emptyList(),
) {
    val all: List<User> = listOf(dealer) + guests
}
