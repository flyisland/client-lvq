# Client side Last Value Queue Demo

## 1. Run `./gradlew build` to build this project.
## 2. Run `java -jar ./build/libs/client-lvq.jar -h amqp://localhost:5672 -t "fx/>"`

```bash
> java -jar ./build/libs/client-lvq.jar -h amqp://localhost:5672 -t "fx/>"
08:54:13.664 [main] INFO  c.s.demo.lvq.App - Connected to amqp://localhost:5672 with username default
08:54:13.668 [main] INFO  c.s.demo.lvq.App - Subscribe to TOPIC [fx/>]
08:54:13.690 [AmqpProvider :(1):[amqp://localhost:5672]] INFO  o.a.q.j.JmsConnection - Connection ID:26081935-146c-4b13-b759-a14726e017ac:1 connected to server: amqp://localhost:5672
08:54:13.732 [main] INFO  c.s.demo.lvq.App - Waiting for messages ...

```
## 3. Run './send-messages.sh' to send messages

```bash
> ./send-messages.sh
Sending '2022-04-01-08:54:17' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:17' to topic 'fx/rt/USD/JPY'

Sending '2022-04-01-08:54:18' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:18' to topic 'fx/rt/USD/JPY'

Sending '2022-04-01-08:54:19' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:19' to topic 'fx/rt/USD/JPY'

Sending '2022-04-01-08:54:21' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:21' to topic 'fx/rt/USD/JPY'

Sending '2022-04-01-08:54:22' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:22' to topic 'fx/rt/USD/JPY'

Sending '2022-04-01-08:54:23' to topic 'fx/rt/CNY/USD'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/CNY/JPY'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/CNY/EUR'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/USD/EUR'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/USD/GBP'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/USD/CAD'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/USD/CNY'
Sending '2022-04-01-08:54:23' to topic 'fx/rt/USD/JPY'
```
## 4. Check the output of the client-lvq.jar

You can see that the DumpMessageListener always gets the last message of each topic.

```bash
❯ java -jar ./build/libs/client-lvq.jar -h amqp://localhost:5672 -t "fx/>"
08:54:13.664 [main] INFO  c.s.demo.lvq.App - Connected to amqp://localhost:5672 with username default
08:54:13.668 [main] INFO  c.s.demo.lvq.App - Subscribe to TOPIC [fx/>]
08:54:13.690 [AmqpProvider :(1):[amqp://localhost:5672]] INFO  o.a.q.j.JmsConnection - Connection ID:26081935-146c-4b13-b759-a14726e017ac:1 connected to server: amqp://localhost:5672
08:54:13.732 [main] INFO  c.s.demo.lvq.App - Waiting for messages ...

---
08:54:17.338 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/CNY/USD]:2022-04-01-08:54:17

---
08:54:18.339 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/CAD]:2022-04-01-08:54:17
08:54:19.340 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/GBP]:2022-04-01-08:54:18
08:54:20.340 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/EUR]:2022-04-01-08:54:19
08:54:21.341 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/JPY]:2022-04-01-08:54:19
08:54:22.342 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/CNY]:2022-04-01-08:54:21
08:54:23.343 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/CNY/EUR]:2022-04-01-08:54:22
08:54:24.344 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/CNY/JPY]:2022-04-01-08:54:23

---
08:54:25.344 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/CAD]:2022-04-01-08:54:23
08:54:26.345 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/GBP]:2022-04-01-08:54:23
08:54:27.346 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/EUR]:2022-04-01-08:54:23
08:54:28.346 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/JPY]:2022-04-01-08:54:23
08:54:29.347 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/CNY/USD]:2022-04-01-08:54:23
08:54:30.348 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/USD/CNY]:2022-04-01-08:54:23
08:54:31.349 [pool-1-thread-1] INFO  c.s.d.l.DumpMessageListener - [fx/rt/CNY/EUR]:2022-04-01-08:54:23
```