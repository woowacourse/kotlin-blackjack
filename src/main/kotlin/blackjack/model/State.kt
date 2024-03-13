package blackjack.model

sealed interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun hand(): Hand

    fun calculateWinningResult(opponent: Participant): WinningResult

    fun getEarningRate(): Double

    companion object {
        const val THRESHOLD_BUST = 21
        const val THRESHOLD_BLACKJACK = 21
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
            else -> Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)

    override fun calculateWinningResult(opponent: Participant): WinningResult = WinningResult.DRAW

    override fun getEarningRate(): Double = 0.0
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

    override fun getEarningRate(): Double = 1.0
}

class Blackjack(hand: Hand) : Finished(hand) {
    override fun calculateWinningResult(opponent: Participant): WinningResult {
        return when (opponent.state) {
            is Blackjack -> WinningResult.DRAW
            else -> WinningResult.WIN
        }
    }

    override fun getEarningRate(): Double = 1.5
}

class Bust(hand: Hand) : Finished(hand) {
    override fun calculateWinningResult(opponent: Participant): WinningResult {
        return when (opponent.state) {
            is Bust -> if (opponent is Dealer) WinningResult.LOSE else WinningResult.WIN
            else -> WinningResult.LOSE
        }
    }

    override fun getEarningRate(): Double = 0.0
}
