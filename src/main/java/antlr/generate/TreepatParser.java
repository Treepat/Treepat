// Generated from /Users/johanmurillo/Development/Tesis/AntlrJava/Treepat/src/main/java/antlr/Treepat.g4 by ANTLR 4.8
package antlr.generate;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TreepatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NEWLINE=1, OR_SIGN=2, PAR_OPEN=3, PAR_CLOSE=4, NUMBER_SIGN=5, AT_SIGN=6, 
		ASTERISK=7, ID=8, WS=9, INDENT=10, DEDENT=11;
	public static final int
		RULE_model = 0, RULE_subtree = 1, RULE_expression = 2, RULE_child = 3, 
		RULE_sibling = 4, RULE_union = 5, RULE_subtreeWrapper = 6, RULE_depthClosure = 7, 
		RULE_simpleExpression = 8, RULE_depthTerm = 9, RULE_breadthClosure = 10, 
		RULE_term = 11, RULE_node = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "subtree", "expression", "child", "sibling", "union", "subtreeWrapper", 
			"depthClosure", "simpleExpression", "depthTerm", "breadthClosure", "term", 
			"node"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'|'", "'('", "')'", "'#'", "'@'", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NEWLINE", "OR_SIGN", "PAR_OPEN", "PAR_CLOSE", "NUMBER_SIGN", "AT_SIGN", 
			"ASTERISK", "ID", "WS", "INDENT", "DEDENT"
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

	public static class ModelContext extends ParserRuleContext {
		public SubtreeContext subtree() {
			return getRuleContext(SubtreeContext.class,0);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
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
		enterRule(_localctx, 2, RULE_subtree);
		try {
			int _alt;
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				expression();
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(29);
						match(NEWLINE);
						}
						} 
					}
					setState(34);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(35);
				child();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				expression();
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(38);
						match(NEWLINE);
						}
						} 
					}
					setState(43);
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
		enterRule(_localctx, 4, RULE_expression);
		try {
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT_SIGN:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				simpleExpression();
				}
				break;
			case NEWLINE:
			case PAR_OPEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
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
		enterRule(_localctx, 6, RULE_child);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(NEWLINE);
			setState(51);
			match(INDENT);
			setState(52);
			sibling();
			setState(53);
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
		enterRule(_localctx, 8, RULE_sibling);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				union();
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << PAR_OPEN) | (1L << AT_SIGN) | (1L << ID))) != 0) );
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
		enterRule(_localctx, 10, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			subtreeWrapper();
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_SIGN) {
				{
				{
				setState(61);
				match(OR_SIGN);
				setState(62);
				subtreeWrapper();
				}
				}
				setState(67);
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
		enterRule(_localctx, 12, RULE_subtreeWrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
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
		enterRule(_localctx, 14, RULE_depthClosure);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAR_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(PAR_OPEN);
				setState(71);
				child();
				setState(72);
				match(PAR_CLOSE);
				setState(73);
				match(NUMBER_SIGN);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				child();
				setState(76);
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
		enterRule(_localctx, 16, RULE_simpleExpression);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				breadthClosure();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
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
		enterRule(_localctx, 18, RULE_depthTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(AT_SIGN);
			setState(86);
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
		enterRule(_localctx, 20, RULE_breadthClosure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			term();
			setState(89);
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
		enterRule(_localctx, 22, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
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
		enterRule(_localctx, 24, RULE_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rb\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3\3\3"+
		"\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\5\3/\n\3\3\4\3\4\5\4\63\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\6\6;\n\6\r\6\16\6<\3\7\3\7\3\7\7\7B\n\7\f\7\16\7E\13"+
		"\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tQ\n\t\3\n\3\n\3\n\5\nV\n"+
		"\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\2\2\17\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\2\2\2]\2\34\3\2\2\2\4.\3\2\2\2\6\62\3\2\2\2\b"+
		"\64\3\2\2\2\n:\3\2\2\2\f>\3\2\2\2\16F\3\2\2\2\20P\3\2\2\2\22U\3\2\2\2"+
		"\24W\3\2\2\2\26Z\3\2\2\2\30]\3\2\2\2\32_\3\2\2\2\34\35\5\4\3\2\35\3\3"+
		"\2\2\2\36\"\5\6\4\2\37!\7\3\2\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3"+
		"\2\2\2#%\3\2\2\2$\"\3\2\2\2%&\5\b\5\2&/\3\2\2\2\'+\5\6\4\2(*\7\3\2\2)"+
		"(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,/\3\2\2\2-+\3\2\2\2.\36\3\2\2"+
		"\2.\'\3\2\2\2/\5\3\2\2\2\60\63\5\22\n\2\61\63\5\20\t\2\62\60\3\2\2\2\62"+
		"\61\3\2\2\2\63\7\3\2\2\2\64\65\7\3\2\2\65\66\7\f\2\2\66\67\5\n\6\2\67"+
		"8\7\r\2\28\t\3\2\2\29;\5\f\7\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2"+
		"=\13\3\2\2\2>C\5\16\b\2?@\7\4\2\2@B\5\16\b\2A?\3\2\2\2BE\3\2\2\2CA\3\2"+
		"\2\2CD\3\2\2\2D\r\3\2\2\2EC\3\2\2\2FG\5\4\3\2G\17\3\2\2\2HI\7\5\2\2IJ"+
		"\5\b\5\2JK\7\6\2\2KL\7\7\2\2LQ\3\2\2\2MN\5\b\5\2NO\7\7\2\2OQ\3\2\2\2P"+
		"H\3\2\2\2PM\3\2\2\2Q\21\3\2\2\2RV\5\30\r\2SV\5\26\f\2TV\5\24\13\2UR\3"+
		"\2\2\2US\3\2\2\2UT\3\2\2\2V\23\3\2\2\2WX\7\b\2\2XY\5\30\r\2Y\25\3\2\2"+
		"\2Z[\5\30\r\2[\\\7\t\2\2\\\27\3\2\2\2]^\5\32\16\2^\31\3\2\2\2_`\7\n\2"+
		"\2`\33\3\2\2\2\n\"+.\62<CPU";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}