package blackjack.model

import blackjack.model.BettingMoney.Companion.BLACKJACK_MULTIPLE
import blackjack.model.CardsStatus.Companion.BLACKJACK_SCORE

class Player(
    name: String,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    lateinit var bettingMoney: BettingMoney

    override val money: Money get() = bettingMoney

    init {
        require(name != "딜러") { "플레이어는 딜러라는 이름을 가질 수 없습니다." }
        require(name.length in MIN_NAME_SIZE..MAX_NAME_SIZE) { "플레이어는 ${MIN_NAME_SIZE}에서 ${MAX_NAME_SIZE}사이 길이의 이름만 가질 수 있습니다." }
    }

    fun updateBettingMoney(money: BettingMoney) {
        bettingMoney = money
    }

    override fun canHit(): Boolean = getScore() < BLACKJACK_SCORE && !isBlackjack() && !isBust()

    override fun updateProfit(opponent: Participant) {
        when {
            isBlackjack() && opponent.isBlackjack().not() -> bettingMoney.multiple(BLACKJACK_MULTIPLE)
            isBust() -> bettingMoney.minus(bettingMoney)
            opponent.isBust() -> bettingMoney.plus(bettingMoney)
            getScore() > opponent.getScore() -> bettingMoney.plus(bettingMoney)
            getScore() < opponent.getScore() -> bettingMoney.minus(bettingMoney)
        }
    }

    enum class Behavior {
        HIT,
        STAY,
    }

    companion object {
        private const val MIN_NAME_SIZE = 1
        private const val MAX_NAME_SIZE = 5
    }
}
