package blackjack.state

sealed interface State {
    sealed interface Action : State {
        data object Hit : Action
    }

    sealed interface Finish : State {
        data object Bust : Finish

        data object Stay : Finish

        data object BlackJack : Finish
    }
}
