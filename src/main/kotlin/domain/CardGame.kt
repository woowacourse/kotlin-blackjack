package domain

import model.Bet
import model.BetInfos
import model.BetInfosBuilder
import model.CardPack
import model.Dealer
import model.DealerBuilder
import model.InputState
import model.Name
import model.Names
import model.Participants
import model.Player
import model.Players
import model.PlayersBuilder

class CardGame(private val cardPack: CardPack, private val onError: (String) -> Unit) {
    fun setUp(getName: () -> InputState<List<String>>): Participants {
        val names = initName(getName)
        val dealer = setDealer()
        val players = players {
            names.forEach {
                player {
                    name(it)
                    hand(cardPack.pop())
                    hand(cardPack.pop())
                }
            }
        }
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

    fun setBets(players: Players, onGetBet: (Name) -> Unit, getBet: () -> InputState<Int>): BetInfos {
        return betInfos {
            players.toList().forEach {
                onGetBet(it.name)
                bet(it, setBet(getBet))
            }
        }
    }

    private fun setBet(getBet: () -> InputState<Int>): Bet {
        return when (val bet = getBet()) {
            is InputState.Error -> {
                onError(bet.error)
                setBet(getBet)
            }

            is InputState.Success -> Bet(bet.input)
        }
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

    private fun askYesOrNo(getAnswer: () -> InputState<Boolean>): Boolean {
        return when (val answer = getAnswer()) {
            is InputState.Error -> {
                onError(answer.error)
                askYesOrNo(getAnswer)
            }

            is InputState.Success -> answer.input
        }
    }

    fun getMoreDealerCard(dealer: Dealer, onHitDealer: () -> Unit) {
        while (dealer.isHit()) {
            onHitDealer()
            dealer.pick(cardPack)
        }
    }

    private fun betInfos(block: BetInfosBuilder.() -> Unit): BetInfos {
        return BetInfosBuilder().apply(block).build()
    }

    private fun dealer(block: DealerBuilder.() -> Unit): Dealer {
        return DealerBuilder().apply(block).build()
    }

    private fun players(block: PlayersBuilder.() -> Unit): Players {
        return PlayersBuilder().apply(block).build()
    }
}
