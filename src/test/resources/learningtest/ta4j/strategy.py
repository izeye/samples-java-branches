from org.ta4j.core import BaseStrategy
from org.ta4j.core.indicators import SMAIndicator
from org.ta4j.core.indicators.helpers import ClosePriceIndicator
from org.ta4j.core.trading.rules import OverIndicatorRule
from org.ta4j.core.trading.rules import UnderIndicatorRule

def buildStrategy(series):
    closePriceIndicator = ClosePriceIndicator(series)
    sma = SMAIndicator(closePriceIndicator, 12)
    return BaseStrategy(
        OverIndicatorRule(sma, closePriceIndicator),
        UnderIndicatorRule(sma, closePriceIndicator))
