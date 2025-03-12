package blackjack.model

class BlackjackEngine(
    val cardDeck: CardDeck = CardDeck()
) {
    fun preparePlayers(names: List<String>): Players = Players(names.map { name ->
        Player(name, makeFirstHand())
    })

    fun prepareDealer(): Dealer = Dealer(hand = makeFirstHand())

    fun drawPlayer(player: Player): Unit = player.draw(cardDeck)

    fun drawDealer(dealer: Dealer): Unit = dealer.drawUntilFinished(cardDeck)

    fun makeFirstHand(): Hand = Hand(List(2) { cardDeck.draw() })

    fun progressPlayersDraw(players: Players, eventListener: EventListener, eventProvider: EventProvider) {
        players.value.forEach { player ->
            eventListener.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player, eventListener, eventProvider)
        }
    }

    private fun progressPlayerDrawUntilFinished(
        player: Player,
        eventListener: EventListener,
        eventProvider: EventProvider,
    ) {
        while (eventProvider.getIsDrawMore(player.name)) {
            drawPlayer(player)
            eventListener.displayParticipantCards(player.name, player.hand.cards)
            if (player.hand.isBust()) return
        }
    }

    fun progressDealerDraw(dealer: Dealer, eventListener: EventListener) {
        drawDealer(dealer)
        eventListener.displayDealerDrawInfo(dealer.name, dealer.getAdditionalDrawCount())
        eventListener.displayParticipantInfo(dealer.name, dealer.hand.cards, dealer.hand.score(), dealer.hand.isBust())
    }

}
