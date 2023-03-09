package blackjack.domain

class Participants(private val participants: List<Participant>) {
    init {
        require(participants.size in MINIMUM_PARTICIPANTS..MAXIMUM_PARTICIPANTS) {
            "블랙잭은 딜러를 포함하여 최소 ${MINIMUM_PARTICIPANTS}명에서 최대 ${MAXIMUM_PARTICIPANTS}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${participants.size}명)"
        }
    }

    fun drawFirst(
        deck: CardDeck,
        onStartFirstDrawn: (Participants) -> Unit = {},
        onFirstDrawn: (Participant) -> Unit = {},
    ) {
        onStartFirstDrawn(this)
        participants.forEach { participant ->
            participant.addCard(deck.draw())
            participant.addCard(deck.draw())
            onFirstDrawn(participant)
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

    fun getMatchResults(): List<MatchResult> = listOf(getDealerMatchResult()) + getPlayerMatchResults()

    private fun getDealerMatchResult(): MatchResult {
        var (win, lose, draw) = Triple(0, 0, 0)
        getPlayers().forEach { player ->
            when (getDealer() judge player) {
                GameResult.WIN -> win++
                GameResult.LOSE -> lose++
                GameResult.DRAW -> draw++
            }
        }
        return MatchResult(getDealer(), win, lose, draw)
    }

    private fun getPlayerMatchResults(): List<MatchResult> = getPlayers().map { player ->
        var (win, lose, draw) = Triple(0, 0, 0)
        when (player judge getDealer()) {
            GameResult.WIN -> win++
            GameResult.LOSE -> lose++
            GameResult.DRAW -> draw++
        }
        MatchResult(player, win, lose, draw)
    }

    fun getCardResults(): List<CardResult> = participants.map { participant ->
        CardResult(
            participant,
            participant.getCards(),
            participant.getTotalScore()
        )
    }

    fun getPlayers(): List<Participant> = participants.filterIsInstance<Player>()

    fun getDealer(): Participant = participants.first { it is Dealer }

    private fun draw(participant: Participant, deck: CardDeck) {
        participant.addCard(deck.draw())
    }

    companion object {
        private const val MINIMUM_PARTICIPANTS = 2
        private const val MAXIMUM_PARTICIPANTS = 8
    }
}
