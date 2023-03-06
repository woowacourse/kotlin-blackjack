package blackjack.domain

import blackjack.domain.carddeck.CardDeck

class Participants(names: List<String>, private val cardDeck: CardDeck) {
    val dealer: Dealer = Dealer(makeCardBunch())
    val players: List<Player> = names.map { Player(it, makeCardBunch()) }

    private fun makeCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())

    fun progressPlayersAddCard(getDecision: (Player) -> Boolean, printPlayerCard: (Player) -> Unit) {
        players.forEach { progressEachPlayerAddCard(it, getDecision, printPlayerCard) }
    }

    private fun progressEachPlayerAddCard(
        player: Player,
        getDecision: (Player) -> Boolean,
        printPlayerCard: (Player) -> Unit,
    ) {
        while (!player.isBurst()) {
            if (!progressEachTurn(player, getDecision, printPlayerCard)) return
        }
    }

    private fun progressEachTurn(
        player: Player,
        getDecision: (Player) -> Boolean,
        printPlayerCard: (Player) -> Unit,
    ): Boolean {
        if (getDecision(player)) {
            positiveGetCard(player, printPlayerCard)
            return true
        }
        printPlayerCard(player)
        return false
    }

    private fun positiveGetCard(
        player: Player,
        printPlayerCard: (Player) -> Unit,
    ) {
        player.cardBunch.addCard(cardDeck.drawCard())
        printPlayerCard(player)
    }
}
