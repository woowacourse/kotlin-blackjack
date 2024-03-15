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
        if (opponent.getParticipantState() is Blackjack) return GameState.Tie
        return GameState.WinWhenBlackjack
    }
}

data object Bust : ParticipantState() {
    override val isFinished = true

    override fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState {
        if (self is Dealer && opponent.getParticipantState() is Bust) return GameState.Win
        return GameState.Lose
    }
}

data object Normal : ParticipantState() {
    override val isFinished = false

    override fun calculateGameState(
        self: Participant,
        opponent: Participant,
    ): GameState {
        return when {
            opponent.getParticipantState() is Bust || self.getCardsSum() > opponent.getCardsSum() -> GameState.Win
            opponent.getParticipantState() is Blackjack || self.getCardsSum() < opponent.getCardsSum() -> GameState.Lose
            else -> GameState.Tie
        }
    }
}
