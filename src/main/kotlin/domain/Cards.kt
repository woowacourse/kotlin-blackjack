package domain


class Cards(val cards: List<TrumpCard>) {
    fun getTotalScore(): Int {
        return cards.map { it.getScore() }.sum()
    }

}
