grammar task5;

//start: e+EOF {if($e.val==4) System.out.println("the result is "+$e.val);};
//e returns[int val]:term expr[$term.val] {$val = $expr.val;};
//expr [int inh] returns [int val] : '+' term E1 = expr [$inh + $term.val] { $val = $E1.val; }|'-' term E1 = expr [$inh -$term.val] { $val = $E1.val; }| { $val = $inh; };
//term returns [int val]:NUM {$val = Integer.parseInt($NUM.text);};
//NUM : ('0' ..'9') +;

start:e+EOF{System.out.println("the result is "+$e.val1);};
e returns[int val1,int val2]:term {$val1 = 1; $val2=$term.val;}|
'('term','E1=e','E2=e')'{if(($term.val >= $E1.val2 || $E1.val2==-1)&& ($term.val <=$E2.val2|| $E2.val2==-1) ) {$val1=$E1.val1 * $E2.val1;} else {$val1=0;}$val2=$term.val;};
term returns [int val]:NUM {if($NUM.text.equals("nil"))$val=-1; else $val = Integer.parseInt($NUM.text);};
NUM : ('0' ..'9') +|'nil';
WS  : [ \r \n \t] + -> skip;
