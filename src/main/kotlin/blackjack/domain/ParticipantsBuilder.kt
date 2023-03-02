package blackjack.domain

object ParticipantsBuilder {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    fun dealer() {
        dealer = Dealer()
    }

    fun players(names: List<String>) {
        players = Players(names.map { Player(it) })
    }

    private fun build(): Participants = Participants(dealer, players)

    operator fun invoke(block: ParticipantsBuilder.() -> Unit): Participants =
        ParticipantsBuilder.apply(block).build()
}
