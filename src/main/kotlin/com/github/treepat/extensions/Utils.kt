package com.github.treepat.extensions

import com.github.treepat.target_tree.TargetTreeNode

fun List<TargetTreeNode>.matchToString(): String = this.first().getRoot().matchedNodesString(this)
