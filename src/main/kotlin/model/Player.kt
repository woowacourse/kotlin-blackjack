package model

class Player(hand: Hand, name: Name) : Participant(hand, name) {
    override fun isHit(): Boolean = !isBust() && (hand.sum() != BLACKJACK_POINT)

    fun judge(dealer: Dealer): FinalResult {
        val playerPoint = hand.sum()
        val dealerPoint = dealer.hand.sum()

        return when {
            isBust() -> FinalResult.LOSE // 플레이어 버스트
            dealer.isBust() -> FinalResult.WIN // 딜러 버스트
            isBlackJack() && dealer.isBlackJack() -> FinalResult.PUSH // 둘 다 블랙잭
            isBlackJack() -> FinalResult.BLACKJACK_WIN // 플레이어만 블랙잭
            dealer.isBlackJack() -> FinalResult.LOSE // 딜러만 블랙잭
            playerPoint == dealerPoint -> FinalResult.PUSH // 딜러, 플레이어 점수 같음
            playerPoint > dealerPoint -> FinalResult.WIN // 플레이어가 더 점수가 큼
            else -> FinalResult.LOSE
        }
    }
}
