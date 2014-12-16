serial-killer-mud2.0
====================

This is the new version. The us 2 version. 

Testing line update

Notes on give and get with two arguments:
- maybe instead of storing the giveRecipient and getRecipient in the sender player,
  it would be better to store the giveSender and getSender in the recipient player,
  that way they can only have one get and one give going at a time (?)
- or store the giveRecipient in the sender player and the getSender in the recipient

Also:
- If someone is giving an item, there should be a lockdown on that item until the recipient
  of the give sends a rejection or acceptance. And if someone wants to get something from 
  someone, there should be a lockdown on the item of the recipient of the get message until 
  they deny/accept the get. This way errors will be avoided if a player tries to drop that
  item or give it to someone else. Also a player should only have one get or give going at 
  anytime. Never more than one give going, never more than one get going, and never both at 
  the same time

