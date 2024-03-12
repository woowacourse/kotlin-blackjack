package blackjack.model

class BlackJackGame(
    private val participants: Participants,
    private val gameDeck: GameDeck,
) {
    fun start(printGameSetting: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        participants.initSetting(gameDeck)
        printGameSetting(participants.dealer, participants.playerGroup)
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

    fun runDealerTurn(alert: (drawCount: Int) -> Unit) {
        alert(participants.dealer.drawDealerCard(gameDeck = gameDeck))
    }

    fun finish(printEveryCards: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        printEveryCards(participants.dealer, participants.playerGroup)
    }
}
