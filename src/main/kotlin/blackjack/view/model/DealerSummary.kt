package blackjack.view.model

class DealerSummary(
    val dealerCards: List<String>,
    val dealerScore: Int,
) {
    override fun toString(): String = "\n딜러 카드: ${dealerCards.joinToString()} - 결과: $dealerScore"
}
