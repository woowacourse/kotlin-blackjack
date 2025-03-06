package blackjack.view

import blackjack.model.domain.Participants

class OutputView {
    fun printCardStatus(
        dealer: Participants,
        players: List<Participants>,
    ) {
        val playerName = players.joinToString(", ") { it.name }
        println(OUTPUT_DISTRIBUTE_CARD.format(dealer.name, playerName))
        printInitCard(listOf(dealer) + players)
    }

    private fun printInitCard(players: List<Participants>) {
        players.forEach { player ->
            val card = player.initGet()
            println(PLAYER_STATUS.format(player.name + CARD, card.joinToString(", ") { "${it.cardNumber.display}${it.symbol.symbol}" }))
        }
    }

    companion object {
        private const val OUTPUT_DISTRIBUTE_CARD: String = "%s와 %s에게 2장의 나누었습니다."

        private const val PLAYER_STATUS: String = "%s: %s"
        private const val CARD = "카드"
    }
}
