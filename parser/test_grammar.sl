;;;;
;;;; stolen from https://github.com/SemGuS-git/Semgus-Benchmarks/blob/master/integer-arithmetic/eq1-exp.sl

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
        ($0)
        ($1)
        ($2)
        ($+ E E)
        ($ite B E E)
    )
    (  ; B productions
        ($t)
        ($f)
        ($not B)
        ($and B B)
        ($or B B)
        ($>= E E)
    )
)
)

;;;
;;; Semantics
;;;
(define-funs-rec
    ;; CHC heads
    ; Add (unused) argument z
    ((E.Sem ((et E) (x Int) (y Int) (z Int) (r Int)) Bool)
     (B.Sem ((bt B) (x Int) (y Int) (z Int) (r Bool)) Bool))

  ;; Bodies
  ((! (match et ; E.Sem definitions
       (($x (= r x))
        ($y (= r y))
        ($0 (= r 0))
        ($1 (= r 1))
        ($2 (= r 2))
        (($+ et1 et2)
         (exists ((r1 Int) (r2 Int))
             (and
              (E.Sem et1 x y z r1)
              (E.Sem et2 x y z r2)
              (= r (+ r1 r2)))))
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
        (($t (= r true))
         ($f(= r false))
         (($not bt1)
          (exists ((rb Bool))
              (and
               (B.Sem bt1 x y z rb)
               (= r(not rb)))))
         (($and bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x y z rb1)
               (B.Sem bt2 x y z rb2)
               (= r(and rb1 rb2)))))
         (($or bt1 bt2)
          (exists ((rb1 Bool) (rb2 Bool))
              (and
               (B.Sem bt1 x y z rb1)
               (B.Sem bt2 x y z rb2)
               (= r(or rb1 rb2)))))
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
(synth-fun eq1() E
    ((Start E) (C E) (B E) (StartBool B)) (
        (Start E (
            $1 
            ($+ Start Start)
            ($ite StartBool B C)
        ))
        (B E (
            $2
        ))
        (C E (
            $x
        ))
        (StartBool B (
            ($>= B C)
        ))
    )
)

;;;
;;; Constraints - examples
;;;
(constraint (E.Sem eq1 0 4 0 1))
(constraint (E.Sem eq1 3 5 0 3))

(check-synth)