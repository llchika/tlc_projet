grammar while;

options {
    output=AST;
}

tokens {
    Instructions;
    Input;
    Output;
    Set;
    
    BoucleFor;
    For;
    Do;
    Od;
    
    BoucleIf;
    If;
    Then;
    Else;
    
    BoucleWhile;
    While;
    
    BoucleForeach;
    Foreach;
    In;
    
    Tail;
    Head;
    List;
    IsEqual;
}

@lexer::header {
    package lp;
}

@parser::header {
    package lp;
}

axiome
	:	program;
	
fragment
COMMUN      : ('a'..'z'|'A'..'Z'|'0'..'9');

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+;

VARIABLE    : 'A'..'Z' COMMUN* ('!'|'?')?;
SYMBOL      : 'a'..'z' COMMUN* ('!'|'?')?;


INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;
    
//////////////////////////////////////////////////////////////////////
                    //   Def grammaire While   //
//////////////////////////////////////////////////////////////////////

program     :  function program? ;

function    : 'function' SYMBOL ':' definition -> ^('function' SYMBOL definition);

definition  : 'read' input? '%' commands '%' 'write' output -> ^(Input input)? ^(Instructions commands) ^(Output output);

input       : inputSub;

inputSub    : VARIABLE ',' inputSub -> VARIABLE inputSub | VARIABLE;

output      : VARIABLE ',' output  -> VARIABLE output | VARIABLE;

commands    : command(';'commands)?-> command commands?; 

vars        : VARIABLE ',' vars -> ^(VARIABLE vars)| VARIABLE;

exprs       :  expression (',' exprs)? -> ^(expression exprs?);

command     : 'nop'
            | vars ':=' exprs -> ^(Set vars exprs) 
            | 'if' expression 'then' commands ('else' commands)? 'fi'->^(BoucleIf  ^(If expression)^(Then commands) ^(Else commands)?)
            | 'while' expression 'do' commands 'od' ->^(BoucleWhile  ^(While expression)^(Do commands) )
            | 'for' expression 'do' commands 'od' ->^(BoucleFor  ^(For expression)^(Do commands) )
            | 'foreach' VARIABLE 'in' expression 'do' commands 'od' -> ^(BoucleForeach  ^(Foreach VARIABLE)^(In expression) ^(Do commands))
            ;

exprBase    : ( 'nil' | VARIABLE | SYMBOL )
            | ( '(' 'cons' exprBase exprBase? ')' -> ^('cons' exprBase exprBase?)
            | '(' 'list' lExpr ')' -> ^(List lExpr))
            | ( '(' 'hd' exprBase ')' -> ^(Head exprBase) 
            | '(' 'tl' exprBase ')' -> ^(Tail exprBase))
            | ( '(' SYMBOL lExpr? ')' -> ^(SYMBOL))
            ;

expression  : a=exprBase ('=?' b=exprBase -> ^(IsEqual $a $b) | -> $a);
        
lExpr       : exprBase lExpr? ;