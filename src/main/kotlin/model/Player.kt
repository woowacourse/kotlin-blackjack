package model

class Player(hand: Hand, name: Name) : Participant(hand, name) {
    override fun isHit(): Boolean = !isBust()

    fun judge(dealer: Dealer): Result {
        val playerPoint = hand.sum()
        val dealerPoint = dealer.hand.sum()

        if (isBust()) Result.LOSE // 플레이어 버스트
        if (dealer.isBust()) return Result.WIN // 딜러 버스트
        if (isBlackJack() && dealer.isBlackJack()) return Result.PUSH // 둘 다 블랙잭
        if (isBlackJack()) return Result.WIN // 플레이어만 블랙잭
        if (playerPoint == dealerPoint) return Result.PUSH // 점수 같음
        if (playerPoint > dealerPoint) return Result.WIN
        return Result.LOSE
    }
}
