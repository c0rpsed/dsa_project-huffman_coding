package dsa_project;

import java.util.LinkedList;
import java.util.Queue;

// A Tree to represent to contain the unique characters
// based on their frequency, low frequency characters end up in the bottom
// while high frequency characters end up high in the tree.
// A node containing a character from the string is always the leaf node.
public class HuffmanTree {
    Node root;

// Populating the tree.
    void insert(MinPriorityQ q) {

        // we insert nodes while queue size is greater than 1
        // when size is 1 we will have the root of the tree stored in the queue.
        while(q.size() > 1){
        // the first two elements with lowest frequency becomes the left right.
        Node left = q.Dequeue();
        Node right = q.Dequeue();

        // their parent is a new node with no character from the String
        // and a frequency that is the sum of the frequency of its child nodes.
        Node parent = new Node('-', left.freq+right.freq);
        parent.left = left;
        parent.right = right;

        // sending the new node into the queue.
        q.Enqueue(parent);
        }
        root = q.Dequeue();
    }

    // printing the Huffman Tree by level order traversal.
    void print() {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        System.out.println("Node val \t Node freq \t Left \t Right");
        while (!(queue.isEmpty())) {

            Node currentNode = queue.remove();
            System.out.print(currentNode.val + " \t\t\t " + currentNode.freq);
            // try catch block to print the left and right node of the current node
            // do nothing if it's a leaf node.
            try{
                System.out.print("\t\t\t\t"+currentNode.left.val + "\t\t" + currentNode.right.val);
            }catch(Exception e){

            }
            System.out.println();
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);

            }
        }
    }

}
