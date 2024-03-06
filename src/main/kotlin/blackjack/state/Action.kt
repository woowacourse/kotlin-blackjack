package blackjack.state

sealed interface Action : State {
    data object Hit : Action
}
