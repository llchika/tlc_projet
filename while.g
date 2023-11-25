grammar while;

//VARIABLE  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

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

fragment
COMMUN      : ('a'..'z'|'A'..'Z'|'0'..'9');

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

//////////////////////////////////////////////////////////////////////
                    //   Def grammaire While   //
//////////////////////////////////////////////////////////////////////

program     :  function program? ;


function    : 'function' SYMBOL ':' definition;

definition  : 'read' input '%' commands '%' 'write' output;

input       : inputSub?;

inputSub    : VARIABLE ',' inputSub | VARIABLE;

output      : VARIABLE ',' output | VARIABLE;

commands    : command(';'commands)?;

vars        : VARIABLE ',' vars | VARIABLE;

exprs       :  expression (',' exprs)?;

command     : 'nop'
            | vars ':=' exprs
            | 'if' expression 'then' commands ('else' commands)? 'fi'
            | 'while' expression 'do' commands 'od'
            | 'for' expression 'do' commands 'od'
            | 'foreach' VARIABLE 'in' expression 'do' commands 'od'
            ;

exprBase    : ( 'nil' | VARIABLE | SYMBOL )
            | ( '(' 'cons' lExpr ')' | '(' 'list' lExpr ')' )
            | ( '(' 'hd' exprBase ')' | '(' 'tl' exprBase ')' )
            | ( '(' SYMBOL lExpr ')' )
            ;

expression  : exprBase ('=?' exprBase)?;
        
lExpr       : exprBase lExpr? ;

axiome
	:	program;