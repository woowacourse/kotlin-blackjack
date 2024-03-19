package blackjack.model.game

class BettingMoney(val bettingMoney: Int = 0) {
    init {
        require(bettingMoney >= 0) { "배팅 금액은 0 이상이어야 합니다." }
    }
}
