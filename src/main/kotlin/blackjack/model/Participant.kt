package blackjack.model

import blackjack.controller.BlackjackController.Companion.INITIAL_DISTRIBUTE_COUNT

sealed class Participant(val name: ParticipantName, hand: Hand) {
    var state =
        when (hand.sumUpCardValues()) {
            State.THRESHOLD_BLACKJACK -> Blackjack(hand)
            else -> Hit(hand)
        }
        private set

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun finishRound() {
        state = state.stay()
    }

    fun getCards(): List<Card> = state.hand().cards

    fun getSumOfCards(): Int = state.hand().sumUpCardValues()

    fun getWinningResult(opponent: Participant): WinningResult = state.calculateWinningResult(opponent)
}

class Player(name: ParticipantName, hand: Hand, private val betAmount: BetAmount) : Participant(name, hand) {
    fun playRound(
        cardDeck: CardDeck,
        isPlayerActionContinued: (name: ParticipantName) -> Boolean,
        updatePlayerStatus: (player: Player) -> Unit,
    ) {
        while (state is Running) {
            if (isPlayerActionContinued(name)) {
                receiveCard(cardDeck.pick())
            } else {
                finishRound()
            }
            updatePlayerStatus(this)
        }
    }

    fun calculateProfit(winningResult: WinningResult): Double {
        return when (winningResult) {
            WinningResult.WIN -> betAmount * state.getEarningRate()
            WinningResult.DRAW -> 0.0
            WinningResult.LOSE -> -betAmount
        }
    }
}

class Dealer(name: ParticipantName = ParticipantName(DEALER_NAME), hand: Hand) : Participant(name, hand) {
    fun playRound(
        cardDeck: CardDeck,
        updateDealerStatus: (dealer: Dealer) -> Unit,
    ) {
        while (state is Running) {
            if (isUnderHitThreshold()) {
                receiveCard(cardDeck.pick())
            } else {
                finishRound()
            }
        }
        if (countAdditionalDrawnCards() > 0) {
            updateDealerStatus(this)
        }
    }

    fun countAdditionalDrawnCards(): Int = getCards().size - INITIAL_DISTRIBUTE_COUNT

    private fun isUnderHitThreshold(threshold: Int = THRESHOLD_HIT): Boolean = state.hand().sumUpCardValues() <= threshold

    companion object {
        const val THRESHOLD_HIT = 16
        const val DEALER_NAME = "딜러"
    }
}
