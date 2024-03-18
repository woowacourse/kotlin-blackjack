package blackjack.model

sealed interface GameResult {
    data object BlackjackWin : GameResult

    data object Win : GameResult

    data object Lose : GameResult

    data object Draw : GameResult
}
