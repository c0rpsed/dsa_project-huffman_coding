package dsa_project;

// Node class to store characters of string
// along with their frequency
public class Node {
        char val;
        int freq;
        Node left, right;
        Node(char val, int freq ) {
            this.val = val;
            this.freq = freq;
            this.left = null;
            this.right = null;
        }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public char getVal() {
        return val;
    }

    public void setVal(char val) {
        this.val = val;
    }
}
