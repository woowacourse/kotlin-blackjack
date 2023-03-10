package blackjack.domain

import blackjack.domain.carddeck.CardDeck

class BlackJackGame(names: List<String>, private val cardDeck: CardDeck) {
    val participants: Participants = Participants(names, cardDeck::drawCard)

    fun progressPlayersAddCard(getDecision: (Player) -> Boolean, printPlayerCard: (Player) -> Unit) {
        participants.players.forEach { progressEachPlayerAddCard(it, getDecision, printPlayerCard) }
    }

    private fun progressEachPlayerAddCard(
        player: Player,
        getDecision: (Player) -> Boolean,
        transferPlayerCard: (Player) -> Unit,
    ) {
        while (!player.isOverCondition()) {
            if (!progressEachTurn(player, getDecision, transferPlayerCard)) return
        }
    }

    private fun progressEachTurn(
        player: Player,
        getDecision: (Player) -> Boolean,
        transferPlayerCard: (Player) -> Unit,
    ): Boolean {
        if (getDecision(player)) {
            positiveGetCard(player, transferPlayerCard)
            return true
        }
        transferPlayerCard(player)
        return false
    }

    private fun positiveGetCard(
        player: Player,
        transferPlayerCard: (Player) -> Unit,
    ) {
        player.cardBunch.addCard(cardDeck.drawCard())
        transferPlayerCard(player)
    }

    fun judgmentDealerAddCard() {
        while (!participants.dealer.isOverCondition()) participants.dealer.addCard(cardDeck.drawCard())
    }
}
