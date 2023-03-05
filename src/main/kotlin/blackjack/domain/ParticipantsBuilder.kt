package blackjack.domain

class ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private lateinit var guests: List<Guest>

    fun dealer() { dealer = Dealer() }

    fun guests(names: List<String>) { guests = names.map { Guest(it) } }

    fun build(): Participants = Participants(dealer, guests)
}
