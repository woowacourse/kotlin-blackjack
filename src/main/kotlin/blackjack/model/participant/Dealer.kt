package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.Deck
import blackjack.model.participant.state.Finish

class Dealer private constructor() : GameParticipant() {
    fun getFirstCard() = handCards.cards.first()

    fun playTurn(
        cards: (Int) -> List<Card>,
        showResult: () -> Unit,
    ) {
        while (getScore() < DEALER_HIT_THRESHOLD) {
            handCards.playTurn(true, cards)
            showResult.invoke()
        }
    }

    fun gameResult(playersState: Map<Player, Finish>): Map<String, Double> {
        val result = mutableMapOf<String, Double>()
        playersState.entries.forEach { (player, finish) ->
            result[player.name] =
                finish.getProfit(player.getScore(), handCards.calculateScore(), player.battingMoney).amount
        }
        return result
    }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17

        fun withInitCards(deck: Deck): Dealer {
            return Dealer().also { it.initCards(deck.draw(INIT_CARD_AMOUNT)) }
        }
    }
}
