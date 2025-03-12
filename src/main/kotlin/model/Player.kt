package model

class Player(val name: String, private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getScore() <= GameResultDecider.BLACKJACK_SCORE
}
