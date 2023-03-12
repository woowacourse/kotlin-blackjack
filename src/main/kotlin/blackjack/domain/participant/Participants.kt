package blackjack.domain.participant

import blackjack.domain.BetMoney
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.result.CardResult
import blackjack.domain.result.GameResult
import blackjack.domain.result.MatchResult

class Participants(private val participants: List<Participant>) {
    private lateinit var participantsBetMoney: Map<Participant, BetMoney>

    init {
        require(participants.size in MINIMUM_PARTICIPANTS..MAXIMUM_PARTICIPANTS) {
            "블랙잭은 딜러를 포함하여 최소 ${MINIMUM_PARTICIPANTS}명에서 최대 ${MAXIMUM_PARTICIPANTS}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${participants.size}명)"
        }
    }

    fun setBetMoney(inputBetMoney: (String) -> Int) {
        participantsBetMoney = participants
            .filterIsInstance<Player>()
            .associateWith { BetMoney(inputBetMoney(it.name)) }
    }

    fun drawFirst(deck: CardDeck) {
        participants.forEach { participant ->
            participant.addCard(deck.draw())
            participant.addCard(deck.draw())
        }
    }

    fun takePlayerTurns(deck: CardDeck, onDrawn: (Participant) -> Unit) {
        getPlayers().forEach { participant ->
            drawUntilCanDraw(participant, deck, onDrawn)
        }
    }

    fun takeDealerTurns(deck: CardDeck, onDrawn: (Participant) -> Unit) {
        drawUntilCanDraw(getDealer(), deck, onDrawn)
    }

    private fun drawUntilCanDraw(
        participant: Participant,
        deck: CardDeck,
        onDrawn: (Participant) -> Unit
    ) {
        while (participant.canDraw()) {
            draw(participant, deck)
            onDrawn(participant)
        }
    }

    fun getMatchResults(): List<MatchResult> {
        val playerMatchResult = getPlayerMatchResults()

        return listOf(getDealerMatchResult(playerMatchResult)) + playerMatchResult
    }

    private fun getDealerMatchResult(playerMatchResult: List<MatchResult>): MatchResult {
        var total = 0
        playerMatchResult.forEach { total += it.total }

        return MatchResult(getDealer(), total * -1)
    }

    private fun getPlayerMatchResults(): List<MatchResult> = getPlayers().map { player ->
        var total = 0
        when (player judge getDealer()) {
            GameResult.WIN -> total += participantsBetMoney[player]?.money
                ?: throw IllegalArgumentException("등록되지 않은 플레이어입니다.")

            GameResult.LOSE -> total -= participantsBetMoney[player]?.money
                ?: throw IllegalArgumentException("등록되지 않은 플레이어입니다.")

            GameResult.DRAW -> {}

            GameResult.BLACKJACK_WIN -> total += participantsBetMoney[player]?.addBlackjackPrizeMoney()
                ?: throw IllegalArgumentException("등록되지 않은 플레이어입니다.")
        }
        MatchResult(player, total)
    }

    fun getCardResults(): List<CardResult> = participants.map { participant ->
        CardResult(
            participant,
            participant.getCards(),
            participant.getTotalScore()
        )
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.associate { it.name to it.getFirstOpenCards() }

    private fun getPlayers(): List<Participant> = participants.filterIsInstance<Player>()

    private fun getDealer(): Participant = participants.first { it is Dealer }

    private fun draw(participant: Participant, deck: CardDeck) {
        participant.addCard(deck.draw())
    }

    companion object {
        private const val MINIMUM_PARTICIPANTS = 2
        private const val MAXIMUM_PARTICIPANTS = 8
    }
}
