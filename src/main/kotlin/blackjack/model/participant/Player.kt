package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.participant.state.Finish
import blackjack.model.participant.state.Gaming

class Player(val name: String) : GameParticipant() {
    fun playTurn(
        cards: (Int) -> List<Card>,
        isHit: (String) -> Boolean,
        showResult: (Player) -> Unit,
    ): Finish {
        while (handCards.state is Gaming) {
            handCards.playTurn(isHit(name), cards)
            showResult(this)
        }
        return handCards.state as Finish
    }
}
