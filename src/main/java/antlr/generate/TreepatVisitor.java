// Generated from /Users/johanmurillo/Development/Tesis/AntlrJava/Treepat/src/main/java/antlr/Treepat.g4 by ANTLR 4.8
package antlr.generate;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TreepatParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TreepatVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TreepatParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(TreepatParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#subtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtree(TreepatParser.SubtreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TreepatParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#child}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChild(TreepatParser.ChildContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#sibling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSibling(TreepatParser.SiblingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(TreepatParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#subtree_wrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtree_wrapper(TreepatParser.Subtree_wrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#depth_closure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepth_closure(TreepatParser.Depth_closureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#simple_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_expression(TreepatParser.Simple_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#depth_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepth_term(TreepatParser.Depth_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#breadth_closure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreadth_closure(TreepatParser.Breadth_closureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(TreepatParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(TreepatParser.NodeContext ctx);
}