package blackjack.model

class BlackJackGame(
    private val participants: Participants,
    private val gameDeck: GameDeck,
) {
    fun start(printGameSetting: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        participants.initSetting(gameDeck = gameDeck)
        printGameSetting(participants.dealer, participants.playerGroup)
    }

    fun runPlayersTurn(
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        participants.playerGroup.drawPlayerCard(
            gameDeck = gameDeck,
            hitOrStay = hitOrStay,
            showPlayerCards = showPlayerCards,
        )
    }

    fun runDealerTurn(printDealerDrawCard: () -> Unit) {
        participants.dealer.drawDealerCard(gameDeck = gameDeck, printDealerDrawCard = printDealerDrawCard)
    }

    fun finish(printEveryCards: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        printEveryCards(participants.dealer, participants.playerGroup)
    }
}
