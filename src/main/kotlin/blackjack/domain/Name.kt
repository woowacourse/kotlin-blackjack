package blackjack.domain

@JvmInline
value class Name(private val value: String) {
    init {
        require(value.length <= NAME_LENGTH_MAX) { ERROR_NAME_LENGTH_MAX }
    }

    override fun toString(): String = value

    companion object {
        private const val NAME_LENGTH_MAX = 20
        private const val ERROR_NAME_LENGTH_MAX = "이름의 최대 글자수는 ${NAME_LENGTH_MAX}글자 입니다."
    }
}
