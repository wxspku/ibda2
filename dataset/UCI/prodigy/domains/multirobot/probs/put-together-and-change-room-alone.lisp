(load-goal ;'(and (next-to box1 block1)(in-room robot rram)))
            '(in-room robot rram))

(load-start-state
 '((connect drramrclk rram rclk)
   (connect drclkrpdp rclk rpdp)
   (statis drramrclk closed)
   (statis drclkrpdp open)
   (is-type drramrclk door)
   (is-type drclkrpdp door)
   (is-type rclk room)
   (is-type rram room)
   (is-type rpdp room)
   (is-type box1 box)
   (is-type block1 block)
   (is-type robot robot)
   (in-room box1 rclk)
   (in-room block1 rclk)
   (in-room robot rclk)
   (pushable box1)
   (pushable block1)))
