package blackjack.domain

class Participants(names: List<String>) {
    val dealer: Dealer = Dealer(makeCardBunch())
    val players: List<Player> = names.map { Player(it, makeCardBunch()) }

    private fun makeCardBunch(): CardBunch = CardBunch(CardDeck.drawCard(), CardDeck.drawCard())

    fun progressPlayersAddCard(getDecision: (Player) -> Boolean, printPlayerCard: (Player) -> Unit) {
        players.forEach { progressEachPlayerAddCard(it, getDecision, printPlayerCard) }
    }

    private fun progressEachPlayerAddCard(
        player: Player,
        getDecision: (Player) -> Boolean,
        printPlayerCard: (Player) -> Unit,
    ) {
        while (!player.isOverCondition()) {
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
        player.cardBunch.addCard(CardDeck.drawCard())
        printPlayerCard(player)
    }

    fun judgmentDealerAddCard() {
        if (!dealer.isOverCondition()) dealer.addCard(CardDeck.drawCard())
    }

    fun getConsequence(player: Player): Consequence = player.chooseWinner(dealer)
}
