package blackjack.model.state

enum class CardDrawDecision {
    YES,
    NO,
    ;

    fun isDraw(): Boolean = this == YES
}
