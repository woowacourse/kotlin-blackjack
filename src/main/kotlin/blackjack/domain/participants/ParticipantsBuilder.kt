package blackjack.domain.participants

import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Money
import blackjack.domain.participants.user.Name
import blackjack.domain.state.FirstTurn

class ParticipantsBuilder {
    private var dealer: Dealer = Dealer()
    private val guests = MutableList(0) { Guest() }

    fun dealer() { dealer = Dealer() }

    fun guest(name: Name, bettingMoney: Money = Money(10)) {
        guests += Guest(bettingMoney, name, FirstTurn())
    }

    fun build(): Participants = Participants(dealer, guests)
}
