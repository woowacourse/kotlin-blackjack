package blackjack.domain

class Blackjack(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun start() {
        dealer.draw()
        dealer.pitch()
        dealer.pitch()
    }

    fun waitForPlayers() {
        players.forEach { player ->
        }

        dealer.hitOrStay()
    }

    fun finish() {
        players.forEach { player ->
            if (player.state != ParticipantState.PLAYING) return@forEach
            val playerScore: Int? = player.score
            if (playerScore == null) {
                player.state = ParticipantState.LOSE
                return@forEach
            }
        }

        val remainingPlayers = players.filter { player -> player.state == ParticipantState.PLAYING }
        remainingPlayers.forEach { player ->
            when {
                player.score > dealer.score -> {
                    player.state = ParticipantState.WIN
                }

                player.score < dealer.score -> {
                    player.state = ParticipantState.LOSE
                }

                else -> player.state = ParticipantState.DRAW
            }
        }
    }
}
