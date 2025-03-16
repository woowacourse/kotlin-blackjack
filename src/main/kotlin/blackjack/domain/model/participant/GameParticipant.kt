package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards
import blackjack.domain.model.progress.BetAmount
import blackjack.domain.model.progress.WinLoss

abstract class GameParticipant(
    protected val participantInfo: ParticipantInfo,
) {
    protected val handCards: HandCards = HandCards()

    val name: String
        get() = participantInfo.name

    val betAmount: BetAmount = participantInfo.betAmount

    val cardStatus: CardStatus
        get() = handCards.getStatus()

    val bestValue: Int
        get() = handCards.calculateBestCardValue()

    val cards: List<Card>
        get() = handCards.cards.toList()

    protected fun calculateWinLoss(rival: GameParticipant): WinLoss =
        when {
            (cardStatus == CardStatus.BLACKJACK) && (rival.cardStatus != CardStatus.BLACKJACK) -> WinLoss.WIN
            cardStatus == CardStatus.BUST -> WinLoss.LOSE
            (rival.cardStatus != CardStatus.BUST) && (rival.bestValue > bestValue) -> WinLoss.LOSE
            (rival.cardStatus == CardStatus.BLACKJACK) && (cardStatus != CardStatus.BLACKJACK) -> WinLoss.LOSE
            (rival.bestValue == bestValue) -> WinLoss.DRAW
            else -> WinLoss.WIN
        }

    val cardSize: Int
        get() = handCards.cards.size

    fun isInitHandCard() = cardSize == HandCards.INIT_CARD_SIZE

    fun drawCardFromDeck(deck: Deck) {
        handCards.addCard(deck.getCard())
    }

    abstract fun isDrawFinish(): Boolean
}
