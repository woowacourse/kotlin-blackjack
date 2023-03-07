package model

class Player(hand: Hand, name: Name) : Participant(hand, name) {
    override fun isHit(): Boolean = !isBust() && (hand.sum() != BLACKJACK_POINT)

    fun judge(dealer: Dealer): FinalResult {
        val playerPoint = hand.sum()
        val dealerPoint = dealer.hand.sum()

        if (isBust()) return FinalResult.LOSE // 플레이어 버스트
        if (dealer.isBust()) return FinalResult.WIN // 딜러 버스트
        if (isBlackJack() && dealer.isBlackJack()) return FinalResult.PUSH // 둘 다 블랙잭
        if (isBlackJack()) return FinalResult.BLACKJACK_WIN // 플레이어만 블랙잭
        if (playerPoint == dealerPoint) return FinalResult.PUSH // 딜러, 플레이어 점수 같음
        if (playerPoint > dealerPoint) return FinalResult.WIN // 플레이어가 더 점수가 큼
        return FinalResult.LOSE
    }
}
