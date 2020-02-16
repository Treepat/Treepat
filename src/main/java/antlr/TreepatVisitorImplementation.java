package antlr;

import antlr.generate.TreepatParser;
import antlr.generate.TreepatVisitor;
import ast.ASTNode;
import ast.Child;
import ast.Node;
import ast.Sibling;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import tree.ImpTargetTreeNode;
import tree.TargetTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class TreepatVisitorImplementation implements TreepatVisitor<ASTNode> {
    @Override
    public ASTNode visitModel(TreepatParser.ModelContext ctx) {
        TargetTreeNode targetTreeNode = new ImpTargetTreeNode();
        ctx.subtree().accept(this).execute(targetTreeNode);
        return null;
    }

    @Override
    public ASTNode visitSubtree(TreepatParser.SubtreeContext ctx) {
        ASTNode expression = ctx.expression().accept(this);
        if( ctx.child() == null )
        {
            return expression;
        }
        ASTNode child = ctx.child().accept(this);
        return new Child(expression, child);
    }

    @Override
    public ASTNode visitExpression(TreepatParser.ExpressionContext ctx) {
        if( ctx.simple_expression() != null )
        {
            return ctx.simple_expression().accept(this);
        }
        return ctx.depth_closure().accept(this);
    }

    @Override
    public ASTNode visitChild(TreepatParser.ChildContext ctx) {
        return ctx.sibling().accept(this);
    }

    @Override
    public ASTNode visitSibling(TreepatParser.SiblingContext ctx) {
        List<ASTNode> siblings;
        siblings = ctx.union().stream()
                .map(instruction -> instruction.accept(this))
                .collect(toList());
        return new Sibling(siblings);
    }

    @Override
    public ASTNode visitUnion(TreepatParser.UnionContext ctx) {
        List<ASTNode> nodes;
        nodes = ctx.subtree_wrapper().stream()
                .map(node -> node.accept(this))
                .collect(toList());
        return nodes.get(0);
    }

    @Override
    public ASTNode visitSubtree_wrapper(TreepatParser.Subtree_wrapperContext ctx) {
        return ctx.subtree().accept(this);
    }

    @Override
    public ASTNode visitDepth_closure(TreepatParser.Depth_closureContext ctx) {
        return ctx.child().accept(this);
    }

    @Override
    public ASTNode visitSimple_expression(TreepatParser.Simple_expressionContext ctx) {
        ASTNode value_to_return = null;
        if( ctx.term() != null )
        {
            value_to_return =ctx.term().accept(this);;
        }
        else if( ctx.breadth_closure() != null )
        {
            value_to_return = ctx.breadth_closure().accept(this);
        }
        else if( ctx.depth_term() != null )
        {
            value_to_return = ctx.depth_term().accept(this);
        }
        return  value_to_return;
    }

    @Override
    public ASTNode visitDepth_term(TreepatParser.Depth_termContext ctx) {
        return ctx.term().accept(this);
    }

    @Override
    public ASTNode visitBreadth_closure(TreepatParser.Breadth_closureContext ctx) {
        return ctx.term().accept(this);
    }

    @Override
    public ASTNode visitTerm(TreepatParser.TermContext ctx) {
        return ctx.node().accept(this);
    }

    @Override
    public ASTNode visitNode(TreepatParser.NodeContext ctx) {
        return new Node(ctx.name.getText());
    }

    @Override
    public ASTNode visit(ParseTree parseTree) {
        return parseTree.accept(this);
    }

    @Override
    public ASTNode visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public ASTNode visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public ASTNode visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
