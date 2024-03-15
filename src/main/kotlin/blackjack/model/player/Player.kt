import blackjack.model.card.Hand
import blackjack.model.game.BettingMoney
import blackjack.model.game.State

class Player(val name: String, val hand: Hand, val bettingMoney: BettingMoney = BettingMoney()) {
    var profit: Int = 0

    val state: State
        get() =
            when {
                hand.isBlackjack() -> State.Finished.BlackJack
                hand.isBust() -> State.Finished.Bust
                hand.isStay() -> State.Finished.Stay
                else -> State.Running.Hit
            }
}
