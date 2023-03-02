package blackjack.domain

open class Player(val name: String) {

    init {
        require(name.length in 2..10) { ERROR_NAME_LENGTH }
    }

    companion object {
        const val ERROR_NAME_LENGTH = "이름은 2글자 이상 10글자 이하여야 합니다."
    }
}
