package blackjack.model.card

enum class Suite(val value: String) {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드"), ;

    companion object {
        fun from(value: String): Suite? {
            return entries.find { it.value == value }
        }
    }
}
