package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.participant.state.Finish

class Dealer : GameParticipant() {
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

    fun getGameResult(playersState: Map<Player, Finish>): Map<String, Double> {
        return playersState.map { (player, finish) ->
            player.name to finish.getProfit(player.getScore(), handCards.calculateScore(), player.battingMoney).amount
        }.toMap()
    }

    companion object {
        private const val INIT_CARD_AMOUNT = 2
        private const val DEALER_HIT_THRESHOLD = 17

        fun withInitCards(cards: (Int) -> List<Card>): Dealer = Dealer().also { it.initCards(cards(INIT_CARD_AMOUNT)) }
    }
}
