;;;;
;;;; max2-exp.sl - The max2 example problem encoded in SemGuS
;;;;

;;; Metadata
(set-info :format-version "2.1.0")
(set-info :author("Jinwoo Kim" "Keith Johnson" "Wiley Corning"))
(set-info :realizable true)

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
        ($ite B E E)
    )
    (  ; B productions
        ($le E E)
    )
)
)

;;;
;;; Semantics
;;;
(define-funs-rec
    ;; CHC heads
    ((E.Sem ((et E) (x Int) (y Int) (r Int)) Bool)
     (B.Sem ((bt B) (x Int) (y Int) (r Bool)) Bool))

  ;; Bodies
  ((! (match et ; E.Sem definitions
       (($x (= r x))
        ($y (= r y))
        (($ite t1 t2 t3)
            (exists ((b Bool)) (and
                (B.Sem t1 x y b)
                (= b true)
                (E.Sem t2 x y r)
            ))
            (exists ((b Bool)) (and
                (B.Sem t1 x y b)
                (= b false)
                (E.Sem t3 x y r)
            ))
        )))

    :input (x y) :output (r))

   (! (match bt ; B.Sem definitions
        ((($le et1 et2)
          (exists ((r1 Int) (r2 Int) (r3 Int) (r4 Int))
              (and
               (E.Sem et1 x y r1)
               (E.Sem et2 x y r4)
               (= r3 1)
               (= r2 (* r4 r3))
               (= r (not (> r1 r2))))))))
    :input (x y) :output (r))))


;;;
;;; Function to synthesize - a term rooted at E
;;;
(synth-fun max2() E) ; Using the default universe of terms rooted at E

;;;
;;; Constraints - examples
;;;
(constraint (E.Sem max2 4 2 4))
(constraint (E.Sem max2 2 5 5))
(constraint (E.Sem max2 1 1 1))

;;;
;;; Instruct the solver to find max2
;;;
(check-synth)
