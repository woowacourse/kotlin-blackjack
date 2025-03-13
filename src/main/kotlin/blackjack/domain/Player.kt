package blackjack.domain

class Player(
    val name: String,
) {
    private val hand: Hand = Hand(emptyList())
    val cards: List<Card>
        get() = hand.value

    var result: Result = Result.NOT_YET

    fun getCard(card: Card) {
        hand.add(card)
    }

    fun getCards(cards: List<Card>) {
        hand.add(cards)
    }

    fun getCountOfCards(): Int = hand.getSize()

    fun wantToHit(): Boolean = hand.getScore() <= 21

//    fun hitOrStay(hit: () -> Unit): Boolean {
//        if (wantToHit) {
//            hit()
//            return true
//        }
//        return false
//    }

    fun canGetCard(): Boolean = getScore() <= 21

    fun setResult() {
        if (!wantToHit()) {
            result = Result.LOSE
        }
    }

    fun getScore() = hand.getScore()
}
