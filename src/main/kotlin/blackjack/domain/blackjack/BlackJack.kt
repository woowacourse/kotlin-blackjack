package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants
import blackjack.domain.result.Outcome
import blackjack.domain.result.Outcome.Companion.calculateGuestWin

data class BlackJack(
    val cardDeck: CardDeck,
    val participants: Participants,
) {
    val dealer: Dealer
        get() = participants.dealer

    val guests: List<Guest>
        get() = participants.guests

    fun getResult(): List<Outcome> = participants.guests.map { guest -> calculateGuestWin(guest, participants.dealer) }
}
