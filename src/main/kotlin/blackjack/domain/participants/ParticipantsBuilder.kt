package blackjack.domain.participants

class ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private val guests = MutableList(0) { Guest("") }

    fun dealer() { dealer = Dealer() }

    fun guest(name: String, bettingMoney: Int) {
        guests += Guest(name, bettingMoney)
    }

    fun build(): Participants = Participants(dealer, guests)
}
