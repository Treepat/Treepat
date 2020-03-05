// Generated from D:/doria/Documents/GitHub/Treepat/src/main/java/antlr/TreeFormatParser\TreeFormat.g4 by ANTLR 4.8
package antlr.treeFormatParser.generate;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TreeFormatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, STRING=2, MARKLESS_STRING=3, NEWLINE=4, WS=5, INDENT=6, DEDENT=7;
	public static final int
		RULE_subtree = 0, RULE_child = 1, RULE_sibling = 2, RULE_wrapper = 3, 
		RULE_node = 4, RULE_information = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"subtree", "child", "sibling", "wrapper", "node", "information"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COLON", "STRING", "MARKLESS_STRING", "NEWLINE", "WS", "INDENT", 
			"DEDENT"
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
	public String getGrammarFileName() { return "TreeFormat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TreeFormatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SubtreeContext extends ParserRuleContext {
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public ChildContext child() {
			return getRuleContext(ChildContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TreeFormatParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TreeFormatParser.NEWLINE, i);
		}
		public SubtreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterSubtree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitSubtree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitSubtree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtreeContext subtree() throws RecognitionException {
		SubtreeContext _localctx = new SubtreeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_subtree);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			node();
			setState(16);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(13);
					match(NEWLINE);
					}
					} 
				}
				setState(18);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(19);
			child();
			setState(20);
			node();
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(21);
				match(NEWLINE);
				}
				}
				setState(26);
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

	public static class ChildContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TreeFormatParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(TreeFormatParser.INDENT, 0); }
		public SiblingContext sibling() {
			return getRuleContext(SiblingContext.class,0);
		}
		public TerminalNode DEDENT() { return getToken(TreeFormatParser.DEDENT, 0); }
		public ChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_child; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitChild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitChild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChildContext child() throws RecognitionException {
		ChildContext _localctx = new ChildContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_child);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(NEWLINE);
			setState(28);
			match(INDENT);
			setState(29);
			sibling();
			setState(30);
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
		public List<WrapperContext> wrapper() {
			return getRuleContexts(WrapperContext.class);
		}
		public WrapperContext wrapper(int i) {
			return getRuleContext(WrapperContext.class,i);
		}
		public SiblingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sibling; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterSibling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitSibling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitSibling(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SiblingContext sibling() throws RecognitionException {
		SiblingContext _localctx = new SiblingContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sibling);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				wrapper();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING || _la==MARKLESS_STRING );
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

	public static class WrapperContext extends ParserRuleContext {
		public SubtreeContext subtree() {
			return getRuleContext(SubtreeContext.class,0);
		}
		public WrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wrapper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitWrapper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WrapperContext wrapper() throws RecognitionException {
		WrapperContext _localctx = new WrapperContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
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

	public static class NodeContext extends ParserRuleContext {
		public InformationContext name;
		public InformationContext tag;
		public TerminalNode COLON() { return getToken(TreeFormatParser.COLON, 0); }
		public List<InformationContext> information() {
			return getRuleContexts(InformationContext.class);
		}
		public InformationContext information(int i) {
			return getRuleContext(InformationContext.class,i);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			((NodeContext)_localctx).name = information();
			setState(40);
			match(COLON);
			setState(41);
			((NodeContext)_localctx).tag = information();
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

	public static class InformationContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TreeFormatParser.STRING, 0); }
		public TerminalNode MARKLESS_STRING() { return getToken(TreeFormatParser.MARKLESS_STRING, 0); }
		public InformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_information; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).enterInformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreeFormatListener ) ((TreeFormatListener)listener).exitInformation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreeFormatVisitor ) return ((TreeFormatVisitor<? extends T>)visitor).visitInformation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InformationContext information() throws RecognitionException {
		InformationContext _localctx = new InformationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_information);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==MARKLESS_STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t\60\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\7\2\21\n\2\f\2\16\2\24\13"+
		"\2\3\2\3\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\3\3\3\3\3\3\3\3\3\3\4\6\4"+
		"$\n\4\r\4\16\4%\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f"+
		"\2\3\3\2\4\5\2,\2\16\3\2\2\2\4\35\3\2\2\2\6#\3\2\2\2\b\'\3\2\2\2\n)\3"+
		"\2\2\2\f-\3\2\2\2\16\22\5\n\6\2\17\21\7\6\2\2\20\17\3\2\2\2\21\24\3\2"+
		"\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\25\3\2\2\2\24\22\3\2\2\2\25\26\5\4"+
		"\3\2\26\32\5\n\6\2\27\31\7\6\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2"+
		"\2\2\32\33\3\2\2\2\33\3\3\2\2\2\34\32\3\2\2\2\35\36\7\6\2\2\36\37\7\b"+
		"\2\2\37 \5\6\4\2 !\7\t\2\2!\5\3\2\2\2\"$\5\b\5\2#\"\3\2\2\2$%\3\2\2\2"+
		"%#\3\2\2\2%&\3\2\2\2&\7\3\2\2\2\'(\5\2\2\2(\t\3\2\2\2)*\5\f\7\2*+\7\3"+
		"\2\2+,\5\f\7\2,\13\3\2\2\2-.\t\2\2\2.\r\3\2\2\2\5\22\32%";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}