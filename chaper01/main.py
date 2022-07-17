from chaper01.Bag import Bag
from chaper01.Invitation import Invitation
from chaper01.Ticket import Ticket

bag = Bag(amount=100, ticket=Ticket())
print(bag.plus_amount(10))
