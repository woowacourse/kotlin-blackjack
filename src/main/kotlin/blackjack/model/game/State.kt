package blackjack.model.game

sealed interface State {
    sealed interface Running : State {
        data object Hit : Running
    }

    sealed interface Finished : State {
        data object Bust : Finished

        data object Stay : Finished

        data object BlackJack : Finished
    }
}
