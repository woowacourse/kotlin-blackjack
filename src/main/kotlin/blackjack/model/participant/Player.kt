package blackjack.model.participant

import blackjack.model.BattingMoney
import blackjack.model.deck.Card
import blackjack.model.participant.state.Finish
import blackjack.model.participant.state.Gaming

class Player private constructor(val name: String, val battingMoney: BattingMoney) : GameParticipant() {
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

    companion object {
        private const val INIT_CARD_AMOUNT = 2

        fun withInitCards(
            name: String,
            battingMoney: BattingMoney,
            cards: (Int) -> List<Card>,
        ): Player = Player(name, battingMoney).also { it.initCards(cards(INIT_CARD_AMOUNT)) }
    }
}
