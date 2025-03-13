package blackjack.model.participant

@JvmInline
value class Name(
    private val value: String,
) {
    init {
        require(value.isNotBlank()) {
            "[ERROR] 닉네임은 공백일 수 없습니다."
        }
    }

    override fun toString(): String = value
}
