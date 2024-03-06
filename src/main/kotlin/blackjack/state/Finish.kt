package blackjack.state

sealed interface Finish : State {
    data object Bust : Finish
    data object Stay : Finish
    data object BlackJack : Finish
}
