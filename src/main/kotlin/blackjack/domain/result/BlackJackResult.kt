package blackjack.domain.result

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants
import blackjack.domain.result.Outcome.Companion.calculateGuestWin

class BlackJackResult(dealer: Dealer, guests: List<Guest>) {
    val guests: List<BlackJackResultUser> = guests.map { guest ->
        BlackJackResultUser(guest.name, calculateGuestRateOfReturn(guest, dealer))
    }

    val dealer = BlackJackResultUser(dealer.name, calculateDealerRateOfReturn())

    constructor(participants: Participants) : this(participants.dealer, participants.guests)

    private fun calculateDealerRateOfReturn(): Int = guests.sumOf { -it.rateOfReturn }

    private fun calculateGuestRateOfReturn(guest: Guest, dealer: Dealer): Int =
        (calculateGuestWin(guest, dealer).rate * guest.bettingMoney.value).toInt()
}
