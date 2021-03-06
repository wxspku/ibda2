; 14 probs, max 3 blocks, max 2 goals
(setq *TEST-PROBS*
    '((SS1-1
      (unlocked dr12)
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
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm1)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-2 
      (and (dr-closed dr12) (inroom B rm2))
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
       (inroom C rm2)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-3 (and (unlocked dr12) (arm-empty))
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
       (pushable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm1)
       (inroom B rm2)
       (inroom A rm2)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-4 (and (inroom C rm2) (inroom robot rm1))
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
       (pushable C)
       (carriable B)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm1)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-5 (and (inroom robot rm2) (inroom A rm1))
      ((arm-empty)
       (dr-to-rm dr12 rm2)
       (dr-to-rm dr12 rm1)
       (connects dr12 rm2 rm1)
       (connects dr12 rm1 rm2)
       (next-to B A)
       (next-to A B)
       (dr-closed dr12)
       (locked dr12)
       (is-room rm2)
       (is-room rm1)
       (is-door dr12)
       (pushable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm2)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-6 (and (next-to A C) (arm-empty))
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
       (pushable C)
       (pushable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm2)
       (inroom B rm1)
       (inroom A rm1)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-7 (and (inroom key12 rm2) (arm-empty))
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
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-8 (and (inroom robot rm2) (inroom A rm2))
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
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm1)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-9 (and (next-to C A) (next-to A C))
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
       (carriable C)
       (pushable A)
       (is-object key12)
       (is-object C)
       (is-object B)
       (is-object A)
       (inroom C rm2)
       (inroom B rm2)
       (inroom A rm2)
       (inroom key12 rm2)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-10 (and (inroom A rm1) (inroom robot rm1))
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
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-11 (and (dr-open dr12) (inroom B rm2))
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
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-12 (and (dr-closed dr12) (inroom key12 rm2))
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
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-13 (and (dr-closed dr12) (inroom A rm1))
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
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))
     (SS1-14 (and (inroom robot rm1) (inroom B rm2))
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
       (carriable A)
       (is-object key12)
       (is-object B)
       (is-object A)
       (inroom B rm1)
       (inroom A rm2)
       (inroom key12 rm1)
       (inroom robot rm2)
       (carriable key12)
       (is-key dr12 key12)))))

(setq *AUX-COMMANDS*
  '((SS1-12 (optimal-path (cadr *ALL-NODES*)))))

; doesnt come up ;    (SS1-13 (no-optimal-path (cadr ALL-NODES)))))
 ; 'N19

