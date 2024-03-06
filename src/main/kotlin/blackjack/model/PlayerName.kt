package blackjack.model

data class PlayerName(private val name: String) {
    init {
        require(NAME_RULE.toRegex().matches(name)) { INVALID_NAME_MESSAGE }
    }

    override fun toString() = name

    companion object {
        private const val NAME_RULE = "^[a-z]{1,5}\$"
        private const val INVALID_NAME_MESSAGE = "이름은 다섯 글자 이하의 소문자로 구성되어야 합니다."
    }
}
