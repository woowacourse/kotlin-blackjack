package domain

import model.Participants
import model.Result

class GameResult(private val participants: Participants) {
    val winCount: Int
        get() = getResult(Result.WIN)
    val loseCount: Int
        get() = getResult(Result.LOSE)

    private fun getResult(result: Result): Int {
        return participants.players.count { participants.dealer.getGameResult(it) == result }
    }
}
