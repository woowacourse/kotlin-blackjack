package model

class Player(override val hand: Hand, val name: String = "player") : Human(hand) {
    init {
        require(name.length in 1..10) { ERROR_EXCEED_NAME_LENGTH }
    }

    override fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return true
        }
        return false
    }

    override fun isPossible(): Boolean =
        getPointIncludingAce().amount < 21


    companion object {
        const val ERROR_EXCEED_NAME_LENGTH = "이름의 길이가 초과되었습니다."
    }
}