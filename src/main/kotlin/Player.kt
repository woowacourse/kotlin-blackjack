class Player(
    val name: String,
    val cards: Cards
) {
    fun isPossibleToDraw(): Boolean {
        if (cards.cards.sumOf { card -> card.number.value } > 21)
            return false

        return true
    }

    fun drawCard() {
        if (isPossibleToDraw()) {
            cards.draw()
        }
    }
}
