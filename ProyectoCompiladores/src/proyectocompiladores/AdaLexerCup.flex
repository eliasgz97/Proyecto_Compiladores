package proyectocompiladores;
import java_cup.runtime.Symbol;
%%
%class AdaLexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
%char
%caseless
%ignorecase

//caracteres
LETRA = [a-zA-Z]
NUMERO = [0-9]
//nuevos caracteres
ESPACIO = \t|\n|\s
SIGNOSESPECIALES = ","|";"|"."|":"|"'"|"!"|"?"|"¡"|"¿"|"_"|"{"|"}"|"["|"]"|"@"|"#"|"$"|"%"|"^"|"&"|"*"
ASIGNACION = ":="
DECLARACION = ":"
OPREL = "="|"/="|"<"|"<="|">"|">="
OPMULTIPLICACION = "*" | "/"
OPSUMA = "+" | "-"
PARIZQ = "("
PARDER = ")"
SEMICOLON = ";"
COMA = ","
DOSPUNTOS = ".."

//identificadores
ID = {LETRA}(_|({LETRA}|{NUMERO}))*

//numeros
NUMINT = {NUMERO}+
NUMFLOAT = {NUMERO}+"."{NUMERO}+

//comentarios
COMENTARIO = "--"({LETRA}|{NUMERO}|" "|{SIGNOSESPECIALES}|{OPREL}|{PARDER}|{PARIZQ})*

//strings
STRING = \"[^\n\"]*\"(({ESPACIO}|{ESPACIO})*"&_"{ESPACIO}(({ESPACIO}|{ESPACIO})+|\t)*\"[^\n\"]*\")*
TIPOVARIABLE = ("integer"|"float"|"boolean"|"String")
CONDICIONALES = ("and"|"or")

// Decisiones
BEGIN = "begin"
ELSE = ("else")
END = ("end")
IF = ("if")
THEN = ("then")
ELSIF = ("elsif")

// Palabras Reservadas
EXIT = ("exit")
FUNCTION = ("function")
GET = ("get")
PUT = ("put")
IN = ("in")
IS = is
NULL = ("null")
OUT = ("OUT")
PROCEDURE = procedure
RETURN = ("return")
TRUE = ("true")
FALSE = ("false")
WHEN = ("when")

// For, While y Loop
FOR = for
WHILE = ("while")
LOOP = ("loop")
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }

%}
%%
<YYINITIAL> {
    {PROCEDURE}     {return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext());}
    {IS}            {return new Symbol(sym.IS, yycolumn, yyline, yytext());}
    {BEGIN}         {return new Symbol(sym.BEGIN, yycolumn, yyline, yytext());}
    {END}           {return new Symbol(sym.END, yycolumn, yyline, yytext());}
    {TRUE}          {return new Symbol(sym.TRUE, yycolumn, yyline, yytext());}
    {FALSE}         {return new Symbol(sym.FALSE, yycolumn, yyline, yytext());}
    {PUT}           {return new Symbol(sym.PUT, yycolumn, yyline, yytext());}
    {THEN}          {return new Symbol(sym.THEN, yycolumn, yyline, yytext());}
    {IF}            {return new Symbol(sym.IF, yycolumn, yyline, yytext());}
    {ELSE}          {return new Symbol(sym.ELSE, yycolumn, yyline, yytext());}
    {ELSIF}         {return new Symbol(sym.ELSIF, yycolumn, yyline, yytext());}
    {CONDICIONALES} {return new Symbol(sym.CONDICIONALES, yycolumn, yyline, yytext());}
    {DECLARACION}   {return new Symbol(sym.DECLARACION, yycolumn, yyline, yytext());}
    {TIPOVARIABLE}  {return new Symbol(sym.TIPOVARIABLE, yycolumn, yyline, yytext());}
    {SEMICOLON}     {return new Symbol(sym.SEMICOLON, yycolumn, yyline, yytext());}  
    {PARIZQ}        {return new Symbol(sym.PARIZQ, yycolumn, yyline, yytext());}
    {PARDER}        {return new Symbol(sym.PARDER, yycolumn, yyline, yytext());}
    {ID}            {return new Symbol(sym.ID, yycolumn, yyline, yytext());}
    {NUMERO}        {return new Symbol(sym.NUMERO, yycolumn, yyline, yytext());}
    {STRING}        {return new Symbol(sym.STRING, yycolumn, yyline, yytext());}
    {OPREL}         {return new Symbol(sym.OPREL, yycolumn, yyline, yytext());}
    {ESPACIO}       {}
    .               {}
}

