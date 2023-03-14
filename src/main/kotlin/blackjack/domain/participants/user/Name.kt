package blackjack.domain.participants.user

@JvmInline
value class Name(private val value: String) {
    init {
        require(value.length in NAME_LENGTH_MIN..NAME_LENGTH_MAX) { ERROR_NAME_LENGTH_MAX }
    }

    override fun toString(): String = value

    companion object {
        private const val NAME_LENGTH_MIN = 1
        private const val NAME_LENGTH_MAX = 20
        private const val ERROR_NAME_LENGTH_MAX = "이름의 글자수는 $NAME_LENGTH_MIN~${NAME_LENGTH_MAX}사이 입니다."
    }
}
