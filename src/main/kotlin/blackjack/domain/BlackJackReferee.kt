package blackjack.domain

class BlackJackReferee {

    private fun judgePlayerGameResults(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cards.getTotalCardsScore(), dealer.cards.getTotalCardsScore())
        )
    }

    private fun judgeDealerGameResults(playerGameResults: List<PlayerGameResult>) = playerGameResults.map { playerGameResult ->
        !playerGameResult.gameResult
    }

    fun judgeTotalGameResults(players: List<Player>, dealer: Dealer): TotalGameResult {
        val playersGameResult = judgePlayerGameResults(players, dealer)
        val dealerGameResult = judgeDealerGameResults(playersGameResult)

        return TotalGameResult(
            playersGameResult = playersGameResult,
            dealerGameResults = dealerGameResult
        )
    }
}
