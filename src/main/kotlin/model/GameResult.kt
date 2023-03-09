package model

class GameResult(private val participants: Participants) {
    val winCount: Int
        get() = getResult(Result.WIN)
    val loseCount: Int
        get() = getResult(Result.LOSE)
    private fun getResult(winOrLose: Result): Int {
        val res = participants.players.count { participants.dealer.getGameResult(it) == winOrLose }
        return res
    }
}
