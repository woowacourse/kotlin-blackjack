package blackjack.model

class Player(name: String, cards: MutableList<Card>) : CardHolder(name, cards) {
    fun canPickCard(threshold: Int = THRESHOLD_PICK_CARD): Boolean = calculateSum() < threshold

    companion object {
        const val THRESHOLD_PICK_CARD = 21
    }
}
