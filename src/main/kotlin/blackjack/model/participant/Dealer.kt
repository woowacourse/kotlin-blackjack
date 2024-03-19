package blackjack.model.participant

import blackjack.model.Result
import blackjack.model.deck.Card

class Dealer : GameParticipant() {
    fun getFirstCard() = handCards.cards.first()

    fun playTurn(
        cards: (Int) -> List<Card>,
        playResult: () -> Unit,
    ) {
        while (getScore() < DEALER_HIT_THRESHOLD) {
            handCards.playTurn(true, cards)
            playResult.invoke()
        }
    }

    fun getGameResult(playersState: List<Result>): Map<String, Double> {
        return playersState.associate { (player, finish) ->
            player.name to finish.getProfit(player.getScore(), handCards.calculateScore(), player.battingMoney).amount
        }
    }

    companion object {
        private const val INIT_CARD_AMOUNT = 2
        private const val DEALER_HIT_THRESHOLD = 17

        fun withInitCards(cards: (Int) -> List<Card>): Dealer = Dealer().also { it.initCards(cards(INIT_CARD_AMOUNT)) }
    }
}
