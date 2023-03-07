package domain

class GameResult(val participants: Participants) {
    fun getPlayersGameResult(): Map<Name, GameResultType> {
        return participants.players.list.associate { player ->
            Pair(player.name, player.getGameResult(participants.dealer.getScore()))
        }
    }

    fun getDealerGameResult(): Map<GameResultType, Int> {
        val playersGameResult = getPlayersGameResult()
        return GameResultType.values()
            .associateWith { gameResultType -> playersGameResult.values.count { changeType(it) == gameResultType } }
    }

    private fun changeType(gameResultType: GameResultType): GameResultType {
        return when (gameResultType) {
            GameResultType.LOSE -> GameResultType.WIN
            GameResultType.WIN -> GameResultType.LOSE
            else -> gameResultType
        }
    }
}
