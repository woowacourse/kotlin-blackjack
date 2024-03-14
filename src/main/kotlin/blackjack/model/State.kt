package blackjack.model

sealed class State(open val hand: Hand) {
    sealed class Progressing(hand: Hand) : State(hand)
    sealed class Finished(hand: Hand) : State(hand)
}
