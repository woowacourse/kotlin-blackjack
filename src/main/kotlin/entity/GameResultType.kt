package entity

enum class GameResultType {
    WIN, DRAW, LOSE;

    fun opposite(): GameResultType {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
