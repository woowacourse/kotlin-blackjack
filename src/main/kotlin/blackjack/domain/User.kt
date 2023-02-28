package blackjack.domain

class User(val name: String) {
    val cards = Cards()
    val score: Int
        get() {
            var score = cards.toList().sumOf { it.value.value }
            if (score > 21 && cards.toList().filter { it.value == CardValue.ACE }.isNotEmpty()) {
                score -= 10
            }
            return score
        }

    fun draw(card: Card) {
        cards.add(card)
    }
}
