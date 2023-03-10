package blackjack.domain

class ParticipantGenerator(private val drawCard: () -> Card) {

    fun generateDealer(): Dealer = Dealer(makeCardBunch(drawCard))

    fun generatePlayer(name: String): Player = Player(name, makeCardBunch(drawCard))

    private fun makeCardBunch(drawCard: () -> Card): CardBunch = CardBunch(drawCard(), drawCard())
}
