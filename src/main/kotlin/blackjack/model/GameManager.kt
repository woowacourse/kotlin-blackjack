package blackjack.model

import blackjack.model.card.Deck

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val deck = Deck.create()

    fun distributeInitialCardWithCount(count: Int) {
        distributeCardWithCount(dealer, count)
        players.forEach { player ->
            distributeCardWithCount(player, count)
        }
    }

    private fun distributeCardWithCount(
        participant: Participant,
        count: Int,
    ) {
        repeat(count) {
            participant.addCard(deck.draw())
        }
    }

    fun distributeCardWithChoice(
        drawDecision: CardDrawDecision,
        player: Player,
    ): Boolean {
        if (drawDecision.isDraw()) {
            distributeCard(player)
            return true
        }
        return false
    }

    fun distributeCard(participant: Participant) {
        participant.addCard(deck.draw())
    }

    fun getPlayersGameResult(): Map<Player, ResultType> {
        val playersSummary =
            players.associateBy(
                { player -> player },
                { player -> ResultType.judgeForPlayer(player, dealer) },
            )
        return playersSummary
    }

    fun getDealerGameResult(): Map<ResultType, Int> =
        players
            .groupBy { player -> ResultType.judgeForDealer(dealer, player) }
            .mapValues { typeGroup -> typeGroup.value.size }
}
