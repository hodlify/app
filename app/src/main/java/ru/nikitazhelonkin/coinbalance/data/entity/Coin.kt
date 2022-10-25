package ru.nikitazhelonkin.coinbalance.data.entity


import androidx.annotation.DrawableRes

import ru.nikitazhelonkin.coinbalance.R

enum class Coin constructor(val ticker: String, val title: String, @param:DrawableRes val iconResId: Int) {

    BTC("BTC", "Bitcoin", R.drawable.btc),
    BCH("BCH", "Bitcoin Cash", R.drawable.bch),
    ETH("ETH", "Ethereum", R.drawable.eth),
    ETC("ETC", "Ethereum Classic", R.drawable.etc),
    LTC("LTC", "Litecoin", R.drawable.ltc),
    XRP("XRP", "Ripple", R.drawable.xrp),
    BNB("BNB", "Binance Coin", R.drawable.bnb),
    DASH("DASH", "Dash", R.drawable.dash),
    XEM("XEM", "Nem", R.drawable.xem),
    XLM("XLM", "Stellar", R.drawable.xlm),
    ADA("ADA", "Cardano", R.drawable.ada),
    TRX("TRX", "Tron", R.drawable.trx),
    NEO("NEO", "Neo", R.drawable.neo),
    ZEC("ZEC", "ZCash", R.drawable.zec),
    DOGE("DOGE", "Doge", R.drawable.doge),
    DOT("DOT", "Polkadot", R.drawable.dot);

    override fun toString(): String {
        return ticker
    }

    companion object {

        @JvmStatic fun forTicker(ticker: String): Coin? {
            for (coin in values()) {
                if (coin.ticker.equals(ticker, ignoreCase = true)) {
                    return coin
                }
            }
            return null
        }
    }
}
