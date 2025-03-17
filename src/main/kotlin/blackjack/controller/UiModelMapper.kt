package blackjack.controller

import blackjack.domain.Betting
import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.state.ParticipantState
import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerConfig
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

fun List<PlayerConfig>.toPlayers(deck: Deck): List<Player> = map { playerConfig -> playerConfig.toPlayer(deck) }

private fun PlayerConfig.toPlayer(deck: Deck): Player = Player(name, Betting(bettingAmount), deck)

val List<Player>.names: List<String> get() = map { player -> player.name }

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

val Dealer.cardsPrettyString: List<String> get() = cards.prettyString

val List<Participant>.cardsPrettyStrings: List<List<String>>
    get() = map { participant -> participant.cards.prettyString }

val List<Card>.prettyString: List<String>
    get() = map { card: Card -> card.prettyString }

private val Card.prettyString: String
    get() = rank.prettyString + suit.prettyString

val Participant.summary: DealerSummary
    get() =
        DealerSummary(
            cards.prettyString,
            score.value,
        )

private val Player.summary: PlayerSummary
    get() = PlayerSummary(name, cards.prettyString, score.value)

val List<Player>.summaries: List<PlayerSummary>
    get() = map { player -> player.summary }

fun List<Player>.toResults(dealerState: ParticipantState): List<PlayerResult> =
    map { player -> PlayerResult(player.name, player.calculateProfit(dealerState).toInt()) }
