package blackjack.domain

import blackjack.domain.state.DealerFirstTurn
import blackjack.domain.state.FirstTurn

class ParticipantGenerator(private val drawCard: () -> Card) {

    fun generateDealer(): Dealer = Dealer(DealerFirstTurn(makeCardBunch(drawCard)).draw(drawCard()))

    fun generatePlayer(name: String): Player = Player(name, FirstTurn(makeCardBunch(drawCard)).draw(drawCard()))

    private fun makeCardBunch(drawCard: () -> Card): CardBunch = CardBunch(drawCard())
}
