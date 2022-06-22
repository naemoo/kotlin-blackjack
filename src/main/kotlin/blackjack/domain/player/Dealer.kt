package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.game.TakeMoreDealer

class Dealer(private val cardDeck: CardDeck) : Player(name = DEALER_NAME, cardDeck.pickCards(INIT_PICK_CARD_NUMBER)) {

    var win: Int = 0
    var lose: Int = 0

    fun play(takeMoreDealer: TakeMoreDealer) {
        while (canBeTakeOneCard(takeMoreDealer)) {
            takeMoreDealer.printTakeMoreDealer()
            addCard(cardDeck.pickCard())
        }
    }

    private fun canBeTakeOneCard(takeMoreDealer: TakeMoreDealer): Boolean {
        return takeMoreDealer.wantToTake(this)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
