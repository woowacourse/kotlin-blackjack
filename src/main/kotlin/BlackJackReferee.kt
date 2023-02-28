class BlackJackReferee {

    fun judgeGameResult(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name,
            gameResult = GameResult.valueOf(player.cards.getSum(), dealer.cards.getSum())
        )
    }
}
