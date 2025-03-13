package blackjack.controller

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.ParticipantState
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.view.model.DealerResult
import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

private val Rank.prettyString: String
    get() =
        when (this) {
            Rank.AceRank -> "A"
            Rank.NumberRank.TWO -> "2"
            Rank.NumberRank.THREE -> "3"
            Rank.NumberRank.FOUR -> "4"
            Rank.NumberRank.FIVE -> "5"
            Rank.NumberRank.SIX -> "6"
            Rank.NumberRank.SEVEN -> "7"
            Rank.NumberRank.EIGHT -> "8"
            Rank.NumberRank.NINE -> "9"
            Rank.NumberRank.TEN -> "10"
            Rank.FaceRank.JACK -> "J"
            Rank.FaceRank.QUEEN -> "Q"
            Rank.FaceRank.KING -> "K"
        }

private val Suit.prettyString: String
    get() =
        when (this) {
            Suit.SPADE -> "♠"
            Suit.HEART -> "♥"
            Suit.DIAMOND -> "♦"
            Suit.CLOVER -> "♣"
        }

val ParticipantState.prettyString: String
    get() =
        when (this) {
            ParticipantState.PLAYING -> "진행중"
            ParticipantState.WIN -> "승"
            ParticipantState.DRAW -> "무"
            ParticipantState.LOSE -> "패"
        }

val List<Player>.names: List<String>
    get() = map { player -> player.name }

val List<Card>.prettyString: List<String>
    get() = map { card: Card -> card.prettyString }

private val Card.prettyString: String
    get() = rank.prettyString + suit.prettyString

val List<Player>.playersCards: List<List<String>>
    get() = map { player -> player.cards.prettyString }

val List<String>.toPlayers: List<Player>
    get() = map { playerName -> Player(playerName) }

val Dealer.result: DealerResult
    get() =
        DealerResult(
            dealerResults.count { state: ParticipantState -> state == ParticipantState.WIN },
            dealerResults.count { state: ParticipantState -> state == ParticipantState.DRAW },
            dealerResults.count { state: ParticipantState -> state == ParticipantState.LOSE },
        )

val List<Player>.results: List<PlayerResult>
    get() = map { player -> player.result }

private val Player.result: PlayerResult
    get() = PlayerResult(name, state.prettyString)

val Dealer.summary: DealerSummary
    get() =
        DealerSummary(
            cards.map { card -> card.prettyString },
            score.value,
        )

private val Player.summary: PlayerSummary
    get() = PlayerSummary(name, cards.prettyString, score.value)

val List<Player>.summaries: List<PlayerSummary>
    get() = map { player -> player.summary }
