from datetime import datetime
from __future__ import annotations

from chapter02.Customer import Customer
from chapter02.Money import Money
from chapter02.Reservation import Reservation


class Screening:
    def __init__(self):
        pass

    def get_start_time(self) -> datetime:
        pass

    def get_movie_fee(self) -> Money:
        pass

    def is_sequence(self, screening: Screening) -> bool:
        pass

    def __calculate_fee(self, audience_count) -> Money:
        return self._movie.calculato_movie_fee(self).times(audience_count)

    def reserve(self, customer: Customer, audience_count: int) -> Reservation:
        return Reservation(customer, self, self.__calculate_fee(audience_count), audience_count)
