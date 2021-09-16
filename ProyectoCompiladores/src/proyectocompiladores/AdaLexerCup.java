/* The following code was generated by JFlex 1.6.1 */

package proyectocompiladores;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/EliasGZ/Documents/Clases/Compiladores/Proyecto_Compiladores/ProyectoCompiladores/src/proyectocompiladores/AdaLexerCup.flex</tt>
 */
class AdaLexerCup implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\4\1\3\1\3\1\3\22\0\1\3\1\0\1\16"+
    "\1\0\1\0\1\0\1\10\1\0\1\14\1\15\1\0\1\0\1\0"+
    "\1\0\1\0\1\12\12\2\1\6\1\5\1\13\1\11\1\13\1\0"+
    "\1\0\1\31\1\32\1\40\1\35\1\23\1\26\1\24\1\36\1\20"+
    "\2\1\1\27\1\1\1\21\1\30\1\41\1\1\1\25\1\34\1\22"+
    "\1\37\1\1\1\1\1\1\2\1\1\0\1\0\1\0\1\0\1\7"+
    "\1\0\1\31\1\32\1\40\1\35\1\23\1\26\1\24\1\36\1\20"+
    "\2\1\1\27\1\1\1\21\1\30\1\41\1\1\1\25\1\34\1\22"+
    "\1\37\1\1\1\1\1\1\2\1\1\0\1\0\1\0\7\0\1\3"+
    "\32\0\1\3\1\0\35\0\1\0\160\0\2\17\115\0\1\33\u1500\0"+
    "\1\3\u097f\0\13\3\35\0\1\3\1\3\5\0\1\3\57\0\1\3"+
    "\u0fa0\0\1\3\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ud00f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\1"+
    "\1\6\1\7\1\10\2\1\7\2\1\1\2\2\1\0"+
    "\1\11\1\0\1\12\1\13\1\2\1\12\1\13\6\2"+
    "\1\14\3\2\1\0\3\2\3\0\3\2\1\15\1\0"+
    "\5\2\1\0\2\2\1\16\2\0\1\2\1\17\1\20"+
    "\1\0\1\21\1\2\1\21\1\2\1\0\1\2\1\0"+
    "\2\2\1\0\2\2\2\0\1\2\2\22\1\23\2\24"+
    "\2\25\1\2\1\0\2\2\1\0\2\2\1\23\3\2"+
    "\1\26";

  private static int [] zzUnpackAction() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\42\0\104\0\42\0\42\0\42\0\42\0\146"+
    "\0\146\0\42\0\42\0\210\0\252\0\314\0\356\0\u0110"+
    "\0\u0132\0\u0154\0\u0176\0\u0198\0\u01ba\0\u01dc\0\u01fe\0\210"+
    "\0\u0220\0\u0242\0\42\0\42\0\u0264\0\104\0\104\0\u0286"+
    "\0\u02a8\0\u02ca\0\u02ec\0\u030e\0\u0330\0\104\0\u0352\0\u0374"+
    "\0\u0396\0\u03b8\0\u03da\0\u03fc\0\u041e\0\u0220\0\u0440\0\u0462"+
    "\0\u0484\0\u04a6\0\u04c8\0\104\0\u04ea\0\u050c\0\u052e\0\u0550"+
    "\0\u0572\0\u0594\0\u05b6\0\u05d8\0\u05fa\0\104\0\u061c\0\u063e"+
    "\0\u0660\0\104\0\104\0\u0682\0\42\0\u06a4\0\104\0\u06c6"+
    "\0\u06e8\0\u070a\0\u072c\0\u074e\0\u0770\0\u0792\0\u07b4\0\u07d6"+
    "\0\u07f8\0\u081a\0\u083c\0\42\0\104\0\104\0\42\0\104"+
    "\0\42\0\104\0\u085e\0\u0880\0\u08a2\0\u08c4\0\u08e6\0\u0908"+
    "\0\u092a\0\42\0\u094c\0\u096e\0\u0990\0\104";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\2\2\1\5\1\6\2\2\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\3"+
    "\1\17\1\20\2\3\1\21\1\3\1\22\1\23\1\24"+
    "\1\25\1\26\4\3\1\27\43\0\2\3\4\0\1\3"+
    "\10\0\13\3\1\0\6\3\11\0\1\7\30\0\4\30"+
    "\1\0\11\30\1\31\23\30\21\0\1\32\4\0\1\33"+
    "\4\0\2\34\6\0\2\3\4\0\1\3\10\0\1\3"+
    "\1\35\4\3\1\36\4\3\1\34\1\37\5\3\1\0"+
    "\2\3\4\0\1\3\10\0\5\3\1\40\5\3\1\0"+
    "\2\3\1\41\3\3\1\0\2\3\4\0\1\3\10\0"+
    "\1\3\1\42\5\3\1\43\3\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\7\3\1\44\1\3\1\45"+
    "\1\3\1\0\6\3\1\0\2\3\4\0\1\3\10\0"+
    "\5\3\1\46\5\3\1\0\6\3\1\0\2\3\4\0"+
    "\1\3\10\0\1\3\1\47\11\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\3\3\1\50\4\3\1\51"+
    "\2\3\1\0\6\3\22\0\1\52\20\0\2\3\4\0"+
    "\1\3\10\0\2\3\1\53\10\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\5\3\1\54\5\3\1\0"+
    "\3\3\1\55\2\3\3\0\2\56\3\0\1\57\53\0"+
    "\1\60\20\0\2\3\4\0\1\3\10\0\2\3\1\61"+
    "\10\3\1\0\6\3\1\0\2\3\4\0\1\3\10\0"+
    "\13\3\1\0\3\3\1\62\2\3\1\0\2\3\4\0"+
    "\1\3\10\0\3\3\1\63\7\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\13\3\1\0\1\3\1\64"+
    "\4\3\1\0\2\3\4\0\1\3\10\0\13\3\1\65"+
    "\1\66\5\3\1\0\2\3\4\0\1\3\10\0\10\3"+
    "\1\67\2\3\1\0\6\3\1\0\2\3\4\0\1\3"+
    "\10\0\7\3\1\70\3\3\1\0\6\3\1\0\2\3"+
    "\4\0\1\3\10\0\13\3\1\0\1\3\1\46\4\3"+
    "\1\0\2\3\4\0\1\3\10\0\4\3\1\71\6\3"+
    "\1\0\6\3\1\0\2\3\4\0\1\3\10\0\10\3"+
    "\1\72\2\3\1\0\6\3\25\0\1\73\15\0\2\3"+
    "\4\0\1\3\10\0\5\3\1\74\5\3\1\0\6\3"+
    "\1\0\2\3\4\0\1\3\10\0\10\3\1\75\2\3"+
    "\1\0\6\3\1\0\2\3\4\0\1\3\10\0\2\3"+
    "\1\76\10\3\1\0\6\3\7\0\1\77\55\0\1\100"+
    "\17\0\2\3\4\0\1\3\10\0\3\3\1\101\7\3"+
    "\1\0\6\3\1\0\2\3\4\0\1\3\10\0\3\3"+
    "\1\102\7\3\1\0\6\3\1\0\2\3\4\0\1\3"+
    "\10\0\1\3\1\103\11\3\1\0\6\3\17\0\2\104"+
    "\2\0\1\105\17\0\2\3\4\0\1\3\7\0\1\104"+
    "\1\106\2\3\1\107\7\3\1\0\6\3\1\0\2\3"+
    "\4\0\1\3\10\0\11\3\1\110\1\3\1\0\6\3"+
    "\1\0\2\3\4\0\1\3\10\0\13\3\1\111\1\112"+
    "\5\3\1\0\2\3\4\0\1\3\7\0\1\113\1\114"+
    "\12\3\1\0\6\3\1\0\2\3\4\0\1\3\10\0"+
    "\7\3\1\115\3\3\1\0\6\3\17\0\2\116\22\0"+
    "\2\3\4\0\1\3\7\0\1\116\1\117\12\3\1\0"+
    "\6\3\1\0\2\3\4\0\1\3\10\0\13\3\1\0"+
    "\4\3\1\120\1\3\3\0\2\121\61\0\1\122\16\0"+
    "\2\3\4\0\1\3\10\0\4\3\1\123\6\3\1\0"+
    "\6\3\26\0\1\124\14\0\2\3\4\0\1\3\10\0"+
    "\6\3\1\125\4\3\1\0\6\3\1\0\2\3\4\0"+
    "\1\3\10\0\2\3\1\126\10\3\1\0\6\3\23\0"+
    "\1\127\17\0\2\3\4\0\1\3\10\0\3\3\1\130"+
    "\7\3\1\0\6\3\21\0\1\131\21\0\2\3\4\0"+
    "\1\3\10\0\1\3\1\132\11\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\3\3\1\133\7\3\1\0"+
    "\6\3\21\0\1\134\21\0\2\3\4\0\1\3\10\0"+
    "\1\3\1\135\11\3\1\0\6\3\1\0\2\3\4\0"+
    "\1\3\10\0\3\3\1\136\7\3\1\0\6\3\3\0"+
    "\2\121\11\0\1\30\46\0\1\137\17\0\2\3\4\0"+
    "\1\3\10\0\3\3\1\140\7\3\1\0\6\3\1\0"+
    "\2\3\4\0\1\3\10\0\11\3\1\141\1\3\1\0"+
    "\6\3\24\0\1\142\16\0\2\3\4\0\1\3\10\0"+
    "\4\3\1\126\6\3\1\0\6\3\1\0\2\3\4\0"+
    "\1\3\10\0\13\3\1\0\1\3\1\143\4\3\25\0"+
    "\1\142\15\0\2\3\4\0\1\3\10\0\5\3\1\126"+
    "\5\3\1\0\6\3\1\0\2\3\4\0\1\3\10\0"+
    "\1\3\1\126\11\3\1\0\6\3\1\0\2\3\4\0"+
    "\1\3\10\0\13\3\1\0\3\3\1\144\2\3\1\0"+
    "\2\3\4\0\1\3\10\0\5\3\1\145\5\3\1\0"+
    "\6\3\1\0\2\3\4\0\1\3\10\0\3\3\1\146"+
    "\7\3\1\0\6\3";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2482];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\4\11\2\1\2\11\14\1\1\0"+
    "\1\1\1\0\2\11\15\1\1\0\3\1\3\0\4\1"+
    "\1\0\5\1\1\0\3\1\2\0\3\1\1\0\1\11"+
    "\3\1\1\0\1\1\1\0\2\1\1\0\2\1\2\0"+
    "\1\1\1\11\2\1\1\11\1\1\1\11\2\1\1\0"+
    "\2\1\1\0\2\1\1\11\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  AdaLexerCup(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 258) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { 
            }
          case 23: break;
          case 2: 
            { return new Symbol(sym.ID, yycolumn, yyline, yytext());
            }
          case 24: break;
          case 3: 
            { return new Symbol(sym.NUMERO, yycolumn, yyline, yytext());
            }
          case 25: break;
          case 4: 
            { return new Symbol(sym.SEMICOLON, yycolumn, yyline, yytext());
            }
          case 26: break;
          case 5: 
            { return new Symbol(sym.DECLARACION, yycolumn, yyline, yytext());
            }
          case 27: break;
          case 6: 
            { return new Symbol(sym.OPREL, yycolumn, yyline, yytext());
            }
          case 28: break;
          case 7: 
            { return new Symbol(sym.PARIZQ, yycolumn, yyline, yytext());
            }
          case 29: break;
          case 8: 
            { return new Symbol(sym.PARDER, yycolumn, yyline, yytext());
            }
          case 30: break;
          case 9: 
            { return new Symbol(sym.STRING, yycolumn, yyline, yytext());
            }
          case 31: break;
          case 10: 
            { return new Symbol(sym.IF, yycolumn, yyline, yytext());
            }
          case 32: break;
          case 11: 
            { return new Symbol(sym.IS, yycolumn, yyline, yytext());
            }
          case 33: break;
          case 12: 
            { return new Symbol(sym.CONDICIONALES, yycolumn, yyline, yytext());
            }
          case 34: break;
          case 13: 
            { return new Symbol(sym.END, yycolumn, yyline, yytext());
            }
          case 35: break;
          case 14: 
            { return new Symbol(sym.PUT, yycolumn, yyline, yytext());
            }
          case 36: break;
          case 15: 
            { return new Symbol(sym.TRUE, yycolumn, yyline, yytext());
            }
          case 37: break;
          case 16: 
            { return new Symbol(sym.THEN, yycolumn, yyline, yytext());
            }
          case 38: break;
          case 17: 
            { return new Symbol(sym.ELSE, yycolumn, yyline, yytext());
            }
          case 39: break;
          case 18: 
            { return new Symbol(sym.ELSIF, yycolumn, yyline, yytext());
            }
          case 40: break;
          case 19: 
            { return new Symbol(sym.TIPOVARIABLE, yycolumn, yyline, yytext());
            }
          case 41: break;
          case 20: 
            { return new Symbol(sym.FALSE, yycolumn, yyline, yytext());
            }
          case 42: break;
          case 21: 
            { return new Symbol(sym.BEGIN, yycolumn, yyline, yytext());
            }
          case 43: break;
          case 22: 
            { return new Symbol(sym.PROCEDURE, yycolumn, yyline, yytext());
            }
          case 44: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}