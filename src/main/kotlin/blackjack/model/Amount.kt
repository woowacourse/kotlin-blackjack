package blackjack.model

class Amount(val value: Int) {
    init {
        require(value >= 0) { IllegalArgumentException("[ERROR] 금액이 음수입니다") }
    }
}
