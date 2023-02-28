enum class GameResult() {
    WIN, LOSE, DRAW;

    companion object {
        // TODO: 결과값 반환 if 문 개선
        fun valueOf(myScore: Int, otherScore: Int): GameResult {
            return if (myScore == otherScore) {
                DRAW
            } else if (myScore > otherScore) {
                WIN
            } else {
                LOSE
            }
        }
    }
}
