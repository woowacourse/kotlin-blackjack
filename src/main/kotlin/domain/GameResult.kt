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
            .associateWith { gameResultType -> playersGameResult.values.count { it == gameResultType } }
    }
}
