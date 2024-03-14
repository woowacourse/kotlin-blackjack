package blackjack.model

sealed class State {
    abstract val isFinished: Boolean

    abstract fun calculateWinningState(
        self: Participant,
        opponent: Participant,
    ): WinningState

    abstract fun calculatePayoutMultiplier(
        self: Participant,
        opponent: Participant,
    ): Double

    companion object {
        const val BLACKJACK_WIN_PAYOUT_RATIO = 1.5
        const val WIN_PAYOUT_RATIO = 1.0
        const val TIE_PAYOUT_RATIO = 0.0
        const val LOSE_PAYOUT_RATIO = -1.0
    }
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

    override fun calculatePayoutMultiplier(
        self: Participant,
        opponent: Participant,
    ): Double {
        if (calculateWinningState(self, opponent) == WinningState(1, 0)) return BLACKJACK_WIN_PAYOUT_RATIO
        return TIE_PAYOUT_RATIO
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

    override fun calculatePayoutMultiplier(
        self: Participant,
        opponent: Participant,
    ): Double {
        if (calculateWinningState(self, opponent) == WinningState(1, 0)) return WIN_PAYOUT_RATIO
        return LOSE_PAYOUT_RATIO
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

    override fun calculatePayoutMultiplier(
        self: Participant,
        opponent: Participant,
    ): Double {
        return when (calculateWinningState(self, opponent)) {
            WinningState(1, 0) -> WIN_PAYOUT_RATIO
            WinningState(0, 1) -> LOSE_PAYOUT_RATIO
            else -> TIE_PAYOUT_RATIO
        }
    }
}
