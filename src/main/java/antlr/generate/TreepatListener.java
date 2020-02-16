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
	 * Enter a parse tree produced by {@link TreepatParser#subtree_wrapper}.
	 * @param ctx the parse tree
	 */
	void enterSubtree_wrapper(TreepatParser.Subtree_wrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#subtree_wrapper}.
	 * @param ctx the parse tree
	 */
	void exitSubtree_wrapper(TreepatParser.Subtree_wrapperContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#depth_closure}.
	 * @param ctx the parse tree
	 */
	void enterDepth_closure(TreepatParser.Depth_closureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#depth_closure}.
	 * @param ctx the parse tree
	 */
	void exitDepth_closure(TreepatParser.Depth_closureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void enterSimple_expression(TreepatParser.Simple_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void exitSimple_expression(TreepatParser.Simple_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#depth_term}.
	 * @param ctx the parse tree
	 */
	void enterDepth_term(TreepatParser.Depth_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#depth_term}.
	 * @param ctx the parse tree
	 */
	void exitDepth_term(TreepatParser.Depth_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreepatParser#breadth_closure}.
	 * @param ctx the parse tree
	 */
	void enterBreadth_closure(TreepatParser.Breadth_closureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreepatParser#breadth_closure}.
	 * @param ctx the parse tree
	 */
	void exitBreadth_closure(TreepatParser.Breadth_closureContext ctx);
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