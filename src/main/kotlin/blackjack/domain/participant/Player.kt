package blackjack.domain.participant

class Player(val name: String, val bettingAmount: Int = 0) : Participant() {
    init {
        require(name.isNotEmpty()) { INVALID_NAME }
        require(bettingAmount > 0) { INVALID_BETTING_AMOUNT }
    }

    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    companion object {
        const val PLAYER_HIT_THRESHOLD = 21
        const val INVALID_NAME = "이름은 1자 이상 입력해주세요"
        const val INVALID_BETTING_AMOUNT = "최소 1원 이상 베팅해주세요"
    }
}
