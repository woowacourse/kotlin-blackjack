package blackjack.model.participant

enum class GameResult {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun from(compared: Int): GameResult {
            return when {
                compared == 0 -> DRAW
                compared > 0 -> WIN
                compared < 0 -> LOSE
                else -> throw IllegalArgumentException("예상치 못한 값 $GameResult 이 들어옴")
            }
        }
    }
}
