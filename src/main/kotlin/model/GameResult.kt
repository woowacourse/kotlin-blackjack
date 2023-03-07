package model

class GameResult private constructor(val playersFinalResult: Map<String, FinalResult>) {

    fun getDealerWinResult(): Int = playersFinalResult.count { it.value == FinalResult.LOSE }
    fun getDealerLoseResult(): Int = playersFinalResult.count { it.value == FinalResult.WIN || it.value == FinalResult.BLACKJACK_WIN }
    fun getDealerPushResult(): Int = playersFinalResult.count { it.value == FinalResult.PUSH }

    companion object {
        fun of(dealer: Dealer, players: List<Player>): GameResult =
            GameResult(buildMap { players.forEach { put(it.name.value, it.judge(dealer)) } })
    }
}
