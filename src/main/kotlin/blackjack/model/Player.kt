package blackjack.model

import blackjack.model.CardsStatus.Companion.BLACKJACK_SCORE

class Player(
    name: String,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    lateinit var bettingMoney: BettingMoney

    init {
        require(name != "딜러") { "플레이어는 딜러라는 이름을 가질 수 없습니다." }
        require(name.length in 1..5) { "플레이어는 1에서 5사이 길이의 이름만 가질 수 있습니다." }
    }

    fun getBettingMoney(money: BettingMoney) {
        bettingMoney = money
    }

    override fun canHit(): Boolean = getScore() < BLACKJACK_SCORE && !isBlackjack() && !isBust()

    override fun gainMoney(money: Money) {
        bettingMoney.plus(money)
    }

    override fun lossMoney(money: Money) {
        bettingMoney.minus(money)
    }

    enum class Behavior {
        HIT,
        STAY,
    }
}
