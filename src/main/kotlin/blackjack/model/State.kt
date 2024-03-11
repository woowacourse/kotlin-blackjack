package blackjack.model

sealed interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun hand(): Hand

    fun calculateWinningResult(opponent: Participant): WinningResult

    companion object {
        const val THRESHOLD_BUST = 21
        private const val THRESHOLD_BLACKJACK = 21

        fun determineInitialGameState(
            hand: Hand,
            threshold: Int = THRESHOLD_BLACKJACK,
        ): State {
            return when (hand.sumUpCardValues()) {
                threshold -> Blackjack(hand)
                else -> Hit(hand)
            }
        }
    }
}

sealed class Running(private val hand: Hand) : State {
    override fun hand(): Hand = hand
}

sealed class Finished(private val hand: Hand) : State {
    override fun draw(card: Card): State = this

    override fun stay(): State = this

    override fun hand(): Hand = hand
}

class Hit(private val hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        val sumOfCard = hand.sumUpCardValues()
        return when {
            sumOfCard > State.THRESHOLD_BUST -> Bust(hand)
            sumOfCard == State.THRESHOLD_BUST -> Stay(hand)
            else -> Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)

    override fun calculateWinningResult(opponent: Participant): WinningResult = WinningResult.DRAW
}

class Stay(hand: Hand) : Finished(hand) {
    override fun calculateWinningResult(opponent: Participant): WinningResult {
        val myScore = hand().sumUpCardValues()
        val opponentScore = opponent.getSumOfCards()
        return when {
            opponent.state is Bust || myScore > opponentScore -> WinningResult.WIN
            myScore < opponentScore -> WinningResult.LOSE
            else -> WinningResult.DRAW
        }
    }
}

class Blackjack(hand: Hand) : Finished(hand) {
    override fun calculateWinningResult(opponent: Participant): WinningResult {
        return when (opponent.state) {
            is Blackjack -> WinningResult.DRAW
            else -> WinningResult.WIN
        }
    }
}

class Bust(hand: Hand) : Finished(hand) {
    override fun calculateWinningResult(opponent: Participant): WinningResult {
        return when (opponent.state) {
            is Bust -> if (opponent is Dealer) WinningResult.LOSE else WinningResult.WIN
            else -> WinningResult.LOSE
        }
    }
}
