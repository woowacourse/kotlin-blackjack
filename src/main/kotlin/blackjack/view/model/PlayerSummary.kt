package blackjack.view.model

class PlayerSummary(
    val name: String,
    val cards: List<String>,
    val score: Int,
) {
    override fun toString(): String = "${name}카드: ${cards.joinToString()} - 결과: $score"
}
