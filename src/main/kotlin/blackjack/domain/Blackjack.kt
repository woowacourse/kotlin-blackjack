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

    fun finish() {
        players.forEach { player ->
            if (player.result != Result.NOT_YET) return@forEach
            val playerScore: Int = player.getScore()
            if (playerScore > 21) {
                player.result = Result.LOSE
                return@forEach
            }
        }

        val dealerScore: Int = dealer.getScore()
        if (dealerScore > 21) {
            val remainingPlayers = players.filter { player -> player.result == Result.NOT_YET }
            remainingPlayers.forEach { player -> player.result = Result.WIN }
        }

        val remainingPlayers = players.filter { player -> player.result == Result.NOT_YET }
        remainingPlayers.forEach { player ->
            when {
                (player.getScore()) > (dealerScore) -> {
                    player.result = Result.WIN
                }

                (player.getScore()) < (dealerScore) -> {
                    player.result = Result.LOSE
                }

                else -> player.result = Result.DRAW
            }
        }
    }
}
