package blackjack.domain.participant

import blackjack.domain.BetAmount
import blackjack.domain.HandStatus
import blackjack.domain.PlayerResultStatus
import blackjack.domain.card.Card

class Player(val name: String, private val betAmount: BetAmount) : Participant() {
    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    override fun showInitialCards(): List<Card> {
        return hand.getCards().take(PLAYER_INITIAL_CARD_COUNT)
    }

    fun getPlayerStatus(dealer: Dealer): PlayerResultStatus {
        val playerStatus = getStatus()
        val dealerStatus = dealer.getStatus()

        val result =
            when {
                // 블랙잭 판별
                playerStatus == HandStatus.BLACKJACK && dealerStatus != HandStatus.BLACKJACK -> PlayerResultStatus.BLACKJACK_WIN
                playerStatus == HandStatus.BLACKJACK && dealerStatus == HandStatus.BLACKJACK -> PlayerResultStatus.DRAW
                dealerStatus == HandStatus.BLACKJACK -> PlayerResultStatus.PLAYER_LOSE

                // 버스트 판별
                playerStatus == HandStatus.BUST -> PlayerResultStatus.PLAYER_LOSE
                dealerStatus == HandStatus.BUST -> PlayerResultStatus.PLAYER_WIN

                // 점수 비교
                getTotalSum() > dealer.getTotalSum() -> PlayerResultStatus.PLAYER_WIN
                getTotalSum() < dealer.getTotalSum() -> PlayerResultStatus.PLAYER_LOSE
                else -> PlayerResultStatus.DRAW
            }
        betAmount.update(result)
        return result
    }

    fun getBetAmount() = betAmount.getAmount()

    companion object {
        private const val PLAYER_HIT_THRESHOLD = 21
        private const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
