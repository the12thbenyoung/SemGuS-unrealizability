;;;;
;;;; gtswitch-exp.sl - The gtswitch example problem encoded in SemGuS
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
        ($0)
        ($1)
        ($2)
        ($3)
        ($+ E E)
        ($ite B E E)
    )
    (  ; B productions
        ($t)
        ($f)
        ($not B)
        ($and B B)
        ($or B B)
        ($<= E E)
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
        ($0 (= r 0))
        ($1 (= r 1))
        ($2 (= r 2))
        ($3 (= r 3))
        (($+ et1 et2)
         (exists ((r1 Int) (r2 Int))
             (and
              (E.Sem et1 x y r1)
              (E.Sem et2 x y r2)
              (= r (+ r1 r2)))))
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
        (($t (= r true))
         ($f(= r false))
         (($not bt1)
          (exists ((rb Bool))
              (and
               (B.Sem bt1 x y rb)
               (= r(not rb)))))
         (($and bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x y rb1)
               (B.Sem bt2 x y rb2)
               (= r(and rb1 rb2)))))
         (($or bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x y rb1)
               (B.Sem bt2 x y rb2)
               (= r(or rb1 rb2)))))
         (($<= et1 et2)
          (exists ((r1 Int) (r2 Int))
              (and
               (E.Sem et1 x y r1)
               (E.Sem et2 x y r2)
               (= r(<= r1 r2)))))))
    :input (x y) :output (r))))

;;;
;;; Function to synthesize - a term rooted at E
;;;
;; (synth-fun gtswitch() E) ; Using the default universe of terms rooted at E
(synth-fun gtswitch() E
    ((Start E) (RStart E) (StartBool B) (A E) (C E)) (
        (Start E (
            ($+ C RStart)
        ))
        (RStart E (
            ($+ RStart RStart)
            ($ite StartBool A A)
        ))
        (StartBool B (
            ($<= A A)
        ))
        (A E (
            $x
            $y
        ))
        (C E (
            $3
        ))
    )
)

;;;
;;; Constraints - examples
;;; (11,12,4) = (3,3,3) + 2 (2,2,0) + (4,5,1)
(constraint (E.Sem gtswitch 4 2 11))
(constraint (E.Sem gtswitch 2 5 12))
(constraint (E.Sem gtswitch 0 1 4))

;;;
;;; Instruct the solver to find gtswitch
;;;
(check-synth)
