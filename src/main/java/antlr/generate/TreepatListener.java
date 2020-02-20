// Generated from /Users/johanmurillo/Development/Tesis/AntlrJava/Treepat/src/main/java/antlr/Treepat.g4 by ANTLR 4.8
package antlr.generate;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TreepatParser}.
 */
public interface TreepatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TreepatParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(TreepatParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(TreepatParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#subtree}.
	 * @param ctx the parse tree
	 */
	void enterSubtree(TreepatParser.SubtreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#subtree}.
	 * @param ctx the parse tree
	 */
	void exitSubtree(TreepatParser.SubtreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TreepatParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TreepatParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#child}.
	 * @param ctx the parse tree
	 */
	void enterChild(TreepatParser.ChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#child}.
	 * @param ctx the parse tree
	 */
	void exitChild(TreepatParser.ChildContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#sibling}.
	 * @param ctx the parse tree
	 */
	void enterSibling(TreepatParser.SiblingContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#sibling}.
	 * @param ctx the parse tree
	 */
	void exitSibling(TreepatParser.SiblingContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(TreepatParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(TreepatParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#subtreeWrapper}.
	 * @param ctx the parse tree
	 */
	void enterSubtreeWrapper(TreepatParser.SubtreeWrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#subtreeWrapper}.
	 * @param ctx the parse tree
	 */
	void exitSubtreeWrapper(TreepatParser.SubtreeWrapperContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#depthClosure}.
	 * @param ctx the parse tree
	 */
	void enterDepthClosure(TreepatParser.DepthClosureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#depthClosure}.
	 * @param ctx the parse tree
	 */
	void exitDepthClosure(TreepatParser.DepthClosureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(TreepatParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(TreepatParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#depthTerm}.
	 * @param ctx the parse tree
	 */
	void enterDepthTerm(TreepatParser.DepthTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#depthTerm}.
	 * @param ctx the parse tree
	 */
	void exitDepthTerm(TreepatParser.DepthTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#breadthClosure}.
	 * @param ctx the parse tree
	 */
	void enterBreadthClosure(TreepatParser.BreadthClosureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#breadthClosure}.
	 * @param ctx the parse tree
	 */
	void exitBreadthClosure(TreepatParser.BreadthClosureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TreepatParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TreepatParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(TreepatParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(TreepatParser.NodeContext ctx);
}