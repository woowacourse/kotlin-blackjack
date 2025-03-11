package blackjack.controller

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.ParticipantState
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.view.model.DealerResult
import blackjack.view.model.PlayerResult

val Card.prettyString: String
    get() = rank.prettyString + suit.prettyString

val Rank.prettyString: String
    get() =
        when (this) {
            Rank.AceRank -> "A"
            is Rank.FaceRank -> value.toString()
            is Rank.NumberRank -> value.toString()
        }

val Suit.prettyString: String
    get() =
        when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLOVER -> "클로버"
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

val List<Player>.cards: List<List<String>>
    get() = map { player -> player.cards.prettyString }

val List<String>.toPlayers: List<Player>
    get() = map { playerName -> Player(playerName) }

val Dealer.toDealerResult: DealerResult
    get() =
        DealerResult(
            dealerResults.count { state: ParticipantState -> state == ParticipantState.WIN },
            dealerResults.count { state: ParticipantState -> state == ParticipantState.DRAW },
            dealerResults.count { state: ParticipantState -> state == ParticipantState.LOSE },
        )

val Player.toPlayerResult: PlayerResult
    get() = PlayerResult(name, state.prettyString)
