package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.ResultView

private const val INSERT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
private const val INSERT_PLAYER_BET_AMOUNT = "%s의 배팅 금액은?"
private const val ANSWER_YES = "y"
private const val ANSWER_NO = "n"
private const val INSERT_GET_ONE_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_YES, 아니오는 $ANSWER_NO)"
private const val DECK_EXHAUSTED_MESSAGE = "덱의 카드가 모두 소진되었습니다."

fun main() {
    val deck = Deck()
    val players = getPlayers()
    val dealer = Dealer()
    val playersBetAmount = getPlayersBetAmount(players)

    dealCards(players.toList() + dealer, deck)
    ResultView.printSetUp(dealer, players)

    decideHitOrStand(players, deck)

    checkDealerHitOrStand(dealer, deck)

    ResultView.printResult(dealer, players, BlackjackResult.of(dealer, players))
}

private fun getPlayers(): Players {
    ResultView.printMessage(INSERT_PLAYERS_NAME)
    return ReadValueSureModifier.tryToReadValueAndModifyToTargetUntilNoErrorOccur(
        InputView::readStrings,
        Players::create,
    )
}

private fun getPlayersBetAmount(players: Players): PlayersBetAmount {
    val playersBetAmount = mutableMapOf<Player, Money>()
    players.forEach {
        playersBetAmount[it] = getPlayerBetAmount(it)
    }
    return PlayersBetAmount(playersBetAmount)
}

private fun getPlayerBetAmount(player: Player): Money {
    ResultView.printMessage(INSERT_PLAYER_BET_AMOUNT.format(player.name))
    return ReadValueSureModifier.tryToReadValueAndModifyToTargetUntilNoErrorOccur(
        InputView::readNumber,
        ::Money,
    )
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
    players.forEach { decideHitOrStand(it, deck) }
}

private fun decideHitOrStand(player: Player, deck: Deck) {
    while (deck.isNotExhausted() && player.canHit() && askPlayerWantToHitUntilGetCorrectAnswer(player)) {
        tryToDraw(player, deck)
        ResultView.printCards(player)
    }
}

private fun askPlayerWantToHitUntilGetCorrectAnswer(player: Player): Boolean {
    ResultView.printMessage(INSERT_GET_ONE_MORE_CARD.format(player.name))
    var readValue = InputView.readCharacter()
    while (readValue != ANSWER_YES && readValue != ANSWER_NO) {
        ResultView.printMessage("$ANSWER_YES 또는 ${ANSWER_NO}만 입력바랍니다.")
        readValue = InputView.readCharacter()
    }
    return readValue == ANSWER_YES
}

private fun checkDealerHitOrStand(dealer: Dealer, deck: Deck) {
    if (deck.isNotExhausted() && dealer.shouldHit()) {
        tryToDraw(dealer, deck)
        ResultView.printDealerHitMessage(dealer.name)
    }
}
