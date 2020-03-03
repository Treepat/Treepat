// Generated from D:/doria/Documents/GitHub/Treepat/src/main/java/antlr/TreeFormatParser\TreeFormat.g4 by ANTLR 4.8
package antlr.TreeFormatParser.gen.antlr.TreeFormatParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TreeFormatParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TreeFormatVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#subtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtree(TreeFormatParser.SubtreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#child}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChild(TreeFormatParser.ChildContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#sibling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSibling(TreeFormatParser.SiblingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#wrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrapper(TreeFormatParser.WrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(TreeFormatParser.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TreeFormatParser#information}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInformation(TreeFormatParser.InformationContext ctx);
}