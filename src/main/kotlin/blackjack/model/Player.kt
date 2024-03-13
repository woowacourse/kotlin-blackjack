package blackjack.model

class Player(name: String) : Participant(name) {

    override fun openInitCards(): List<Card>? {
        return getCards().take(2).ifEmpty { null }
    }

    override fun checkShouldDrawCard(): Boolean {
        return checkHitState()
    }

    companion object {
        const val ERROR_DUPLICATION_NAME = "사용자 이름은 중복이 불가능 합니다."
    }
}
