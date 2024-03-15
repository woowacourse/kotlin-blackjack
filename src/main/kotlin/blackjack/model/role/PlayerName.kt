package blackjack.model.role

@JvmInline
value class PlayerName(val name: String) {
    init {
        require(name.length in NAME_LENGTH_RANGE) { "이름은 ${MIN_NAME_LENGTH}자 이상 ${MAX_NAME_LENGTH}자 이하여야 합니다." }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 5

        private val NAME_LENGTH_RANGE: IntRange = MIN_NAME_LENGTH..MAX_NAME_LENGTH
    }
}
