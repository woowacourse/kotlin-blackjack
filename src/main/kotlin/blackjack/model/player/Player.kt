import blackjack.model.card.Hand
import blackjack.model.game.State

class Player(val name: String, val hand: Hand) {
    var state: State
        get() =
            when {
                hand.isBlackjack() -> State.Finished.BlackJack
                hand.isBust() -> State.Finished.Bust
                else -> State.Running.Hit
            }
}
