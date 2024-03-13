package blackjack.model

sealed class State {
    abstract val isFinished: Boolean

    abstract fun calculateWinningState(
        self: Participant,
        opponent: Participant,
    ): WinningState
}

class Blackjack : State() {
    override val isFinished = true

    override fun calculateWinningState(
        self: Participant,
        opponent: Participant,
    ): WinningState {
        if (opponent.getState() is Blackjack) return WinningState(0, 0)
        return WinningState(1, 0)
    }
}

class Bust : State() {
    override val isFinished = true

    override fun calculateWinningState(
        self: Participant,
        opponent: Participant,
    ): WinningState {
        if (self is Dealer && opponent.getState() is Bust) return WinningState(1, 0)
        return WinningState(0, 1)
    }
}

class Normal : State() {
    override val isFinished = false

    override fun calculateWinningState(
        self: Participant,
        opponent: Participant,
    ): WinningState {
        return when {
            opponent.getState() is Bust || self.getCardsSum() > opponent.getCardsSum() -> WinningState(1, 0)
            opponent.getState() is Blackjack || self.getCardsSum() < opponent.getCardsSum() -> WinningState(0, 1)
            else -> WinningState(0, 0)
        }
    }
}
