package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck()
    val players = Players(InputView.getNames().map(::Player))
    val dealer = Dealer()

    dealCards(players.toList() + dealer, deck)
    ResultView.printSetUp(dealer, players)

    decideHitOrStand(players, deck)

    checkDealerHitOrStand(dealer, deck)

    ResultView.printResult(dealer, players, BlackjackResult.of(dealer, players))
}

private fun dealCards(participants: List<Participant>, deck: Deck) {
    repeat(Participant.INIT_CARD_SIZE) {
        participants.forEach { tryToDraw(it, deck) }
    }
}

private fun tryToDraw(participant: Participant, deck: Deck) {
    deck.draw()?.let { card -> participant.receive(card) } ?: ResultView.printMessage(DECK_EXHAUSTED_MESSAGE)
}

private fun decideHitOrStand(players: Players, deck: Deck) {
    players.toList().forEach { decideHitOrStand(it, deck) }
}

private fun decideHitOrStand(player: Player, deck: Deck) {
    while (deck.isNotExhausted() && player.canHit() && InputView.getAnswerOf(player) == ANSWER_YES) {
        tryToDraw(player, deck)
        ResultView.printCards(player)
    }
}

private fun checkDealerHitOrStand(dealer: Dealer, deck: Deck) {
    if (deck.isNotExhausted() && dealer.shouldHit()) {
        tryToDraw(dealer, deck)
        ResultView.printDealerHitMessage(dealer.name)
    }
}
