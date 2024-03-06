package blackjack.model

class Player(val name: String, val handCards: HandCards) {
    private val _scoreBoard: MutableMap<State, Int> = mutableMapOf()
    val scoreBoard: Map<State, Int> = _scoreBoard.toMap()

    // 카드를 받는 행동
    fun isBust(): Boolean {
        return true
    }

    fun updateScoreBoard(state: State) {
        _scoreBoard[state] = _scoreBoard[state]!! + 1
    }
}
// 상태 : 이름, 손패
// 상태 : 승, 패, 무승부
// 행동 : Stay, Hit
// 행동 : isBust
