package blackjack.model

@JvmInline
value class HumanName(val name: String) {
    init {
        require(name.length in NAME_RANGE) { "이름의 길이는 1에서 20 사이여야 합니다" }
    }

    override fun toString(): String = name

    companion object {
        val NAME_RANGE = 1..20
    }
}
