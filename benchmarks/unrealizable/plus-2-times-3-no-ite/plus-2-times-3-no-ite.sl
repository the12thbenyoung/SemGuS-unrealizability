;;;;
;;;; plus2times3-exp.sl - The plus2times3 example problem encoded in SemGuS
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
        ($+ E E)
    )
    (  ; B productions
        ($not B)
        ($and B B)
        ($or B B)
        ($< E E)
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
        (($+ et1 et2)
         (exists ((r1 Int) (r2 Int))
             (and
              (E.Sem et1 x r1)
              (E.Sem et2 x r2)
              (= r (+ r1 r2)))))
        ))

    :input (x) :output (r))

   (! (match bt ; B.Sem definitions
        ((($not bt1)
          (exists ((rb Bool))
              (and
               (B.Sem bt1 x rb)
               (= r(not rb)))))
         (($and bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x rb1)
               (B.Sem bt2 x rb2)
               (= r(and rb1 rb2)))))
         (($or bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x rb1)
               (B.Sem bt2 x rb2)
               (= r(or rb1 rb2)))))
         (($< et1 et2)
          (exists ((r1 Int) (r2 Int))
              (and
               (E.Sem et1 x r1)
               (E.Sem et2 x r2)
               (= r(< r1 r2)))))))
    :input (x) :output (r))))


;;;
;;; Function to synthesize - a term rooted at E
;;;
(synth-fun plus2times3() E) ; Using the default universe of terms rooted at E

;;;
;;; Constraints - examples
;;;
(constraint (E.Sem plus2times3 1 9))
(constraint (E.Sem plus2times3 2 12))
(constraint (E.Sem plus2times3 3 15))
(constraint (E.Sem plus2times3 4 18))
(constraint (E.Sem plus2times3 5 21))

;;;
;;; Instruct the solver to find plus2times3
;;;
(check-synth)
