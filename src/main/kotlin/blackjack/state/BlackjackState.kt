package blackjack.state

sealed interface BlackjackState {
    sealed interface Running : BlackjackState {
        data object Hit : Running
    }

    sealed interface Finished : BlackjackState {
        data object Bust : Finished

        data object Stay : Finished

        data object Blackjack : Finished
    }
}
