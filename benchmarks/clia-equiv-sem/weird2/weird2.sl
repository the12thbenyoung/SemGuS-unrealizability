;;;;
;;;; plus2-exp.sl - The plus2 example problem encoded in SemGuS
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
        ($2)
        ($+ E E)
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
    ((E.Sem ((et E) (x Int) (r Int)) Bool)
     (B.Sem ((bt B) (x Int) (r Bool)) Bool))

  ;; Bodies
  ((! (match et ; E.Sem definitions
       (($x (= r x))
        ($2 (= r (+ 1 1)))
        (($+ et1 et2)
         (exists ((r1 Int) (r2 Int))
             (and
              (E.Sem et1 x r1)
              (E.Sem et2 x r2)
              (= r (+ r1 r2)))))
        ))

    :input (x) :output (r))

   (! (match bt ; B.Sem definitions
        ((($le et1 et2)
          (exists ((r1 Int) (r2 Int) (r3 Int) (r4 Int))
              (and
               (E.Sem et1 x r1)
               (E.Sem et2 x r4)
               (= r3 1)
               (= r2 (* r4 r3))
               (= r (not (> r1 r2))))))))
    :input (x) :output (r))))


;;;
;;; Function to synthesize - a term rooted at E
;;;
(synth-fun plus2() E) ; Using the default universe of terms rooted at E

;;;
;;; Constraints - examples
;;;
(constraint (E.Sem plus2 1 7))
(constraint (E.Sem plus2 3 9))
(constraint (E.Sem plus2 5 11))

;;;
;;; Instruct the solver to find plus2
;;;
(check-synth)
