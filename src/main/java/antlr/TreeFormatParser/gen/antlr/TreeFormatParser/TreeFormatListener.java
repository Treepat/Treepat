// Generated from D:/doria/Documents/GitHub/Treepat/src/main/java/antlr/TreeFormatParser\TreeFormat.g4 by ANTLR 4.8
package antlr.TreeFormatParser.gen.antlr.TreeFormatParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TreeFormatParser}.
 */
public interface TreeFormatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#subtree}.
	 * @param ctx the parse tree
	 */
	void enterSubtree(TreeFormatParser.SubtreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#subtree}.
	 * @param ctx the parse tree
	 */
	void exitSubtree(TreeFormatParser.SubtreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#child}.
	 * @param ctx the parse tree
	 */
	void enterChild(TreeFormatParser.ChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#child}.
	 * @param ctx the parse tree
	 */
	void exitChild(TreeFormatParser.ChildContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#sibling}.
	 * @param ctx the parse tree
	 */
	void enterSibling(TreeFormatParser.SiblingContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#sibling}.
	 * @param ctx the parse tree
	 */
	void exitSibling(TreeFormatParser.SiblingContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#wrapper}.
	 * @param ctx the parse tree
	 */
	void enterWrapper(TreeFormatParser.WrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#wrapper}.
	 * @param ctx the parse tree
	 */
	void exitWrapper(TreeFormatParser.WrapperContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(TreeFormatParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(TreeFormatParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeFormatParser#information}.
	 * @param ctx the parse tree
	 */
	void enterInformation(TreeFormatParser.InformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeFormatParser#information}.
	 * @param ctx the parse tree
	 */
	void exitInformation(TreeFormatParser.InformationContext ctx);
}