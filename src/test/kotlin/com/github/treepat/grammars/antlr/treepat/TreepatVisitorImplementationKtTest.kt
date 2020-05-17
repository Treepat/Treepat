package com.github.treepat.grammars.antlr.treepat

import antlr.treepat.TreepatParser
import com.github.treepat.grammars.ast.ASTNode
import com.github.treepat.grammars.ast.BreadthClosure
import com.github.treepat.grammars.ast.Child
import com.github.treepat.grammars.ast.DepthClosure
import com.github.treepat.grammars.ast.DepthTerm
import com.github.treepat.grammars.ast.Dot
import com.github.treepat.grammars.ast.Node
import com.github.treepat.grammars.ast.Union
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class TreepatVisitorImplementationKtTest {

    private val treepatVisitorImplementation = TreepatVisitorImplementation()

    @MockK
    private lateinit var mockToken: Token

    @MockK
    private lateinit var mockRuleNode: RuleNode

    @MockK
    private lateinit var mockErrorNode: ErrorNode

    @MockK
    private lateinit var mockTerminalNode: TerminalNode

    @MockK
    private lateinit var mockParseTree: ParseTree

    @MockK
    private lateinit var mockASTNode: ASTNode

    @MockK
    private lateinit var mockSubtreeContext: TreepatParser.SubtreeContext

    @MockK
    private lateinit var mockDotContext: TreepatParser.DotContext

    @MockK
    private lateinit var mockDepthClosureContext: TreepatParser.DepthClosureContext

    @MockK
    private lateinit var mockDepthTermContext: TreepatParser.DepthTermContext

    @MockK
    private lateinit var mockNestedIndentContext: TreepatParser.NestedIndentContext

    @MockK
    private lateinit var mockAtomTermContext: TreepatParser.AtomTermContext

    @MockK
    private lateinit var mockNestedContext: TreepatParser.NestedContext

    @MockK
    private lateinit var mockAtomTermWrapperContext: TreepatParser.AtomTermWrapperContext

    @MockK
    private lateinit var mockBreadthClosureContext: TreepatParser.BreadthClosureContext

    @MockK
    private lateinit var mockNodeContext: TreepatParser.NodeContext

    @MockK
    private lateinit var mockIndentContext: TreepatParser.IndentContext

    @MockK
    private lateinit var mockIndentWrapperContext: TreepatParser.IndentWrapperContext

    @MockK
    private lateinit var mockUnionContext: TreepatParser.UnionContext

    @MockK
    private lateinit var mockSiblingContext: TreepatParser.SiblingContext

    @MockK
    private lateinit var mockChildContext: TreepatParser.ChildContext

    @Test
    fun `visitSubtree should call sibling subtree`() {
        // arrange
        every { mockSubtreeContext.sibling().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitSubtree(mockSubtreeContext)
        // assert
        verify(exactly = 1) { mockSubtreeContext.sibling().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitDot should return Dot object`() {
        // act
        val result = treepatVisitorImplementation.visitDot(mockDotContext)
        // assert
        verify { mockDotContext wasNot Called }
        assert(result is Dot)
    }

    @Test
    fun `visitDepthClosure should call indentWrapper visitor`() {
        // arrange
        every { mockDepthClosureContext.indentWrapper().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitDepthClosure(mockDepthClosureContext)
        // assert
        verify(exactly = 1) { mockDepthClosureContext.indentWrapper().accept(treepatVisitorImplementation) }
        assert(result is DepthClosure)
        assertEquals(mockASTNode, (result as DepthClosure).expression)
    }

    @Test
    fun `visitDepthTerm should call node visitor`() {
        // arrange
        every { mockDepthTermContext.node().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitDepthTerm(mockDepthTermContext)
        // assert
        verify(exactly = 1) { mockDepthTermContext.node().accept(treepatVisitorImplementation) }
        assert(result is DepthTerm)
        assertEquals(mockASTNode, (result as DepthTerm).node)
    }

    @Test
    fun `visit should call parseTree visitor`() {
        // arrange
        every { mockParseTree.accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visit(mockParseTree)
        // assert
        verify(exactly = 1) { mockParseTree.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitIndent should call of subtree visitor`() {
        // arrange
        every { mockIndentContext.subtree().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitIndent(mockIndentContext)
        // assert
        verify(exactly = 1) { mockIndentContext.subtree().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitNestedIndent should call indent visitor`() {
        // arrange
        every { mockNestedIndentContext.indent().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitNestedIndent(mockNestedIndentContext)
        // assert
        verify(exactly = 1) { mockNestedIndentContext.indent().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitAtomTerm should call atomTermWrapper visitor`() {
        // arrange
        every { mockAtomTermContext.atomTermWrapper().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitAtomTerm(mockAtomTermContext)
        // assert
        verify(exactly = 1) { mockAtomTermContext.atomTermWrapper().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitNested should call subtree visitor`() {
        // arrange
        every { mockNestedContext.subtree().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitNested(mockNestedContext)
        // assert
        verify(exactly = 1) { mockNestedContext.subtree().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitChildren should throw NotImplementedError`() {
        // assert
        val error = assertFailsWith<NotImplementedError> { treepatVisitorImplementation.visitChildren(mockRuleNode) }
        verify { mockRuleNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    @Test
    fun `visitErrorNode should throw NotImplementedError`() {
        // assert
        val error = assertFailsWith<NotImplementedError> { treepatVisitorImplementation.visitErrorNode(mockErrorNode) }
        verify { mockErrorNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    @Test
    fun `visitTerminal should throw NotImplementedError`() {
        // assert
        val error =
            assertFailsWith<NotImplementedError> { treepatVisitorImplementation.visitTerminal(mockTerminalNode) }
        verify { mockTerminalNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    private fun initializeMockAtomTermWrapperContextToNull() {
        every { mockAtomTermWrapperContext.depthClosure() } returns null
        every { mockAtomTermWrapperContext.depthTerm() } returns null
        every { mockAtomTermWrapperContext.node() } returns null
        every { mockAtomTermWrapperContext.nested() } returns null
    }

    @Test
    fun `visitAtomTermWrapper should call nested visitor if nested is not null`() {
        // arrange
        initializeMockAtomTermWrapperContextToNull()
        every { mockNestedContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockAtomTermWrapperContext.nested() } returns mockNestedContext
        // act
        val result = treepatVisitorImplementation.visitAtomTermWrapper(mockAtomTermWrapperContext)
        // assert
        verify { mockAtomTermWrapperContext.nested() }
        verify(exactly = 1) { mockNestedContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitAtomTermWrapper should call depthTerm visitor if depthTerm is not null`() {
        // arrange
        initializeMockAtomTermWrapperContextToNull()
        every { mockDepthTermContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockAtomTermWrapperContext.depthTerm() } returns mockDepthTermContext
        assertNull(mockAtomTermWrapperContext.depthClosure())
        // act
        val result = treepatVisitorImplementation.visitAtomTermWrapper(mockAtomTermWrapperContext)
        // assert
        verify { mockAtomTermWrapperContext.depthTerm() }
        verify(exactly = 1) { mockDepthTermContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitAtomTermWrapper should call depthClosure if depthClosure visitor is not null`() {
        // arrange
        initializeMockAtomTermWrapperContextToNull()
        every { mockDepthClosureContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockAtomTermWrapperContext.depthClosure() } returns mockDepthClosureContext
        // act
        val result = treepatVisitorImplementation.visitAtomTermWrapper(mockAtomTermWrapperContext)
        // assert
        verify { mockAtomTermWrapperContext.depthClosure() }
        verify(exactly = 1) { mockDepthClosureContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitAtomTermWrapper should call node visitor as default`() {
        // arrange
        initializeMockAtomTermWrapperContextToNull()
        every { mockNodeContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockAtomTermWrapperContext.node() } returns mockNodeContext
        // act
        val result = treepatVisitorImplementation.visitAtomTermWrapper(mockAtomTermWrapperContext)
        // assert
        verify { mockAtomTermWrapperContext.node() }
        verify(exactly = 1) { mockNodeContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitBreadthClosure should call atomTerm visitor if ASTERISK is null`() {
        // arrange
        every { mockBreadthClosureContext.ASTERISK() } returns null
        every { mockBreadthClosureContext.atomTerm().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitBreadthClosure(mockBreadthClosureContext)
        // assert
        verify { mockBreadthClosureContext.ASTERISK() }
        verify { mockBreadthClosureContext.atomTerm().accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitBreadthClosure should return BreadthClosure with atomTerm visitor`() {
        // arrange
        every { mockBreadthClosureContext.ASTERISK() } returns mockTerminalNode
        every { mockBreadthClosureContext.atomTerm().accept(treepatVisitorImplementation) } returns mockASTNode
        // act
        val result = treepatVisitorImplementation.visitBreadthClosure(mockBreadthClosureContext)
        // assert
        verify { mockTerminalNode wasNot Called }
        verify { mockBreadthClosureContext.atomTerm().accept(treepatVisitorImplementation) }
        assert(result is BreadthClosure)
        assertEquals(mockASTNode, (result as BreadthClosure).expression)
    }

    @Test
    fun `visitNode should call dot visitor if dot is not null`() {
        // arrange
        every { mockDotContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockNodeContext.dot() } returns mockDotContext
        // act
        val result = treepatVisitorImplementation.visitNode(mockNodeContext)
        // assert
        verify { mockDotContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitNode should return Node if dot is null`() {
        // arrange
        val tNodeName = "Node name"
        every { mockToken.text } returns tNodeName
        every { mockNodeContext.dot() } returns null
        mockNodeContext.name = mockToken
        // act
        val result = treepatVisitorImplementation.visitNode(mockNodeContext)
        // assert
        verify { mockDotContext wasNot Called }
        assert(result is Node)
        assertEquals(tNodeName, (result as Node).name)
    }

    @Test
    fun `visitIndentWrapper should call indent visitor if indent is not null`() {
        // arrange
        every { mockIndentContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockIndentWrapperContext.indent() } returns mockIndentContext
        // act
        val result = treepatVisitorImplementation.visitIndentWrapper(mockIndentWrapperContext)
        // assert
        verify { mockIndentContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitIndentWrapper should call nestedIndent visitor if nestedIndent is not null`() {
        // arrange
        every { mockIndentWrapperContext.indent() } returns null
        every { mockNestedIndentContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockIndentWrapperContext.nestedIndent() } returns mockNestedIndentContext
        // act
        val result = treepatVisitorImplementation.visitIndentWrapper(mockIndentWrapperContext)
        // assert
        verify { mockNestedIndentContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
    }

    @Test
    fun `visitUnion should call child visitor for every child and return first expression if only one child`() {
        // arrange
        val tChildren = listOf(mockChildContext)
        every { mockChildContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockUnionContext.child() } returns tChildren
        // act
        val result = treepatVisitorImplementation.visitUnion(mockUnionContext)
        // assert
        verify(exactly = tChildren.size) { mockChildContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
        assertFalse(result is Union)
    }

    @Test
    fun `visitUnion should call child visitor for every child and return Union with all children`() {
        // arrange
        val tChildren = listOf(mockChildContext, mockChildContext, mockChildContext)
        every { mockChildContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockUnionContext.child() } returns tChildren
        // act
        val result = treepatVisitorImplementation.visitUnion(mockUnionContext)
        // assert
        verify(exactly = tChildren.size) { mockChildContext.accept(treepatVisitorImplementation) }
        assert(result is Union)
        assertEquals(tChildren.map { mockASTNode }, (result as Union).expressions)
    }

    @Test
    fun `visitSibling should call child union for every child and return first expression if only one child`() {
        // arrange
        val tUnions = listOf(mockUnionContext)
        every { mockUnionContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockSiblingContext.union() } returns tUnions
        // act
        val result = treepatVisitorImplementation.visitSibling(mockSiblingContext)
        // assert
        verify(exactly = tUnions.size) { mockUnionContext.accept(treepatVisitorImplementation) }
        assertEquals(mockASTNode, result)
        assertFalse(result is Union)
    }

    @Test
    fun `visitChild should call breadthClosure visitor if indentWrapper is null`() {
        // arrange
        every { mockChildContext.breadthClosure().accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockChildContext.indentWrapper() } returns null
        // act
        val result = treepatVisitorImplementation.visitChild(mockChildContext)
        // assert
        verify { mockChildContext.breadthClosure().accept(treepatVisitorImplementation) }
        verify { mockChildContext.indentWrapper() }
        assertEquals(mockASTNode, result)
        assertFalse(result is Child)
    }

    @Test
    fun `visitChild should return a Child with child as the indented element`() {
        // arrange
        every { mockChildContext.breadthClosure().accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockIndentWrapperContext.accept(treepatVisitorImplementation) } returns mockASTNode
        every { mockChildContext.indentWrapper() } returns mockIndentWrapperContext
        // act
        val result = treepatVisitorImplementation.visitChild(mockChildContext)
        // assert
        verify { mockChildContext.breadthClosure().accept(treepatVisitorImplementation) }
        verify { mockIndentWrapperContext.accept(treepatVisitorImplementation) }
        assert(result is Child)
        assertEquals(mockASTNode, (result as Child).child)
        assertEquals(mockASTNode, result.father)
    }
}
