package blackjack.model

data class PlayerName(private val name: String) {
    init {
        require(name.length in LENGTH_RANGE) { INVALID_NAME_LENGTH }
        require(NAME_SMALL_LETTER.toRegex().matches(name)) { INVALID_NAME_MESSAGE }
    }

    override fun toString() = name

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 5
        private val LENGTH_RANGE = MIN_LENGTH..MAX_LENGTH
        private const val NAME_SMALL_LETTER = "[a-z]+"
        private const val INVALID_NAME_LENGTH = "이름은 ${MIN_LENGTH}~${MAX_LENGTH}글자 사이여야 합니다."
        private const val INVALID_NAME_MESSAGE = "이름은 소문자로 구성되어야 합니다."
    }
}
