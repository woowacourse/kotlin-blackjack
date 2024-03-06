package blackjack.model

sealed class GameState {
    data object Running : GameState()

    class Finished(val state: UserState, val result: Result = Result()) : GameState()
}
