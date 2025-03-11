package model

class Player(val name: String, private val hand: Hand) : Participant(hand) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
    }

    override fun performTurn(cardDistributor: CardDistributor): Boolean {
        return decideToHit().also {
            if (it) {
                val drawnCard = cardDistributor.drawCard()
                addCard(drawnCard)
            }
        }
    }

    override fun decideToHit(): Boolean = getScore() <= GameResultDecider.BLACKJACK_SCORE

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
