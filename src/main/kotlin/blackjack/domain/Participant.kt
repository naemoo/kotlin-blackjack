package blackjack.domain

abstract class Participant(open val name: String, open val myCards: Cards = Cards()) {

    val totalScore: Int
        get() = myCards.totalScore

    fun receive(card: Card) = myCards.add(card)

    fun canDraw(): Boolean = totalScore < BLACK_JACK

    companion object {
        private const val BLACK_JACK = 21
    }
}
