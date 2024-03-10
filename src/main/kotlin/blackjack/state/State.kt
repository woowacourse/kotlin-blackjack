package blackjack.state

sealed interface State {
    sealed interface Running : State {
        data object Hit : Running
    }

    sealed interface Finished : State {
        data object Bust : Finished

        data object Stay : Finished

        data object Blackjack : Finished
    }
}
