package blackjack.model

sealed class GameState {
    abstract val winningState: WinningState
    abstract val payoutMultiplier: Double
}

data object WinWhenBlackjack : GameState() {
    override val winningState = WinningState(1, 0)
    override val payoutMultiplier = 1.5
}

data object Win : GameState() {
    override val winningState = WinningState(1, 0)
    override val payoutMultiplier = 1.0
}

data object Tie : GameState() {
    override val winningState = WinningState(0, 0)
    override val payoutMultiplier = 0.0
}

data object Lose : GameState() {
    override val winningState = WinningState(0, 1)
    override val payoutMultiplier = -1.0
}
