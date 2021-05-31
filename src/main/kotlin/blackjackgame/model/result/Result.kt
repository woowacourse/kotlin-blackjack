package blackjackgame.model.result


val WIN = Result(1, 0, 0)
val LOSE = Result(0, 1, 0)
val DRAW = Result(0, 0, 1)

data class Result(val win: Int, val lose: Int, val draw: Int) {
    operator fun plus(other: Result): Result = Result(win + other.win, lose + other.lose, draw + other.draw)

    fun reverse(): Result {
        return Result(this.lose, this.win, this.draw)
    }
}