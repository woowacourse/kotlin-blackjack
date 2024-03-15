package blackjack.model

sealed class ParticipantState {
    abstract val isFinished: Boolean

    abstract fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState
}

data object Blackjack : ParticipantState() {
    override val isFinished = true

    override fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState {
        if (opponent.getState() is Blackjack) return Tie
        return WinWhenBlackjack
    }
}

data object Bust : ParticipantState() {
    override val isFinished = true

    override fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState {
        if (self is Dealer && opponent.getState() is Bust) return Win
        return Lose
    }
}

data object Normal : ParticipantState() {
    override val isFinished = false

    override fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState {
        return when {
            opponent.getState() is Bust || self.getCardsSum() > opponent.getCardsSum() -> Win
            opponent.getState() is Blackjack || self.getCardsSum() < opponent.getCardsSum() -> Lose
            else -> Tie
        }
    }
}
