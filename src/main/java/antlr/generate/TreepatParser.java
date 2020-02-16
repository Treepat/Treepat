// Generated from /Users/johanmurillo/Development/Tesis/AntlrJava/Treepat/src/main/java/antlr/Treepat.g4 by ANTLR 4.8
package antlr.generate;

	import java.util.Map;
	import java.util.List;
	import java.util.ArrayList;

	import ast.*;
	import tree.*;

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
		RULE_sibling = 4, RULE_union = 5, RULE_subtree_wrapper = 6, RULE_depth_closure = 7, 
		RULE_simple_expression = 8, RULE_depth_term = 9, RULE_breadth_closure = 10, 
		RULE_term = 11, RULE_node = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "subtree", "expression", "child", "sibling", "union", "subtree_wrapper", 
			"depth_closure", "simple_expression", "depth_term", "breadth_closure", 
			"term", "node"
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


	    TargetTreeNode targetTreeNode = new ImpTargetTreeNode();

	public TreepatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ModelContext extends ParserRuleContext {
		public SubtreeContext subtree;
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
			((ModelContext)_localctx).subtree = subtree();

			            ((ModelContext)_localctx).subtree.nodeAST.execute(targetTreeNode);
				
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
		public ASTNode nodeAST;
		public ExpressionContext expression;
		public ChildContext child;
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
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				((SubtreeContext)_localctx).expression = expression();
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(30);
						match(NEWLINE);
						}
						} 
					}
					setState(35);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(36);
				((SubtreeContext)_localctx).child = child();

				    	((SubtreeContext)_localctx).nodeAST =  new Child(((SubtreeContext)_localctx).expression.nodeAST, ((SubtreeContext)_localctx).child.nodeAST);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				((SubtreeContext)_localctx).expression = expression();
				setState(43);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(40);
						match(NEWLINE);
						}
						} 
					}
					setState(45);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}

				        ((SubtreeContext)_localctx).nodeAST =  ((SubtreeContext)_localctx).expression.nodeAST;
				    
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
		public ASTNode nodeAST;
		public Simple_expressionContext simple_expression;
		public Depth_closureContext depth_closure;
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public Depth_closureContext depth_closure() {
			return getRuleContext(Depth_closureContext.class,0);
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
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT_SIGN:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((ExpressionContext)_localctx).simple_expression = simple_expression();

					    ((ExpressionContext)_localctx).nodeAST =  ((ExpressionContext)_localctx).simple_expression.nodeAST;
					
				}
				break;
			case NEWLINE:
			case PAR_OPEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				((ExpressionContext)_localctx).depth_closure = depth_closure();

					    ((ExpressionContext)_localctx).nodeAST =  ((ExpressionContext)_localctx).depth_closure.nodeAST;
					
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
		public ASTNode nodeAST;
		public SiblingContext sibling;
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
			setState(58);
			match(NEWLINE);
			setState(59);
			match(INDENT);
			setState(60);
			((ChildContext)_localctx).sibling = sibling();

					    ((ChildContext)_localctx).nodeAST =  ((ChildContext)_localctx).sibling.nodeAST;
					
			setState(62);
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
		public ASTNode nodeAST;
		public UnionContext union;
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

			        List<ASTNode> siblings = new ArrayList<>();
			    
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				((SiblingContext)_localctx).union = union();

					        siblings.add(((SiblingContext)_localctx).union.nodeAST);
					    
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << PAR_OPEN) | (1L << AT_SIGN) | (1L << ID))) != 0) );

				    ((SiblingContext)_localctx).nodeAST =  new Sibling(siblings);
				
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
		public ASTNode nodeAST;
		public Subtree_wrapperContext subtree_wrapper;
		public List<Subtree_wrapperContext> subtree_wrapper() {
			return getRuleContexts(Subtree_wrapperContext.class);
		}
		public Subtree_wrapperContext subtree_wrapper(int i) {
			return getRuleContext(Subtree_wrapperContext.class,i);
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
			setState(74);
			((UnionContext)_localctx).subtree_wrapper = subtree_wrapper();

				    ((UnionContext)_localctx).nodeAST =  ((UnionContext)_localctx).subtree_wrapper.nodeAST;
				
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_SIGN) {
				{
				{
				setState(76);
				match(OR_SIGN);
				setState(77);
				((UnionContext)_localctx).subtree_wrapper = subtree_wrapper();
				}
				}
				setState(82);
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

	public static class Subtree_wrapperContext extends ParserRuleContext {
		public ASTNode nodeAST;
		public SubtreeContext subtree;
		public SubtreeContext subtree() {
			return getRuleContext(SubtreeContext.class,0);
		}
		public Subtree_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtree_wrapper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSubtree_wrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSubtree_wrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSubtree_wrapper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subtree_wrapperContext subtree_wrapper() throws RecognitionException {
		Subtree_wrapperContext _localctx = new Subtree_wrapperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_subtree_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			((Subtree_wrapperContext)_localctx).subtree = subtree();

				    ((Subtree_wrapperContext)_localctx).nodeAST =  ((Subtree_wrapperContext)_localctx).subtree.nodeAST;
				
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

	public static class Depth_closureContext extends ParserRuleContext {
		public ASTNode nodeAST;
		public ChildContext child;
		public TerminalNode PAR_OPEN() { return getToken(TreepatParser.PAR_OPEN, 0); }
		public ChildContext child() {
			return getRuleContext(ChildContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(TreepatParser.PAR_CLOSE, 0); }
		public TerminalNode NUMBER_SIGN() { return getToken(TreepatParser.NUMBER_SIGN, 0); }
		public Depth_closureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depth_closure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterDepth_closure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitDepth_closure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitDepth_closure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depth_closureContext depth_closure() throws RecognitionException {
		Depth_closureContext _localctx = new Depth_closureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_depth_closure);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAR_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(PAR_OPEN);
				setState(87);
				((Depth_closureContext)_localctx).child = child();

					        ((Depth_closureContext)_localctx).nodeAST =  ((Depth_closureContext)_localctx).child.nodeAST;
					    
				setState(89);
				match(PAR_CLOSE);
				setState(90);
				match(NUMBER_SIGN);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				child();
				setState(93);
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

	public static class Simple_expressionContext extends ParserRuleContext {
		public ASTNode nodeAST;
		public TermContext term;
		public Breadth_closureContext breadth_closure;
		public Depth_termContext depth_term;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Breadth_closureContext breadth_closure() {
			return getRuleContext(Breadth_closureContext.class,0);
		}
		public Depth_termContext depth_term() {
			return getRuleContext(Depth_termContext.class,0);
		}
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterSimple_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitSimple_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitSimple_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_simple_expression);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				((Simple_expressionContext)_localctx).term = term();

					    ((Simple_expressionContext)_localctx).nodeAST =  ((Simple_expressionContext)_localctx).term.nodeAST;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				((Simple_expressionContext)_localctx).breadth_closure = breadth_closure();

					    ((Simple_expressionContext)_localctx).nodeAST =  ((Simple_expressionContext)_localctx).breadth_closure.nodeAST;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				((Simple_expressionContext)_localctx).depth_term = depth_term();

					    ((Simple_expressionContext)_localctx).nodeAST =  ((Simple_expressionContext)_localctx).depth_term.nodeAST;
					
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

	public static class Depth_termContext extends ParserRuleContext {
		public ASTNode nodeAST;
		public TermContext term;
		public TerminalNode AT_SIGN() { return getToken(TreepatParser.AT_SIGN, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Depth_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depth_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterDepth_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitDepth_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitDepth_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depth_termContext depth_term() throws RecognitionException {
		Depth_termContext _localctx = new Depth_termContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_depth_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(AT_SIGN);
			setState(109);
			((Depth_termContext)_localctx).term = term();

				    ((Depth_termContext)_localctx).nodeAST =  ((Depth_termContext)_localctx).term.nodeAST;
				
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

	public static class Breadth_closureContext extends ParserRuleContext {
		public ASTNode nodeAST;
		public TermContext term;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(TreepatParser.ASTERISK, 0); }
		public Breadth_closureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breadth_closure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).enterBreadth_closure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TreepatListener ) ((TreepatListener)listener).exitBreadth_closure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TreepatVisitor ) return ((TreepatVisitor<? extends T>)visitor).visitBreadth_closure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Breadth_closureContext breadth_closure() throws RecognitionException {
		Breadth_closureContext _localctx = new Breadth_closureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_breadth_closure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			((Breadth_closureContext)_localctx).term = term();
			setState(113);
			match(ASTERISK);

				    ((Breadth_closureContext)_localctx).nodeAST =  ((Breadth_closureContext)_localctx).term.nodeAST;
				
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
		public ASTNode nodeAST;
		public NodeContext node;
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
			setState(116);
			((TermContext)_localctx).node = node();

				    ((TermContext)_localctx).nodeAST =  ((TermContext)_localctx).node.nodeAST;
				
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
		public ASTNode nodeAST;
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
			setState(119);
			((NodeContext)_localctx).name = match(ID);

				    ((NodeContext)_localctx).nodeAST =  new Node((((NodeContext)_localctx).name!=null?((NodeContext)_localctx).name.getText():null));
				
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\7\3\"\n\3\f\3\16\3%\13\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\5\3\63\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4;\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\6"+
		"\6G\n\6\r\6\16\6H\3\6\3\6\3\7\3\7\3\7\3\7\7\7Q\n\7\f\7\16\7T\13\7\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tb\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\nm\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2"+
		"\2\2x\2\34\3\2\2\2\4\62\3\2\2\2\6:\3\2\2\2\b<\3\2\2\2\nB\3\2\2\2\fL\3"+
		"\2\2\2\16U\3\2\2\2\20a\3\2\2\2\22l\3\2\2\2\24n\3\2\2\2\26r\3\2\2\2\30"+
		"v\3\2\2\2\32y\3\2\2\2\34\35\5\4\3\2\35\36\b\2\1\2\36\3\3\2\2\2\37#\5\6"+
		"\4\2 \"\7\3\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3"+
		"\2\2\2&\'\5\b\5\2\'(\b\3\1\2(\63\3\2\2\2)-\5\6\4\2*,\7\3\2\2+*\3\2\2\2"+
		",/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\b\3\1\2\61"+
		"\63\3\2\2\2\62\37\3\2\2\2\62)\3\2\2\2\63\5\3\2\2\2\64\65\5\22\n\2\65\66"+
		"\b\4\1\2\66;\3\2\2\2\678\5\20\t\289\b\4\1\29;\3\2\2\2:\64\3\2\2\2:\67"+
		"\3\2\2\2;\7\3\2\2\2<=\7\3\2\2=>\7\f\2\2>?\5\n\6\2?@\b\5\1\2@A\7\r\2\2"+
		"A\t\3\2\2\2BF\b\6\1\2CD\5\f\7\2DE\b\6\1\2EG\3\2\2\2FC\3\2\2\2GH\3\2\2"+
		"\2HF\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\b\6\1\2K\13\3\2\2\2LM\5\16\b\2MR\b"+
		"\7\1\2NO\7\4\2\2OQ\5\16\b\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\r"+
		"\3\2\2\2TR\3\2\2\2UV\5\4\3\2VW\b\b\1\2W\17\3\2\2\2XY\7\5\2\2YZ\5\b\5\2"+
		"Z[\b\t\1\2[\\\7\6\2\2\\]\7\7\2\2]b\3\2\2\2^_\5\b\5\2_`\7\7\2\2`b\3\2\2"+
		"\2aX\3\2\2\2a^\3\2\2\2b\21\3\2\2\2cd\5\30\r\2de\b\n\1\2em\3\2\2\2fg\5"+
		"\26\f\2gh\b\n\1\2hm\3\2\2\2ij\5\24\13\2jk\b\n\1\2km\3\2\2\2lc\3\2\2\2"+
		"lf\3\2\2\2li\3\2\2\2m\23\3\2\2\2no\7\b\2\2op\5\30\r\2pq\b\13\1\2q\25\3"+
		"\2\2\2rs\5\30\r\2st\7\t\2\2tu\b\f\1\2u\27\3\2\2\2vw\5\32\16\2wx\b\r\1"+
		"\2x\31\3\2\2\2yz\7\n\2\2z{\b\16\1\2{\33\3\2\2\2\n#-\62:HRal";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}