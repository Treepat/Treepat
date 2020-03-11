// Generated from /Users/johanmurillo/Development/Tesis/AntlrJava/Treepat/src/main/java/antlr/Treepat.g4 by ANTLR 4.8
package antlr.treepatParser.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TreepatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR_SIGN=1, PAR_OPEN=2, PAR_CLOSE=3, NUMBER_SIGN=4, AT_SIGN=5, ASTERISK=6, 
		ID=7, NEWLINE=8, WS=9, INDENT=10, DEDENT=11;
	public static final int
		RULE_subtree = 0, RULE_expression = 1, RULE_child = 2, RULE_sibling = 3, 
		RULE_union = 4, RULE_subtreeWrapper = 5, RULE_depthClosure = 6, RULE_simpleExpression = 7, 
		RULE_depthTerm = 8, RULE_breadthClosure = 9, RULE_term = 10, RULE_node = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"subtree", "expression", "child", "sibling", "union", "subtreeWrapper", 
			"depthClosure", "simpleExpression", "depthTerm", "breadthClosure", "term", 
			"node"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|'", "'('", "')'", "'#'", "'@'", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OR_SIGN", "PAR_OPEN", "PAR_CLOSE", "NUMBER_SIGN", "AT_SIGN", "ASTERISK", 
			"ID", "NEWLINE", "WS", "INDENT", "DEDENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Treepat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TreepatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SubtreeContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChildContext child() {
			return getRuleContext(ChildContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TreepatParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TreepatParser.NEWLINE, i);
		}
		public SubtreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSubtree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSubtree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSubtree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtreeContext subtree() throws RecognitionException {
		SubtreeContext _localctx = new SubtreeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_subtree);
		try {
			int _alt;
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				expression();
				setState(28);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(25);
						match(NEWLINE);
						}
						} 
					}
					setState(30);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(31);
				child();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				expression();
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(34);
						match(NEWLINE);
						}
						} 
					}
					setState(39);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public DepthClosureContext depthClosure() {
			return getRuleContext(DepthClosureContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT_SIGN:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				simpleExpression();
				}
				break;
			case PAR_OPEN:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				depthClosure();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChildContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TreepatParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(TreepatParser.INDENT, 0); }
		public SiblingContext sibling() {
			return getRuleContext(SiblingContext.class,0);
		}
		public TerminalNode DEDENT() { return getToken(TreepatParser.DEDENT, 0); }
		public ChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_child; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitChild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitChild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChildContext child() throws RecognitionException {
		ChildContext _localctx = new ChildContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_child);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(NEWLINE);
			setState(47);
			match(INDENT);
			setState(48);
			sibling();
			setState(49);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SiblingContext extends ParserRuleContext {
		public List<UnionContext> union() {
			return getRuleContexts(UnionContext.class);
		}
		public UnionContext union(int i) {
			return getRuleContext(UnionContext.class,i);
		}
		public SiblingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sibling; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSibling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSibling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSibling(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SiblingContext sibling() throws RecognitionException {
		SiblingContext _localctx = new SiblingContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sibling);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				union();
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAR_OPEN) | (1L << AT_SIGN) | (1L << ID) | (1L << NEWLINE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public List<SubtreeWrapperContext> subtreeWrapper() {
			return getRuleContexts(SubtreeWrapperContext.class);
		}
		public SubtreeWrapperContext subtreeWrapper(int i) {
			return getRuleContext(SubtreeWrapperContext.class,i);
		}
		public List<TerminalNode> OR_SIGN() { return getTokens(TreepatParser.OR_SIGN); }
		public TerminalNode OR_SIGN(int i) {
			return getToken(TreepatParser.OR_SIGN, i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitUnion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			subtreeWrapper();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_SIGN) {
				{
				{
				setState(57);
				match(OR_SIGN);
				setState(58);
				subtreeWrapper();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubtreeWrapperContext extends ParserRuleContext {
		public SubtreeContext subtree() {
			return getRuleContext(SubtreeContext.class,0);
		}
		public SubtreeWrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtreeWrapper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSubtreeWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSubtreeWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSubtreeWrapper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtreeWrapperContext subtreeWrapper() throws RecognitionException {
		SubtreeWrapperContext _localctx = new SubtreeWrapperContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_subtreeWrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			subtree();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DepthClosureContext extends ParserRuleContext {
		public TerminalNode PAR_OPEN() { return getToken(TreepatParser.PAR_OPEN, 0); }
		public ChildContext child() {
			return getRuleContext(ChildContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(TreepatParser.PAR_CLOSE, 0); }
		public TerminalNode NUMBER_SIGN() { return getToken(TreepatParser.NUMBER_SIGN, 0); }
		public DepthClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depthClosure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterDepthClosure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitDepthClosure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitDepthClosure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DepthClosureContext depthClosure() throws RecognitionException {
		DepthClosureContext _localctx = new DepthClosureContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_depthClosure);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAR_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(PAR_OPEN);
				setState(67);
				child();
				setState(68);
				match(PAR_CLOSE);
				setState(69);
				match(NUMBER_SIGN);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				child();
				setState(72);
				match(NUMBER_SIGN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleExpressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public BreadthClosureContext breadthClosure() {
			return getRuleContext(BreadthClosureContext.class,0);
		}
		public DepthTermContext depthTerm() {
			return getRuleContext(DepthTermContext.class,0);
		}
		public SimpleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSimpleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSimpleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSimpleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleExpressionContext simpleExpression() throws RecognitionException {
		SimpleExpressionContext _localctx = new SimpleExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_simpleExpression);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				breadthClosure();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				depthTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DepthTermContext extends ParserRuleContext {
		public TerminalNode AT_SIGN() { return getToken(TreepatParser.AT_SIGN, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public DepthTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depthTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterDepthTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitDepthTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitDepthTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DepthTermContext depthTerm() throws RecognitionException {
		DepthTermContext _localctx = new DepthTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_depthTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(AT_SIGN);
			setState(82);
			term();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreadthClosureContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(TreepatParser.ASTERISK, 0); }
		public BreadthClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breadthClosure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterBreadthClosure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitBreadthClosure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitBreadthClosure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreadthClosureContext breadthClosure() throws RecognitionException {
		BreadthClosureContext _localctx = new BreadthClosureContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_breadthClosure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			term();
			setState(85);
			match(ASTERISK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			node();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(TreepatParser.ID, 0); }
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			((NodeContext)_localctx).name = match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\2\3\2\3\2\3\2\7\2&"+
		"\n\2\f\2\16\2)\13\2\5\2+\n\2\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\6\5\67\n\5\r\5\16\58\3\6\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bM\n\b\3\t\3\t\3\t\5\tR\n\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\2\2\2Z\2*\3\2\2\2\4.\3\2\2\2\6\60\3\2\2\2\b\66\3\2\2\2\n:\3\2\2\2"+
		"\fB\3\2\2\2\16L\3\2\2\2\20Q\3\2\2\2\22S\3\2\2\2\24V\3\2\2\2\26Y\3\2\2"+
		"\2\30[\3\2\2\2\32\36\5\4\3\2\33\35\7\n\2\2\34\33\3\2\2\2\35 \3\2\2\2\36"+
		"\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3\2\2\2!\"\5\6\4\2\"+\3\2\2"+
		"\2#\'\5\4\3\2$&\7\n\2\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(+\3"+
		"\2\2\2)\'\3\2\2\2*\32\3\2\2\2*#\3\2\2\2+\3\3\2\2\2,/\5\20\t\2-/\5\16\b"+
		"\2.,\3\2\2\2.-\3\2\2\2/\5\3\2\2\2\60\61\7\n\2\2\61\62\7\f\2\2\62\63\5"+
		"\b\5\2\63\64\7\r\2\2\64\7\3\2\2\2\65\67\5\n\6\2\66\65\3\2\2\2\678\3\2"+
		"\2\28\66\3\2\2\289\3\2\2\29\t\3\2\2\2:?\5\f\7\2;<\7\3\2\2<>\5\f\7\2=;"+
		"\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\13\3\2\2\2A?\3\2\2\2BC\5\2\2\2"+
		"C\r\3\2\2\2DE\7\4\2\2EF\5\6\4\2FG\7\5\2\2GH\7\6\2\2HM\3\2\2\2IJ\5\6\4"+
		"\2JK\7\6\2\2KM\3\2\2\2LD\3\2\2\2LI\3\2\2\2M\17\3\2\2\2NR\5\26\f\2OR\5"+
		"\24\13\2PR\5\22\n\2QN\3\2\2\2QO\3\2\2\2QP\3\2\2\2R\21\3\2\2\2ST\7\7\2"+
		"\2TU\5\26\f\2U\23\3\2\2\2VW\5\26\f\2WX\7\b\2\2X\25\3\2\2\2YZ\5\30\r\2"+
		"Z\27\3\2\2\2[\\\7\t\2\2\\\31\3\2\2\2\n\36\'*.8?LQ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}