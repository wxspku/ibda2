; 13 probs 3 blocks, 2 goals

(setq *TEST-PROBS*
    '((SS2-1 (and (locked dr12) (arm-empty))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (next-to B A)
       (next-to A B)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable B)
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-2 (and (dr-closed dr12) (inroom robot rm2))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable B)
       (pushable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm2)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-3 (and (inroom key12 rm1) (inroom C rm2))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable C)
       (carriable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm1)
       (inroom B rm2)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-4 (and (inroom key12 rm1) (dr-closed dr12))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm1)
       (inroom key12 rm2)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-5 (and (inroom key12 rm2) (inroom robot rm2))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable B)
       (pushable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm1)
       (inroom B rm2)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-6 (and (arm-empty) (next-to B A))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-7 (and (locked dr12) (dr-closed dr12))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (next-to B A)
       (next-to A B)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable B)
       (pushable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-8 (and (dr-open dr12) (unlocked dr12))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (dr-closed dr12)
       (locked dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable B)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-9 (and (locked dr12) (next-to A C))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (next-to A C)
       (next-to C A)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable C)
       (carriable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm2)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-10 (and (next-to B A) (inroom B rm2))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable B)
       (pushable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-11 (and (inroom key12 rm1) (inroom robot rm1))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-closed dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable B)
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-12 (next-to A B)
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (carriable B)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS2-13 (and (inroom robot rm1) (inroom B rm1))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (unlocked dr12)
       (dr-open dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable B)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))))

; unecessary
(setq *AUX-COMMANDS*
 '((SS2-2 (optimal-path (cadr *ALL-NODES*)))))

