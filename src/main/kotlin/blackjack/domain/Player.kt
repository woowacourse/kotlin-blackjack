package blackjack.domain

class Player(name: String) : Participant(name) {
    fun canHit(): Boolean = this.getScore() < TARGET_SCORE
}
