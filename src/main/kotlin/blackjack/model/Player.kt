package blackjack.model

class Player(name: String) : Participant(name) {

    override fun isHitable(): Boolean {
        val score = getCardSum()
        val threshold = 21
        return score < threshold
    }
}
