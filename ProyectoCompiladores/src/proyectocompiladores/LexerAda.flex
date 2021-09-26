package proyectocompiladores;
import java_cup.runtime.*;
import java.util.ArrayList;
import static proyectocompiladores.Token.*;

%%
%unicode
%class Lexer
%type Token
%line
%column
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
ID = {LETRA}+("_"({LETRA}|{NUMERO})+)*

//numeros
NUMINT = {NUMERO}+
NUMFLOAT = {NUMERO}+"."{NUMERO}+

//comentarios
COMENTARIO = "--"({LETRA}|{NUMERO}|" "|{SIGNOSESPECIALES}|{OPREL}|{PARDER}|{PARIZQ})*

//strings
STRING ="\""({LETRA}|{NUMERO}|{ESPACIO}|{SIGNOSESPECIALES}|{OPREL}|{PARDER}|{PARIZQ})*"\"" 
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
IS = ("is")
NULL = ("null")
OUT = ("OUT")
PROCEDURE = ("procedure")
RETURN = ("return")
TRUE = ("true")
FALSE = ("false")
WHEN = ("when")

// For, While y Loop
FOR = ("for")
WHILE = ("while")
LOOP = ("loop")




%%

<YYINITIAL> {
    {ESPACIO}             {return ESPACIO;}
    {ASIGNACION}          {return ASIGNACION;}
    {DECLARACION}         {return DECLARACION;}
    {OPREL}               {return OPREL;}
    {OPMULTIPLICACION}    {return OPMULTIPLICACION;}
    {OPSUMA}              {return OPSUMA;}
    {PARIZQ}              {return PARIZQ;}
    {PARDER}              {return PARDER;}
    {SEMICOLON}           {return SEMICOLON;}
    {COMA}                {return COMA;}
    {DOSPUNTOS}           {return DOSPUNTOS;}
    {NUMINT}              {return NUMINT;}
    {NUMFLOAT}            {return NUMFLOAT;}
    {COMENTARIO}          {return COMENTARIO;}
    {STRING}              {return STRING;}
    {TIPOVARIABLE}        {return TIPOVARIABLE;}
    {CONDICIONALES}       {return CONDICIONALES;}
    {BEGIN}               {return BEGIN;}
    {PROCEDURE}           {return PROCEDURE;}
    {RETURN}              {return RETURN;}
    {TRUE}                {return TRUE;}
    {FALSE}               {return FALSE;}
    {WHEN}                {return WHEN;}
    {FOR}                 {return FOR;}
    {WHILE}               {return WHILE;}
    {LOOP}                {return LOOP;}
    {ELSE}                {return ELSE;}
    {END}                 {return END;}
    {IF}                  {return IF;}
    {THEN}                {return THEN;}
    {ELSIF}               {return ELSIF;}
    {EXIT}                {return EXIT;}
    {FUNCTION}            {return FUNCTION;}
    {GET}                 {return GET;}
    {PUT}                 {return PUT;}
    {IN}                  {return IN;}
    {IS}                  {return IS;}
    {OUT}                 {return OUT;}
    {ID}                  {return ID;}
    .                     {return ERROR;}
}