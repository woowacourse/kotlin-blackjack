package blackjack.domain

class User(val name: String) {
    val cards = Cards()
    val score: Int
        get() = if (maxScore <= 21) maxScore else minScore

    val minScore: Int
        get() = cards.toList().sumOf { it.value.value }

    val maxScore: Int
        get() {
            var score = cards.toList().sumOf { it.value.value }
            if (cards.containsACE() && score <= 21 - 10) {
                score += 10
            }
            return score
        }

    fun draw(card: Card) {
        cards.add(card)
    }
}
