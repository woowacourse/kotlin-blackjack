class Dealer(val cards: Cards) {

    fun isPossibleToDraw(score: Int): Boolean {
        if (cards.cards.sumOf { card -> card.number.value } >= score)
            return false

        return true
    }

    fun drawCard() {
        if (isPossibleToDraw(17)) {
            cards.draw()
        }
    }

    fun getSum(): Int {
        val count = cards.cards.count { card -> card.number == CardNumber.A }
        var sum = cards.cards.sumOf { card -> card.number.value }
        repeat(count) {
            sum += 10
            if (sum > 21)
                return sum - 10
        }

        return sum
    }
}
