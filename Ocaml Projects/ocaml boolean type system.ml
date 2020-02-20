(* Mehdad Zaman *)
(* 11232311 *)

(* Part 2 *)

(* Problem 1 *)
type bool_expr =
| Lit of string
| Not of bool_expr
| And of bool_expr * bool_expr
| Or of bool_expr * bool_expr;;

let rec truth_table_helper a b expression value1 value2 =
match expression with
| Lit x -> if x = a then value1 else value2
| Not x -> not (truth_table_helper a b x value1 value2)
| And (x, y) -> (truth_table_helper a b x value1 value2) && (truth_table_helper a b y value1 value2)
| Or (x, y) ->  (truth_table_helper a b x value1 value2) || (truth_table_helper a b y value1 value2);;

let truth_table x y expression =
[(true, true, truth_table_helper x y expression true true)] @
[(true, false, truth_table_helper x y expression true false)] @
[(false, true, truth_table_helper x y expression false true)] @
[(false, false, truth_table_helper x y expression false false)];;