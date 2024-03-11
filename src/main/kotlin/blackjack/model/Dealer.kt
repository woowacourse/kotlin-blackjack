package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {

    override fun isHitable(): Boolean {
        val score = getCardSum()
        val threshold = 17
        return score < threshold
    }
}
