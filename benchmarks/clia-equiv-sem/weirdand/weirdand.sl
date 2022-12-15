;;;;
;;;; stolen from https://github.com/SemGuS-git/Semgus-Benchmarks/blob/master/integer-arithmetic/doublegt-exp.sl

;;; Metadata
;; (set-info :format-version "2.1.0")
;; (set-info :author("Jinwoo Kim" "Keith Johnson" "Wiley Corning"))
;; (set-info :realizable true)

;;;
;;; Term types
;;;
(declare-term-types
;; Nonterminals
((E 0) (B 0))

;; Productions
(
    ( ; E productions
        ($x)
        ($y)
        ($z)
        ($ite B E E)
    )
    (  ; B productions
        ($and B B)
        ($>= E E)
    )
)
)

;;;
;;; Semantics
;;;
(define-funs-rec
    ;; CHC heads
    ((E.Sem ((et E) (x Int) (y Int) (z Int) (r Int)) Bool)
     (B.Sem ((bt B) (x Int) (y Int) (z Int) (r Bool)) Bool))

  ;; Bodies
  ((! (match et ; E.Sem definitions
       (($x (= r x))
        ($y (= r y))
        ($z (= r z))
        (($ite t1 t2 t3)
            (exists ((b Bool)) (and
                (B.Sem t1 x y z b)
                (= b true)
                (E.Sem t2 x y z r)
            ))
            (exists ((b Bool)) (and
                (B.Sem t1 x y z b)
                (= b false)
                (E.Sem t3 x y z r)
            ))
        )))

    :input (x y z) :output (r))

   (! (match bt ; B.Sem definitions
         ((($and bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x y z rb1)
               (B.Sem bt2 x y z rb2)
               (= r (not (or (not rb1) (not rb2)))))))
         (($>= et1 et2)
          (exists ((r1 Int) (r2 Int))
              (and
               (E.Sem et1 x y z r1)
               (E.Sem et2 x y z r2)
               (= r(>= r1 r2)))))))
    :input (x y z) :output (r))))


;;;
;;; Function to synthesize - a term rooted at E
;;;
(synth-fun doublegt() E)

;;;
;;; Constraints - examples
;;; if x geq y and x geq z then x, else y
;;;
(constraint (E.Sem doublegt 3 2 1 3))
(constraint (E.Sem doublegt 5 4 0 5))
(constraint (E.Sem doublegt 7 8 3 8))

(check-synth)