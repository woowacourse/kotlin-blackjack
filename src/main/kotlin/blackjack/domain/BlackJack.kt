package blackjack.domain

import blackjack.domain.Outcome.Companion.winTo

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val result: List<Outcome>
        get() = participants.guests.map { guest -> guest.winTo(participants.dealer) }

    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests
}
