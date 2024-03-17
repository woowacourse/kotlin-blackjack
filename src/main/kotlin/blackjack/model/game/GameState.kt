package blackjack.model.game

sealed interface GameState {
    enum class Running : GameState {
        HIT,
    }

    enum class Finished : GameState {
        STAY,
        BUST,
        BLACKJACK,
    }
}
