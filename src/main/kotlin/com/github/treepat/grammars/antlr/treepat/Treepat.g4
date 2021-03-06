// Define a grammar called Treepat.
grammar Treepat;


// Here starts the implementation for INDENT and DEDENT tokens.
tokens { INDENT, DEDENT }
@lexer::members {
  // A queue where extra tokens are pushed on (see the NEWLINE lexer rule).
  private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();
  // The stack that keeps track of the indentation level.
  private java.util.Stack<Integer> indents = new java.util.Stack<>();
  // The amount of opened braces, brackets and parenthesis.
  private int opened = 0;
  // The most recently produced token.
  private Token lastToken = null;
  @Override
  public void emit(Token t) {
    super.setToken(t);
    tokens.offer(t);
  }

  @Override
  public Token nextToken() {
    // Check if the end-of-file is ahead and there are still some DEDENTS expected.
    if (_input.LA(1) == EOF && !this.indents.isEmpty()) {
      // Remove any trailing EOF tokens from our buffer.
      for (int i = tokens.size() - 1; i >= 0; i--) {
        if (tokens.get(i).getType() == EOF) {
          tokens.remove(i);
        }
      }

      // First emit an extra line break that serves as the end of the statement.
      this.emit(commonToken(TreepatParser.NEWLINE, "\n"));

      // Now emit as much DEDENT tokens as needed.
      while (!indents.isEmpty()) {
        this.emit(createDedent());
        indents.pop();
      }

      // Put the EOF back on the token stream.
      this.emit(commonToken(TreepatParser.EOF, "<EOF>"));
    }

    Token next = super.nextToken();

    if (next.getChannel() == Token.DEFAULT_CHANNEL) {
      // Keep track of the last token on the default channel.
      this.lastToken = next;
    }

    return tokens.isEmpty() ? next : tokens.poll();
  }

  private Token createDedent() {
    CommonToken dedent = commonToken(TreepatParser.DEDENT, "");
    dedent.setLine(this.lastToken.getLine());
    return dedent;
  }

  private CommonToken commonToken(int type, String text) {
    int stop = this.getCharIndex() - 1;
    int start = text.isEmpty() ? stop : stop - text.length() + 1;
    return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
  }

  // Calculates the indentation of the provided spaces, taking the
  // following rules into account:
  //
  // "Tabs are replaced (from left to right) by one to eight spaces
  //  such that the total number of characters up to and including
  //  the replacement is a multiple of eight [...]"
  //
  //  -- https://docs.python.org/3.1/reference/lexical_analysis.html#indentation
  static int getIndentationCount(String spaces) {
    int count = 0;
    for (char ch : spaces.toCharArray()) {
      switch (ch) {
        case '\t':
          count += 8 - (count % 8);
          break;
        default:
          // A normal space char.
          count++;
      }
    }

    return count;
  }

  boolean atStartOfInput() {
    return super.getCharPositionInLine() == 0 && super.getLine() == 1;
  }
}

subtree
    :   sibling
    ;

depthClosure
    :   indentWrapper NUMBER_SIGN
    ;

indentWrapper
    :   indent
    |   nestedIndent
    ;

nestedIndent
    :   PAR_OPEN indent PAR_CLOSE
    ;

indent
    :   NEWLINE INDENT subtree DEDENT
    ;

sibling
    :   union+
    ;

union
    :   child (OR_SIGN child)*
    ;

child
    :   breadthClosure indentWrapper?
    ;

breadthClosure
    :   atomTerm ASTERISK? NEWLINE*
    ;

atomTerm
    :   NEWLINE* atomTermWrapper NEWLINE*
    ;

atomTermWrapper
    :   depthClosure
    |   nested
    |   depthTerm
    |   node
    ;

depthTerm
    :   AT_SIGN node
    ;

node
    :   name=ID
    |   dot
    ;

dot
    :   DOT
    ;

nested
    :   PAR_OPEN subtree PAR_CLOSE
    ;

DOT:
    '.'
    ;

OR_SIGN
    :   '|'
    ;

PAR_OPEN
    :   '('
    ;

PAR_CLOSE
    :   ')'
    ;

NUMBER_SIGN
    :   '#'
    ;

AT_SIGN
    :   '@'
    ;

ASTERISK
    :   '*'
    ;

ID
    :   [A-Za-z][A-Za-z0-9_]*
    ;

NEWLINE
    : ( {atStartOfInput()}?   SPACES
    | ( '\r'? '\n' | '\r' | '\f' ) SPACES? )
    {
       String newLine = getText().replaceAll("[^\r\n]+", "");
       String spaces = getText().replaceAll("[\r\n]+", "");
       int next = _input.LA(1);
       if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
         // If we're inside a list or on a blank line, ignore all indents,
         // dedents and line breaks.
         skip();
       }
       else {
         emit(commonToken(NEWLINE, newLine));
         int indent = getIndentationCount(spaces);
         int previous = indents.isEmpty() ? 0 : indents.peek();
         if (indent == previous) {
           // skip indents of the same size as the present indent-size
           skip();
         }
         else if (indent > previous) {
           indents.push(indent);
           emit(commonToken(TreepatParser.INDENT, spaces));
         }
         else {
           // Possibly emit more than 1 DEDENT token.
           while(!indents.isEmpty() && indents.peek() > indent) {
             this.emit(createDedent());
             indents.pop();
           }
         }
       }
     }
    ;

WS
    : SPACES+ -> skip
    ;

fragment
SPACES
    :   [ \t]+
    ;
