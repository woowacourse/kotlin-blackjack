package blackjack.domain

class Blackjack(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun start() {
        dealer.getCard()
        dealer.giveCard()
        dealer.giveCard()
    }

    fun waitForPlayers() {
        players.forEach { player ->
            player.hitOrStay(hit = {
                dealer.giveCard(player)
            })
        }

        dealer.hitOrStay()
    }

    fun finish() {
        players.forEach { player ->
            if (player.playerState != PlayerState.PLAYING) return@forEach
            val playerScore: Int? = player.getScore()
            if (playerScore == null) {
                player.playerState = PlayerState.LOSE
                return@forEach
            }
        }

        val dealerScore: Int? = dealer.getScore()
        if (dealerScore == null) {
            val remainingPlayers = players.filter { player -> player.playerState == PlayerState.PLAYING }
            remainingPlayers.forEach { player -> player.playerState = PlayerState.WIN }
        }

        val remainingPlayers = players.filter { player -> player.playerState == PlayerState.PLAYING }
        remainingPlayers.forEach { player ->
            when {
                (player.getScore() ?: 0) > (dealerScore ?: 0) -> {
                    player.playerState = PlayerState.WIN
                }

                (player.getScore() ?: 0) < (dealerScore ?: 0) -> {
                    player.playerState = PlayerState.LOSE
                }

                else -> player.playerState = PlayerState.DRAW
            }
        }
    }
}
