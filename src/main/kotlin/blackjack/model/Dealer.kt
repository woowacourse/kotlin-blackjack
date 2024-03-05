package blackjack.model

class Dealer(name: String = "딜러", cards: MutableList<Card>) : CardHolder(name, cards) {
    fun shouldPick(threshold: Int = THRESHOLD_PICK_CARD): Boolean = calculateSum() <= threshold

    companion object {
        const val THRESHOLD_PICK_CARD = 16
    }
}
