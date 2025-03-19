package blackjack.model

import blackjack.model.CardsStatus.Companion.BLACKJACK_SCORE
import blackjack.model.Dealer.Companion.DEFAULT_DEALER_NAME

class Player(
    name: String,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    override val openCard: List<Card>
        get() = hand.value
    private lateinit var bettingMoney: BettingMoney

    init {
        require(name != DEFAULT_DEALER_NAME) { "플레이어는 ${DEFAULT_DEALER_NAME}라는 이름을 가질 수 없습니다." }
        require(name.length in MIN_NAME_SIZE..MAX_NAME_SIZE) { "플레이어는 ${MIN_NAME_SIZE}에서 ${MAX_NAME_SIZE}사이 길이의 이름만 가질 수 있습니다." }
    }

    fun updateBettingMoney(money: BettingMoney) {
        bettingMoney = money
    }

    override fun canHit(): Boolean = getScore() < BLACKJACK_SCORE && !hand.isBlackjack() && !hand.isBust()

    fun calculateProfit(dealerHand: Hand): Profit {
        val result = hand.gameResult(dealerHand)
        return bettingMoney.profit(result)
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
