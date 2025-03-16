package blackjack.model

class BlackjackEngine(
    val cardDeck: CardDeck = CardDeck(),
    val eventListener: EventListener,
    val eventProvider: EventProvider,
) {
    var bets: MutableMap<Player, Money> = mutableMapOf()

    fun getPlayersBet(players: Players) {
        players.getPlayers().forEach { player ->
            bets[player] = Money(eventProvider.getBetAmount(player.name).toDouble())
        }
    }

    fun preparePlayers(names: List<String>): Players =
        Players(
            names.map { name ->
                Player.makePlayer(name, makeFirstHand())
            },
        )

    fun prepareDealer(): Dealer = Dealer.makeDealer(makeFirstHand())

    private fun drawPlayer(player: Player): Unit = player.draw(cardDeck.draw())

    private fun drawDealer(dealer: Dealer): Unit = dealer.drawUntilFinished(cardDeck)

    private fun makeFirstHand(): Hand = Hand(List(START_CARD_COUNT) { cardDeck.draw() })

    fun progressPlayersDraw(players: Players) {
        players.value.forEach { player ->
            eventListener.displayParticipantCards(player.name, player.items.hand.cards)
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player)
        }
    }

    private fun progressPlayerDrawUntilFinished(player: Player) {
        while (eventProvider.getIsDrawMore(player.name)) {
            drawPlayer(player)
            eventListener.displayParticipantCards(player.name, player.items.hand.cards)
            if (player.items.hand.isBust()) return
        }
    }

    fun progressDealerDraw(dealer: Dealer) {
        drawDealer(dealer)
        eventListener.displayDealerDrawInfo(dealer.name, dealer.getHandSize() - START_CARD_COUNT)
        eventListener.displayParticipantInfo(
            dealer.name,
            dealer.items.hand.cards,
            dealer.items.hand.score(),
            dealer.items.hand.isBust(),
        )
    }

    fun progressCalculateResult(
        dealer: Dealer,
        players: Players,
    ): Map<Participant, Money> {
        val participantPrize = mutableMapOf<Participant, Money>()
        val dealerMoney = Money(0.0)
        players.value.forEach { player ->
            val playerBets = bets[player] ?: throw (IllegalArgumentException("[ERROR] 유저를 찾을 수 없습니다."))
            val playerMoney = playerBets.multiplyMoney(WinningResult.getPrize(player.compareHand(dealer)))
            participantPrize[player] = playerMoney
            dealerMoney.addMoney(playerBets.multiplyMoney(WinningResult.getPrize(dealer.compareHand(player))))
        }
        participantPrize[dealer] = dealerMoney
        return participantPrize
    }

    fun progressCalculateFullResult(currentResult: Map<Participant, Money>) {
        currentResult.forEach { (participant, money) ->
            participant.addPrize(money)
        }
    }

    fun setParticipantCard(
        dealer: Dealer,
        players: Players,
    ) {
        dealer.items = Items(makeFirstHand(), dealer.items.money)
        players.value.forEach { player ->
            player.items = Items(makeFirstHand(), player.items.money)
        }
    }

    companion object {
        const val START_CARD_COUNT = 2
    }
}
