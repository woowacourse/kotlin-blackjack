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
        hitOrStay: (humanName: HumanName) -> Boolean,
        getPlayerInfo: (player: Player) -> Unit,
    ) {
        participants.playerGroup.drawPlayerCard(
            gameDeck = gameDeck,
            hitOrStay = hitOrStay,
            getPlayerInfo = getPlayerInfo,
        )
    }

    fun runDealerTurn(alert: () -> Unit) {
        participants.dealer.drawDealerCard(
            gameDeck = gameDeck,
            alert = alert,
        )
    }

    fun finish(printEveryCards: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        printEveryCards(participants.dealer, participants.playerGroup)
    }
}
