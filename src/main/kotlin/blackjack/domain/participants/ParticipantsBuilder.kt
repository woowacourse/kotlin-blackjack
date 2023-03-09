package blackjack.domain.participants

class ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private val guests = MutableList(0) { Guest("") }

    fun dealer() { dealer = Dealer() }

    fun guest(name: String, bettingMoney: Money = Money(10)) {
        guests += Guest(name, bettingMoney)
    }

    fun build(): Participants = Participants(dealer, guests)
}
