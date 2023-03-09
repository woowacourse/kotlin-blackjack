package blackjack.domain.participants

class ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private val guests = MutableList(0) { Guest(Name("")) }

    fun dealer() { dealer = Dealer() }

    fun guest(name: Name, bettingMoney: Money = Money(10)) {
        guests += Guest(name, bettingMoney)
    }

    fun build(): Participants = Participants(dealer, guests)
}
