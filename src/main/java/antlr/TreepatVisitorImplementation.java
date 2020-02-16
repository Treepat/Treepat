package antlr;

import antlr.generate.TreepatParser;
import antlr.generate.TreepatVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TreepatVisitorImplementation implements TreepatVisitor {
    @Override
    public Object visitModel(TreepatParser.ModelContext ctx) {
        return null;
    }

    @Override
    public Object visitSubtree(TreepatParser.SubtreeContext ctx) {
        return null;
    }

    @Override
    public Object visitExpression(TreepatParser.ExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitChild(TreepatParser.ChildContext ctx) {
        return null;
    }

    @Override
    public Object visitSibling(TreepatParser.SiblingContext ctx) {
        return null;
    }

    @Override
    public Object visitUnion(TreepatParser.UnionContext ctx) {
        return null;
    }

    @Override
    public Object visitSubtree_wrapper(TreepatParser.Subtree_wrapperContext ctx) {
        return null;
    }

    @Override
    public Object visitDepth_closure(TreepatParser.Depth_closureContext ctx) {
        return null;
    }

    @Override
    public Object visitSimple_expression(TreepatParser.Simple_expressionContext ctx) {
        return null;
    }

    @Override
    public Object visitDepth_term(TreepatParser.Depth_termContext ctx) {
        return null;
    }

    @Override
    public Object visitBreadth_closure(TreepatParser.Breadth_closureContext ctx) {
        return null;
    }

    @Override
    public Object visitTerm(TreepatParser.TermContext ctx) {
        return null;
    }

    @Override
    public Object visitNode(TreepatParser.NodeContext ctx) {
        return null;
    }

    @Override
    public Object visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
