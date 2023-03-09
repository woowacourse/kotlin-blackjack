package domain

import domain.util.BetInfosBuilder
import domain.util.DealerBuilder
import domain.util.PlayerBuilder
import domain.util.PlayersBuilder
import model.InputState
import model.cards.CardPack
import model.participants.Bet
import model.participants.BetInfo
import model.participants.Dealer
import model.participants.Name
import model.participants.Names
import model.participants.Participants
import model.participants.Player
import model.participants.Players

class CardGame(private val cardPack: CardPack, private val onError: (String) -> Unit) {
    fun setUp(getName: () -> InputState<List<String>>): Participants {
        val dealer = setDealer()
        val players = setPlayers(initName(getName))
        return Participants(dealer, players)
    }

    private fun initName(getName: () -> InputState<List<String>>): Names = when (val names = getName()) {
        is InputState.Error -> {
            onError(names.error)
            initName(getName)
        }

        is InputState.Success -> Names(names.input.map(::Name))
    }

    private fun setDealer(): Dealer = dealer {
        hand(cardPack.pop())
        hand(cardPack.pop())
    }

    private fun setPlayers(names: Names): Players = players {
        names.forEach {
            setPlayer(it)
        }
    }

    private fun setPlayer(name: Name): Player = player {
        name(name)
        hand(cardPack.pop())
        hand(cardPack.pop())
    }

    fun setBets(players: Players, onGetBet: (Name) -> Unit, getBet: () -> InputState<Int>): BetInfo = betInfos {
        players.toList().forEach {
            onGetBet(it.name)
            bet(it, setBet(getBet))
        }
    }

    private fun setBet(getBet: () -> InputState<Int>): Bet = when (val bet = getBet()) {
        is InputState.Error -> {
            onError(bet.error)
            setBet(getBet)
        }

        is InputState.Success -> Bet(bet.input)
    }

    fun askGetMorePlayersCard(
        players: Players,
        onHit: (Name) -> Unit,
        getAnswer: () -> InputState<Boolean>,
        onGetPlayerCard: (Player) -> Unit
    ) {
        players.toList().filter { it.isHit() }.forEach {
            onHit(it.name)
            askGetMorePlayerCard(it, getAnswer, onGetPlayerCard, onHit)
        }
    }

    private fun askGetMorePlayerCard(
        player: Player,
        getAnswer: () -> InputState<Boolean>,
        onGetPlayerCard: (Player) -> Unit,
        onHit: (Name) -> Unit
    ) {
        while (player.isHit() && askYesOrNo(getAnswer)) {
            player.pick(cardPack)
            onGetPlayerCard(player)
            if (player.isHit()) onHit(player.name)
        }
    }

    private fun askYesOrNo(getAnswer: () -> InputState<Boolean>): Boolean = when (val answer = getAnswer()) {
        is InputState.Error -> {
            onError(answer.error)
            askYesOrNo(getAnswer)
        }

        is InputState.Success -> answer.input
    }

    fun getMoreDealerCard(dealer: Dealer, onHitDealer: () -> Unit) {
        while (dealer.isHit()) {
            onHitDealer()
            dealer.pick(cardPack)
        }
    }

    private fun betInfos(block: BetInfosBuilder.() -> Unit): BetInfo = BetInfosBuilder().apply(block).build()
    private fun dealer(block: DealerBuilder.() -> Unit): Dealer = DealerBuilder().apply(block).build()
    private fun player(block: PlayerBuilder.() -> Unit): Player = PlayerBuilder().apply(block).build()
    private fun players(block: PlayersBuilder.() -> Unit): Players = PlayersBuilder().apply(block).build()
}
