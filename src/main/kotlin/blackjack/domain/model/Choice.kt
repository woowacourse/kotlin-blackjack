package blackjack.domain.model

enum class Choice {
    YES,
    NO,
    ;

    fun isYes() = this == YES
}
