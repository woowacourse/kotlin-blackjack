package blackjack.model.store

import blackjack.model.action.Action
import blackjack.model.dispatcher.Dispatcher
import blackjack.model.domain.ParticipantInfo

class Store(dispatcher: Dispatcher) {
    private var _dealerInfo: ParticipantInfo = ParticipantInfo("딜러")
    val dealerInfo: ParticipantInfo
        get() = _dealerInfo

    private var _playersInfo: List<ParticipantInfo> = listOf()
    val playersInfo: List<ParticipantInfo>
        get() = _playersInfo

    init {
        dispatcher.register(::onAction)
    }

    private fun onAction(action: Action) {
        when (action) {
            is Action.ReadNames -> {
                _playersInfo = action.names.map { name ->
                    ParticipantInfo(name)
                }
            }

            is Action.InitDealerCard -> {
                repeat(action.count) {
                    _dealerInfo = dealerInfo.copy(cards = dealerInfo.cards + action.onDrawCard())
                }
            }

            is Action.InitPlayersCard -> {
                repeat(action.count) {
                    _playersInfo = playersInfo.map { playerInfo ->
                        playerInfo.copy(cards = playerInfo.cards + action.onDrawCard())
                    }
                }
            }

            is Action.DrawUntilPlayersSatisfaction -> {
                _playersInfo = playersInfo.map { playerInfo ->
                    var newPlayerInfo = playerInfo

                    while (action.onInputDecision(newPlayerInfo.name) == "y" && newPlayerInfo.sumCardValues() <= 21) {
                        newPlayerInfo = playerInfo.copy(cards = newPlayerInfo.cards + action.onDrawCard())
                        action.onPrintCards(newPlayerInfo)
                    }

                    newPlayerInfo
                }
            }

            is Action.DrawUntilDealerSatisfaction -> {
                while (dealerInfo.sumCardValues() <= 16) {
                    _dealerInfo = dealerInfo.copy(cards = dealerInfo.cards + action.onDrawCard())
                    action.onPrintCards(dealerInfo)
                }
            }
        }
    }
}
