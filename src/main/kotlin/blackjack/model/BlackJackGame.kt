package blackjack.model

class BlackJackGame(
    private val participants: Participants,
    private var gameDeck: GameDeck,
) {
    fun start(printGameSetting: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        gameDeck = participants.initSetting(gameDeck = gameDeck)
        printGameSetting(participants.dealer, participants.playerGroup)
    }

    fun runPlayersTurn(
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        gameDeck =
            participants.playerGroup.drawPlayerCard(
                gameDeck = gameDeck,
                hitOrStay = hitOrStay,
                showPlayerCards = showPlayerCards,
            )
    }

    fun runDealerTurn(printDealerDrawCard: () -> Unit) {
        gameDeck = participants.dealer.drawDealerCard(gameDeck = gameDeck, printDealerDrawCard = printDealerDrawCard)
    }

    fun finish(printEveryCards: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        printEveryCards(participants.dealer, participants.playerGroup)
    }
}
