package blackjack.domain

class ParticipantsBuilder {

    private lateinit var dealer: Dealer
    private lateinit var guests: List<Guest>
    fun dealer(name: String) {
        dealer = Dealer(name)
    }

    fun guests(names: List<String>) {
        guests = names.map { Guest(it) }
    }

    fun build(): Participants {
        return Participants(dealer, guests)
    }

    companion object {
        fun init(block: ParticipantsBuilder.() -> Unit): Participants =
            ParticipantsBuilder().apply(block).build()
    }
}
