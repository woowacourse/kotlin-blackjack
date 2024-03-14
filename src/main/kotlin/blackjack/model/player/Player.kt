import blackjack.model.card.Hand
import blackjack.model.game.BettingMoney
import blackjack.model.game.Result
import blackjack.model.game.State

class Player(val name: String, val hand: Hand, val bettingMoney: BettingMoney = BettingMoney()) {
    var profit: Int = 0

    fun updateProfit(result: Result) {
        when (result) {
            Result.PLAYER_WIN -> profit += bettingMoney.bettingMoney
            Result.DEALER_WIN -> profit -= bettingMoney.bettingMoney
            Result.DRAW -> {} // 무승부의 경우, 수익 변동 없음
        }
    }

    val state: State
        get() =
            when {
                hand.isBlackjack() -> State.Finished.BlackJack
                hand.isBust() -> State.Finished.Bust
                hand.isStay() -> State.Finished.Stay
                else -> State.Running.Hit
            }
}
