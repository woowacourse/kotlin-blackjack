package blackjack.model

import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT

class BlackjackGame(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val gameManager = GameManager(dealer, players)

    fun processDistributeInitialCards(
        initialCardCount: Int = INITIAL_HAND_OUT_CARD_COUNT,
        showDistributeGuideMessage: (List<Player>) -> Unit,
        showDistributedCardStatus: (Dealer, List<Player>) -> Unit,
    ) {
        showDistributeGuideMessage(players)
        gameManager.distributeInitialCardWithCount(initialCardCount)
        showDistributedCardStatus(dealer, players)
    }

    fun processParticipantsDrawCards(
        playerDrawDecision: (Player) -> CardDrawDecision,
        showPlayerCardStatus: (Player) -> Unit,
        showDealerCardStatus: (Boolean) -> Unit,
    ) {
        if (!dealer.isBlackjack()) {
            processPlayerDrawCards(playerDrawDecision, showPlayerCardStatus)
            processDealerDrawCard(showDealerCardStatus)
        }
    }

    private fun processPlayerDrawCards(
        playerDrawDecision: (Player) -> CardDrawDecision,
        showPlayerCardStatus: (Player) -> Unit,
    ) {
        players.forEach { player ->
            playerDrawOrStay(player, playerDrawDecision, showPlayerCardStatus)
        }
    }

    private fun playerDrawOrStay(
        player: Player,
        playerDrawDecision: (Player) -> CardDrawDecision,
        showPlayerCardStatus: (Player) -> Unit,
    ) {
        while (true) {
            val decision: CardDrawDecision = playerDrawDecision(player)
            if (gameManager.distributeCardWithChoice(decision, player)) {
                showPlayerCardStatus(player)
                if (player.isBust()) break
                continue
            }
            showPlayerCardStatus(player)
            break
        }
    }

    private fun processDealerDrawCard(showDealerCardStatus: (Boolean) -> Unit) {
        val isDraw = dealer.isAvailDrawCard()
        if (isDraw) gameManager.distributeCard(dealer)
        showDealerCardStatus(isDraw)
    }

    fun processAllParticipantsCardStatus(showParticipantsCardStatus: (Dealer, List<Player>) -> Unit) {
        showParticipantsCardStatus(dealer, players)
    }

    fun processResultSummary(showResultSummary: (Map<Player, ResultType>, Map<ResultType, Int>) -> Unit) {
        val playersSummary = gameManager.calculatePlayersSummary()
        val dealerSummary = gameManager.calculateDealerSummary()
        showResultSummary(playersSummary, dealerSummary)
    }
}
