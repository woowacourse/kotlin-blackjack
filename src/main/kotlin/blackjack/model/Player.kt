package blackjack.model

class Player(
    name: String,
    cards: Cards = Cards(emptyList()),
) : Participant(name, cards) {
    lateinit var bettingMoney: BettingMoney

    init {
        require(name != "딜러") { "플레이어는 딜러라는 이름을 가질 수 없습니다." }
        require(name.length in 1..5) { "플레이어는 1에서 5사이 길이의 이름만 가질 수 있습니다." }
    }

    fun getBettingMoney(money: BettingMoney) {
        bettingMoney = money
    }

    fun getPlayerScore(): Int = cards.calculateScore()

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
