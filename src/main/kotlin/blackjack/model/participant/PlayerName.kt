package blackjack.model.participant

data class PlayerName(private val name: String) {
    init {
        require(name.length in LENGTH_RANGE) { invalidNameLengthMessage(name) }
        require(NAME_SMALL_LETTER.toRegex().matches(name)) { invalidNameCompositionMessage(name) }
    }

    override fun toString() = name

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 5
        private val LENGTH_RANGE = MIN_LENGTH..MAX_LENGTH
        private const val NAME_SMALL_LETTER = "[a-z]+"

        private fun invalidNameLengthMessage(name: String) = "이름은 $MIN_LENGTH~${MAX_LENGTH}글자 사이여야 합니다. 현재 입력 값: $name"

        private fun invalidNameCompositionMessage(name: String) = "이름은 소문자로 구성되어야 합니다. 현재 입력 값: $name"
    }
}
