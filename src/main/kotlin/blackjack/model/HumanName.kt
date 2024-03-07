package blackjack.model

@JvmInline
value class HumanName(val name: String) {
    override fun toString(): String = name
}
