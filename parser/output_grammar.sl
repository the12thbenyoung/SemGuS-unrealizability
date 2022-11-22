(declare-term-types
((E 0) (B 0))
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
(define-funs-rec
	((E.sem ((et E)  (      ( x  Int )  ( y  Int )  ( z  Int ) (r Int)) Bool)