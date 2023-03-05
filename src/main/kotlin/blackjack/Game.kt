package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = Players(InputView.getNames().map(::Player))
    val dealer = Dealer()

    dealCards(players.toList() + dealer)
    ResultView.printSetUp(dealer, players)

    decideHitOrStand(players)

    checkDealerHitOrStand(dealer)

    ResultView.printResult(dealer, players, BlackjackResult.of(dealer, players))
}

private fun dealCards(participants: List<Participant>) {
    repeat(Participant.INIT_CARD_SIZE) {
        participants.forEach { tryToDraw(it) }
    }
}

private fun tryToDraw(participant: Participant) {
    Deck.draw()?.let { card -> participant.receive(card) } ?: ResultView.printMessage(DECK_EXHAUSTED_MESSAGE)
}

private fun decideHitOrStand(players: Players) {
    players.toList().forEach { decideHitOrStand(it) }
}

private fun decideHitOrStand(player: Player) {
    while (Deck.isNotExhausted() && player.canHit() && InputView.getAnswerOf(player) == ANSWER_YES) {
        tryToDraw(player)
        ResultView.printCards(player)
    }
}

private fun checkDealerHitOrStand(dealer: Dealer) {
    if (Deck.isNotExhausted() && dealer.shouldHit()) {
        tryToDraw(dealer)
        ResultView.printDealerHitMessage(dealer.name)
    }
}
