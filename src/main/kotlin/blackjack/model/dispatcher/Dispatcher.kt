package blackjack.model.dispatcher

import blackjack.model.action.Action

class Dispatcher {
    private val listeners: MutableList<(Action) -> Unit> = mutableListOf()

    fun register(listener: (Action) -> Unit) {
        listeners.add(listener)
    }

    fun dispatch(action: Action) {
        listeners.forEach { it(action) }
    }
}
