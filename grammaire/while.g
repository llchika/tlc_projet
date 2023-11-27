grammar while;

options {
    output=AST;
}

tokens {
    Instructions;
    Input;
    Output;
    Set;
    Condition;
    Else;
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

definition  : 'read' input '%' commands '%' 'write' output -> ^(Input input) ^(Instructions commands) ^(Output output);

input       : inputSub;

inputSub    : VARIABLE ',' inputSub -> VARIABLE inputSub | VARIABLE ;

output      : VARIABLE ',' output  -> VARIABLE output | VARIABLE;

commands    : command(';'commands)? -> command commands?;

vars        : VARIABLE ',' vars| VARIABLE;

exprs       :  expression (',' exprs)?;

command     : 'nop'
            | vars ':=' exprs -> ^(Set vars exprs)
            | 'if' expression 'then' commands ('else' commands)? 'fi'
            | 'while' expression 'do' commands 'od'
            | 'for' expression 'do' commands 'od'
            | 'foreach' VARIABLE 'in' expression 'do' commands 'od'
            ;

exprBase    : ( 'nil' | VARIABLE | SYMBOL )
            | ( '(' 'cons' exprBase exprBase? ')' -> ^('cons' exprBase exprBase?)
            | '(' 'list' lExpr ')' )
            | ( '(' 'hd' exprBase ')' 
            | '(' 'tl' exprBase ')' )
            | ( '(' SYMBOL lExpr ')' )
            ;

expression  : exprBase ('=?' exprBase)?;
        
lExpr       : exprBase lExpr? ;