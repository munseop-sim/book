from chapter02.Discount import DiscountPolicy
from chapter02.Money import Money
from chapter02.Screening import Screening


class Movie:
    def __init__(self, title: str, running_time, fee: Money, discount_policy: DiscountPolicy):
        self._title = title
        self._running_time = running_time
        self._fee = fee
        self._discount_policy = discount_policy

    def get_fee(self) -> Money:
        return self._fee

    def calculate_movie_fee(self, screening: Screening) -> Money:
        return self._fee.minus(self._discount_policy.calculate_discount_amount(screening))
