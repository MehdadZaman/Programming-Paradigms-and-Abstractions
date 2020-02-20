(* Mehdad Zaman *)
(* 11232311 *)

(* Part 1 *)

(* Problem 1 part 1 *)
let rec pow x n = 
match n with
| 0 -> 1
| 1 -> x
| _ -> x * pow x (n-1);;

(* Problem 1 part 2 *)
let rec float_pow x n = 
match n with
| 0 -> 1.0
| 1 -> x
| _ -> x *. float_pow x (n-1);;

(* Problem 2 *)
let rec compress =
function 
| [] -> []
| [x1] -> [x1]
| [x1;x2] -> if x1 = x2 then [x1] else [x1; x2]
| x1::x2::xs -> if x1 = x2 then compress (x1::xs) else x1::compress (x2::xs);;

(* Problem 3 *)
let rec remove_if lst func =
match lst with
| [] -> []
| x::xs -> if func x then remove_if xs func else x::remove_if xs func;;

(* Problem 4 *)
let rec equivs_helper2 func n lst2 =
match lst2 with
| [] -> lst2 @ [[n]]
| x::xs -> 
    if (func n (List.hd x)) 
        then (n::x)::xs 
    else 
        x::equivs_helper2 func n xs;;

let rec equivs_helper1 func lst lst2 =
match lst with
| [] -> lst2
| x::xs -> let lst3 = equivs_helper2 func x lst2
in
equivs_helper1 func xs lst3;;

let rec equivs func lst1 =
match lst1 with
| [] -> [[]]
| x::xs -> equivs_helper1 func lst1 [];;

(* Problem 5 *)
let rec slice1 lst i j =
match lst with
| [] -> []
| x::xs -> if i = 0 then lst else slice1 xs (i-1) j;;

let rec slice2 lst i j =
match lst with
| [] -> []
| x::xs -> if j = 0 then [] else x::slice2 xs i (j-1);;

let slice lst i j =
if i > j then []
else let lst1 = slice1 lst i j 
    in
    slice2 lst1 i (j-i);;

(* Problem 6 *)
let composition func1 func2 y =
let x = func2 y in func1 x;;  

(* Problem 7 *)
let rec equiv_on f g lst =
match lst with
| [] -> true
| x::xs ->  if f x = g x then equiv_on f g xs else false;;

(* Problem 8 *)
let rec pairwisefilter func lst =
match lst with
| [] -> []
| [x1] -> [x1]
| x2::x3::xs -> (func x2 x3)::pairwisefilter func xs;;

(* Problem 9 *)
let rec polynomial lst n =
match lst with
| [] -> 0
| [(x1, x2)] -> x1 * (pow n x2)
| (x3, x4)::xs ->  x3 * (pow n x4) + polynomial xs n;;

(* Problem 10 *)
let rec powerset lst = 
match lst with
| [] -> [[]]
| x::xs -> let lst3 = powerset xs 
    in
    List.map (fun y -> x::y) lst3 @ lst3;;