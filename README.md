Actor Bhau

###  Message Pattern
There are **Four** type message.
1. Ask [?]
    1. Ask  sends message to actor with given timeout and return Future.
    2. No message will send back to sneder.
    3. It has some flaws always needs timeout which we can't always predict.
    4. If receiver of message falis then return Future will not complete.
    5. Beside to work like this Ask create intermediate actor, which eventually complete future promise.
2. Tell [!]
    1. Tell is method on ActorRef/ActorSelection.
    2. It will take sender() and message , and reciver return reply using sneder().
3. Forward
    1. Any message arrived and send it to another actor.
    2. Any reply to sender(), will goes to original message sender.
4. Pipe
    1. It used to send back to sender() a result of Future.
    2. It is great way as compare to Ask.
