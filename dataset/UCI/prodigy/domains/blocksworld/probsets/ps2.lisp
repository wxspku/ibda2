; 10 probs max 3 goals, 4 objs

(setq *TEST-PROBS*
    '((BB2-0 (and (holding C)(on A B))
	    ((object A)
             (clear A)
             (on-table A)
             (object B)
             (clear B)
             (on-table B)
             (object C)
             (clear C)
             (on-table C)
             (arm-empty)))
     (BB2-0a (and (holding C)(on A B))
	    ((object A)
             (clear A)
             (on-table A)
             (object B)
             (clear B)
             (on-table B)
             (object C)
             (clear C)
             (on C D)
             (object D)
             (on-table D)
             (arm-empty)))
      (BB2-0b (and (holding A) (clear D))
	    ((object D)
             (on-table D)
             (object C)
             (on C D)
             (object B)
             (on-table B)
             (clear C)
             (object A)
             (clear B)
             (on-table A)
             (clear A)
             (arm-empty)))
      (BB2-1 (and (on B D) (on A C))
            ((object A)
             (clear A)
             (on-table A)
             (object D)
             (on-table D)
             (object C)
             (on C D)
             (object B)
             (on B C)
             (clear B)
             (arm-empty)))
     (BB2-2 (and (on-table A) (on C B) (clear C))
            ((object D)
             (on-table D)
             (object C)
             (on C D)
             (object B)
             (on B C)
             (clear B)
             (object A)
             (holding A)))
     (BB2-3 (and (on C A) (on-table A) (on B C))
            ((object C)
             (on-table C)
             (object A)
             (on A C)
             (clear A)
             (object B)
             (clear B)
             (on-table B)
             (arm-empty)))
     (BB2-4 (and (on B A) (on-table A) (arm-empty))
            ((object B) (clear B) (on-table B) (object A) (holding A)))
     (BB2-5 (and (on B A) (on-table A) (clear B))
            ((object B)
             (on-table B)
             (object A)
             (on A B)
             (clear A)
             (arm-empty)))
     (BB2-6 (and (on B A) (clear B))
            ((object A)
             (clear A)
             (on-table A)
             (object B)
             (clear B)
             (on-table B)
             (arm-empty)))
     (BB2-6 (and (arm-empty) (on B C) (on A B))
            ((object B)
             (clear B)
             (on-table B)
             (object C)
             (clear C)
             (on-table C)
             (object A)
             (holding A)))
     (BB2-8 (and (on-table A) (on C B) (on B D))
            ((object A)
             (clear A)
             (on-table A)
             (object B)
             (clear B)
             (on-table B)
             (object C)
             (clear C)
             (on-table C)
             (object D)
             (clear D)
             (on-table D)
             (arm-empty)))
     (BB2-9 (and (on A C) (holding B))
            ((object D)
             (on-table D)
             (object C)
             (on C D)
             (object B)
             (on B C)
             (object A)
             (on A B)
             (clear A)
             (arm-empty)))
     (BB2-10 (and (on-table B) (clear C) (holding A))
           ((object A)
            (clear A)
            (on-table A)
            (object C)
            (on-table C)
            (object B)
            (on B C)
            (clear B)
            (arm-empty)))
    (BB2-11 (and (holding A)(clear B)(on B C))
            ((object D)
             (on-table D)
             (object C)
             (on C D)
             (object B)
             (on-table B)
	     (clear C)
             (object A)
	     (clear B)
	     (on-table A)
             (clear A)
             (arm-empty)))))


(setq *AUX-COMMANDS*
  '((BB2-0b (no-optimal-path (cadr *ALL-NODES*)))))