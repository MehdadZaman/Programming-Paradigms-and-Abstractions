(* Mehdad Zaman *)
(* 11232311 *)

(* Part 2 *)

(* Problem 2 *)
type stack = Stack of int list;;

let start f = f (Stack([]));;

let push n lst f =
match lst with
| Stack(lst) -> f (Stack(n::lst));;

let pop lst f  =
match lst with
| Stack([]) -> f (Stack([]))
| Stack([x]) -> f (Stack([]))
| Stack(x::xs) -> f (Stack(xs));;

let add lst f =
match lst with
| Stack([]) -> f (Stack([]))
| Stack([x]) -> f (Stack([x]))
| Stack(x::y::xs) -> f (Stack((x+y)::xs));;

let mult lst f =
match lst with
| Stack([]) -> f (Stack([]))
| Stack([x]) -> f (Stack([x]))
| Stack(x::y::xs) -> f (Stack((x*y)::xs));;

let clone lst f =
match lst with
| Stack([]) -> f (Stack([]))
| Stack([x]) -> f (Stack(x::[x]))
| Stack(x::xs) -> f (Stack(x::x::xs));;

let rec kpop_helper lst k =
match lst with
| Stack([]) -> (Stack([]))
| Stack(x::xs) -> if k<=0 then (Stack(x::xs)) else (kpop_helper (Stack(xs)) (k-1));;

let kpop lst f =
match lst with
| Stack([]) -> f (Stack([]))
| Stack([x]) -> f (Stack([]))
| Stack(x::xs) -> f (kpop_helper (Stack(xs)) x);;

let halt lst = lst;;