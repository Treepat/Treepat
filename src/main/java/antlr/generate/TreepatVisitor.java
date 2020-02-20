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
	 * Visit a parse tree produced by {@link TreepatParser#subtreeWrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtreeWrapper(TreepatParser.SubtreeWrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#depthClosure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepthClosure(TreepatParser.DepthClosureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(TreepatParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#depthTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepthTerm(TreepatParser.DepthTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreepatParser#breadthClosure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreadthClosure(TreepatParser.BreadthClosureContext ctx);
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