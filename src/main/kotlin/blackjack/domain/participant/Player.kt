package blackjack.domain.participant

import blackjack.domain.PlayerResultStatus

class Player(val name: String) : Participant() {
    private var gameStatus: PlayerResultStatus = PlayerResultStatus.DRAW

    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    fun setPlayerStatus(dealer: Dealer): PlayerResultStatus {
        val playerBlackJack = hand.isBlackJack()
        val dealerBlackJack = dealer.hand.isBlackJack()
        val playerBust = hand.isBust()
        val dealerBust = dealer.hand.isBust()
        val playerTotalSum = hand.getTotalSum()
        val dealerTotalSum = dealer.hand.getTotalSum()

        gameStatus =
            when {
                // 블랙잭 판별
                playerBlackJack && !dealerBlackJack -> PlayerResultStatus.PLAYER_WIN
                playerBlackJack && dealerBlackJack -> PlayerResultStatus.DRAW
                dealerBlackJack && !playerBlackJack -> PlayerResultStatus.PLAYER_LOSE

                // 버스트 판별
                playerBust -> PlayerResultStatus.PLAYER_LOSE
                dealerBust -> PlayerResultStatus.PLAYER_WIN

                // 점수 비교
                playerTotalSum > dealerTotalSum -> PlayerResultStatus.PLAYER_WIN
                playerTotalSum < dealerTotalSum -> PlayerResultStatus.PLAYER_LOSE
                else -> PlayerResultStatus.DRAW
            }
        return gameStatus
    }

    companion object {
        const val PLAYER_HIT_THRESHOLD = 21
    }
}
