package blackjack.model

sealed interface GameState {
    sealed interface Running : GameState {
        data object Hit : Running
    }

    sealed interface Finished : GameState {
        data object Stay : Finished

        data object Bust : Finished

        data object BlackJack : Finished
    }
}
