data class Deck(private var deck: List<Card>) {
    fun spreadCard(): Card {
        return deck.toMutableList().removeFirst()
    }
}
