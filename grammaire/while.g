grammar while;

options {
    output=AST;
}

tokens {
    PROGRAM;
    FUNCTION;
    FUNCTION_NAME;
    DEFINITION;
    INPUT;
    VARIABLE;
    OUTPUT;
    COMMANDS;
    VARS;
    EXPRESSIONS;
    NOP;
    IF;
    WHILE;
    FOR;
    FOREACH;
    NIL;
    SYMBOL;
    CONS;
    LIST;
    HD;
    TL;
    SYMBOL_EXPR;
    EXPRESSION;
    LEXPR;
    
    Var;
    Function;
    Commands;
    Output;
    Input;
    Function;
    Definition;
    Corps;
    Set;
    FunCall;
    Nil;
    Args;
    Exprs;
}


@lexer::header {
    package lp;
}

@parser::header {
    package lp;
}
axiome: program ;

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


    
program
    : function program?
    ;

function
    : 'function' SYMBOL ':' definition -> ^(Function SYMBOL definition)
    ;

definition
    : 'read' input? '%' commands '%' 'write' output -> ^(Definition input ^(Output output) ^(Corps commands))
    ;

input
    : inputSub -> ^(Input inputSub)
    ;

inputSub
    : VARIABLE (',' inputSub)? -> VARIABLE inputSub?
    ;

output
    : VARIABLE( ',' output)? -> VARIABLE output?
    ;

commands
    : command(';'commands)? -> command commands?
    ;

vars
    : VARIABLE (',' vars)? -> ^(Var VARIABLE vars?)
    ;

exprs
    : expression (',' exprs)? -> ^(Exprs expression exprs?)
    ;
    
command
    : 'nop' -> ^(NOP)
    | vars ':=' exprs -> ^(Set vars exprs)
    | 'if' expression 'then' commands ('else' commands)? 'fi' -> ^(IF expression commands commands?)
    | 'while' expression 'do' commands 'od' -> ^(WHILE expression commands)
    | 'for' expression 'do' commands 'od' -> ^(FOR expression commands)
    | 'foreach' VARIABLE 'in' expression 'do' commands 'od' -> ^(FOREACH VARIABLE expression commands)
    ;

exprBase
    : 'nil' -> ^(Nil)
    | VARIABLE -> ^(Var VARIABLE)
    | SYMBOL -> ^(SYMBOL SYMBOL)
    | '(' 'cons' lExpr? ')' -> ^(FunCall 'cons' ^(Args lExpr?))
    | '(' 'list' lExpr ')' -> ^(FunCall 'list' ^(Args lExpr))
    | '(' 'hd' exprBase ')' -> ^(FunCall 'hd' ^(Args exprBase))
    | '(' 'tl' exprBase ')' -> ^(FunCall 'tl' ^(Args exprBase))
    | '(' SYMBOL lExpr? ')' -> ^(FunCall SYMBOL ^(Args lExpr?))
    ;

expression
    : exprBase   ('=?' exprBase)? -> exprBase exprBase?
    ;

lExpr
    : exprBase lExpr? -> exprBase lExpr?
    ;
