package grammars.antlr.tree_format

import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.ImpTargetTreeNode
import target_tree.TargetTreeNode
import tree_format.TreeFormatParser

@ExtendWith(MockKExtension::class)
internal class TreeFormatVisitorImplementationKtTest {

    private val treeFormatVisitorImplementation = TreeFormatVisitorImplementation()

    @MockK
    private lateinit var mockParseTree: ParseTree

    @MockK
    private lateinit var mockRuleNode: RuleNode

    @MockK
    private lateinit var mockErrorNode: ErrorNode

    @MockK
    private lateinit var mockTerminalNode: TerminalNode

    @MockK
    private lateinit var mockTargetTree: TargetTreeNode

    @MockK
    private lateinit var mockChildContext: TreeFormatParser.ChildContext

    @MockK
    private lateinit var mockNodeContext: TreeFormatParser.NodeContext

    @MockK
    private lateinit var mockInformationContextName: TreeFormatParser.InformationContext

    @MockK
    private lateinit var mockInformationContextTag: TreeFormatParser.InformationContext

    @MockK
    private lateinit var mockInformationContext: TreeFormatParser.InformationContext

    @MockK
    private lateinit var mockSubtreeContext: TreeFormatParser.SubtreeContext

    @MockK
    private lateinit var mockSiblingContext: TreeFormatParser.SiblingContext

    @Test
    fun `visitChild should call sibling visitor`() {
        // arrange
        every { mockChildContext.sibling().accept(treeFormatVisitorImplementation) } returns mockTargetTree
        // act
        val result = treeFormatVisitorImplementation.visitChild(mockChildContext)
        // assert
        verify { mockChildContext.sibling().accept(treeFormatVisitorImplementation) }
        assertEquals(mockTargetTree, result)
    }

    @Test
    fun `visitNode should return ImpTargetTreeNode with context information`() {
        // arrange
        every { mockInformationContextName.text } returns "Name"
        every { mockInformationContextTag.text } returns "Tag"
        mockNodeContext.name = mockInformationContextName
        mockNodeContext.tag = mockInformationContextTag
        // act
        val result = treeFormatVisitorImplementation.visitNode(mockNodeContext)
        // assert
        assertNotNull(result as? ImpTargetTreeNode)
        assertEquals("Name", result.name)
        assertEquals("Tag", result.tag)
    }

    @Test
    fun `visitInformation should return empty ImpTargetTreeNode`() {
        // act
        val result = treeFormatVisitorImplementation.visitInformation(mockInformationContext)
        // assert
        assertEquals(ImpTargetTreeNode().id, result.id)
    }

    @Test
    fun `visit should call parseTree visitor`() {
        // arrange
        every { mockParseTree.accept(treeFormatVisitorImplementation) } returns mockTargetTree
        // act
        val result = treeFormatVisitorImplementation.visit(mockParseTree)
        // assert
        verify { mockParseTree.accept(treeFormatVisitorImplementation) }
        assertEquals(mockTargetTree, result)
    }

    @Test
    fun `visitChildren should throw NotImplementedError`() {
        // assert
        val error = assertFailsWith<NotImplementedError> { treeFormatVisitorImplementation.visitChildren(mockRuleNode) }
        verify { mockRuleNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    @Test
    fun `visitErrorNode should throw NotImplementedError`() {
        // assert
        val error = assertFailsWith<NotImplementedError> { treeFormatVisitorImplementation.visitErrorNode(mockErrorNode) }
        verify { mockErrorNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    @Test
    fun `visitTerminal should throw NotImplementedError`() {
        // assert
        val error =
            assertFailsWith<NotImplementedError> { treeFormatVisitorImplementation.visitTerminal(mockTerminalNode) }
        verify { mockTerminalNode wasNot Called }
        assertEquals("This method is not supported.", error.message)
    }

    @Test
    fun `visitSubtree should return ImpTargetTreeNode if child is null`() {
        // arrange
        val tTargetTreeNode = ImpTargetTreeNode()
        every { mockSubtreeContext.child() } returns null
        every { mockSubtreeContext.node().accept(treeFormatVisitorImplementation) } returns tTargetTreeNode
        // act
        val result = treeFormatVisitorImplementation.visitSubtree(mockSubtreeContext)
        // assert
        verify { mockSubtreeContext.child() }
        verify { mockSubtreeContext.node().accept(treeFormatVisitorImplementation) }
        assertEquals(tTargetTreeNode, result)
    }

    @Test
    fun `visitSubtree should return ImpTargetTreeNode with list of children when child is null`() {
        // arrange
        val tTargetTreeNodeImpl = ImpTargetTreeNode(children = listOf(ImpTargetTreeNode(), ImpTargetTreeNode()))
        every { mockChildContext.accept(treeFormatVisitorImplementation) } returns tTargetTreeNodeImpl
        every { mockSubtreeContext.child() } returns mockChildContext
        every { mockSubtreeContext.node().accept(treeFormatVisitorImplementation) } returns tTargetTreeNodeImpl
        // act
        val result = treeFormatVisitorImplementation.visitSubtree(mockSubtreeContext)
        // assert
        verify { mockSubtreeContext.child() }
        verify { mockSubtreeContext.node().accept(treeFormatVisitorImplementation) }
        assertEquals(tTargetTreeNodeImpl.children, result.children)
    }

    @Test
    fun `visitSibling should return ImpTargetTreeNode with children list`() {
        // arrange
        val tChildren = listOf(mockSubtreeContext, mockSubtreeContext)
        every { mockSubtreeContext.accept(treeFormatVisitorImplementation) } returns mockTargetTree
        every { mockSiblingContext.subtree() } returns tChildren
        // act
        val result = treeFormatVisitorImplementation.visitSibling(mockSiblingContext)
        // assert
        verify(exactly = tChildren.size) { mockSubtreeContext.accept(treeFormatVisitorImplementation) }
        verify { mockSiblingContext.subtree() }
        assertEquals(tChildren.map { mockTargetTree }, result.children)
    }
}
