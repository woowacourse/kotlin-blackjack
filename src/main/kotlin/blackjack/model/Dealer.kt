package blackjack.model

class Dealer(val handCards: HandCards) {
    private val _scoreBoard: MutableMap<State, Int> = mutableMapOf()
    val scoreBoard: Map<State, Int> = _scoreBoard.toMap()

    // 상태 : 손패, (승 : 0, 패 : 0, 무슨부 : 0 )
    // 행동 : isWin(플레이어와 승 패를 결정함), 기준(17점)으로 Stay, Hit 결정
}
