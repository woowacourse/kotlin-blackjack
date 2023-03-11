package blackjack.domain.participants

class ParticipantsBuilder {
    private var dealer: Dealer = Dealer()
    private var guests: List<Guest> = emptyList()

    fun dealer() { dealer = Dealer() }

    fun guests(names: List<String>) { guests = names.map { Guest(Name(it)) } }

    fun build(): Participants = Participants(dealer, guests)
}
