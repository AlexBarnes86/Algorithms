package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node clone(Node node, Map<Integer, Node> visited) {
        if(node == null) {
            return null;
        }

        Node clone = new Node(node.val);
        visited.put(node.val, clone);
        for(Node neighbor : node.neighbors) {
            if(!visited.containsKey(neighbor.val)) {
                clone.neighbors.add(clone(neighbor, visited));
            }
            else {
                clone.neighbors.add(visited.get(neighbor.val));
            }
        }
        return clone;
    }

    public Node cloneGraph(Node node) {
        return clone(node, new HashMap<>());
    }
}
