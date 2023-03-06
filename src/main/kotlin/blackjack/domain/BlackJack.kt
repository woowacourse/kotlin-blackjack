package blackjack.domain

import blackjack.domain.Outcome.Companion.winTo

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests

    fun getResult(): List<Outcome> = participants.guests.map { guest -> guest.winTo(participants.dealer) }
}
