package blackjack.domain.participants

import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Money
import blackjack.domain.participants.user.Name
import blackjack.domain.state.inTurn.FirstTurn

class ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private val guests = MutableList(0) { Guest(Name(""), FirstTurn()) }

    fun dealer() { dealer = Dealer() }

    fun guest(name: Name, bettingMoney: Money = Money(10)) {
        guests += Guest(name, FirstTurn(), bettingMoney)
    }

    fun build(): Participants = Participants(dealer, guests)
}
