package blackjack.model

class BlackJackGame(
    private val participants: Participants,
    private val gameDeck: GameDeck,
) {
    fun start(printGameSetting: (dealerCard: Card, playerGroup: PlayerGroup) -> Unit) {
        participants.initSetting(gameDeck)
        printGameSetting(participants.dealer.state.hand.cards.first(), participants.playerGroup)
    }

    fun runPlayersTurn(
        hitOrStay: (name: Name) -> Boolean,
        getPlayerInfo: (player: Player) -> Unit,
    ) {
        participants.playerGroup.drawPlayerCard(
            gameDeck = gameDeck,
            hitOrStay = hitOrStay,
            returnPlayerInfo = getPlayerInfo,
        )
    }

    fun runDealerTurn(alertCountOfDraw: (drawCount: Int) -> Unit) {
        alertCountOfDraw(participants.dealer.drawUntilOverThreshold(gameDeck = gameDeck))
    }

    fun finish(printEveryCards: (dealerHand: Hand, playerGroup: PlayerGroup) -> Unit) {
        printEveryCards(participants.dealer.state.hand, participants.playerGroup)
    }
}
