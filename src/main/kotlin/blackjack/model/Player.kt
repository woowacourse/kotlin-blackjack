package blackjack.model

class Player(name: String) : Participant(name) {
    override fun isHitable(): Boolean {
        return getCardSum() < MAX_SCORE
    }
}
