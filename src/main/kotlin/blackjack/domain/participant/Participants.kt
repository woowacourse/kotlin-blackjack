package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.result.GameResult

class Participants(private val participants: List<Participant>) {
    private val dealer = getDealer()
    private val players = getPlayers()

    init {
        require(participants.size in MINIMUM_PARTICIPANTS..MAXIMUM_PARTICIPANTS) {
            "블랙잭은 딜러를 포함하여 최소 ${MINIMUM_PARTICIPANTS}명에서 최대 ${MAXIMUM_PARTICIPANTS}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${participants.size}명)"
        }
        requireNotNull(dealer) { "참여자에 딜러가 포함되어 있지 않습니다." }
    }

    fun drawFirst(
        deck: CardDeck,
        onFirstDrawn: (Participant) -> Unit = {},
    ): Participants {
        return Participants(
            dealerFirst()
                .map { participant ->
                    participant
                        .draw(deck.draw(), justDraw = true)
                        .draw(deck.draw(), justDraw = true)
                }
                .onEach(onFirstDrawn)
        )
    }

    fun takeTurns(deck: CardDeck, onDrawn: (Participant) -> Unit): Participants = Participants(
        playersFirst().map { participant -> drawUntilCanDraw(participant, deck, onDrawn) }
    )

    private fun drawUntilCanDraw(
        participant: Participant,
        deck: CardDeck,
        onDrawn: (Participant) -> Unit
    ): Participant {
        var result = participant
        while (result.isRunning) {
            val card = deck.draw()
            result = result.draw(card).apply { if (isRunning) onDrawn(this) }
        }
        return result
    }

    fun getGameResult(): List<GameResult> = dealerFirst().map { participant ->
        GameResult(
            participant.name,
            participant.getCards(),
            participant.getTotalScore(),
            participant.getProfit(participants)
        )
    }

    fun getPlayers(): List<Participant> = participants.filterIsInstance<Player>()

    fun getDealer(): Participant? = participants.firstOrNull { it is Dealer }

    private fun dealerFirst(): List<Participant> = listOf(dealer!!) + players

    private fun playersFirst(): List<Participant> = players + dealer!!

    companion object {
        private const val MINIMUM_PARTICIPANTS = 2
        private const val MAXIMUM_PARTICIPANTS = 8
    }
}
