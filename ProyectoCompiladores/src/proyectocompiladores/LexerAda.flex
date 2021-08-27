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

//tokens de prueba
letra = [a-zA-Z]
numero = [0-9]
%%

<YYINITIAL> {
    {letra}    {return letra;}
    {numero}   {return numero;}
    .          {return error;}
}