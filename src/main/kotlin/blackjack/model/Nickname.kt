package blackjack.model

@JvmInline
value class Nickname(val name: String) {
    init {
        require(name.length in NAME_RANGE) { INVALID_NAME_LENGTH_ERROR_MESSAGE.format(name) }
    }

    override fun toString(): String = name

    companion object {
        val NAME_RANGE = 1..20
        const val INVALID_NAME_LENGTH_ERROR_MESSAGE = "%s의 길이는 1 에서 20 사이가 아닙니다"
    }
}
