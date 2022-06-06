package dsa_project;

// implemented through a doubly linked list
// stores the tree node that contains characters with frequency
// in order of increasing frequency

class Element{
        Node node;
        Element next;
        Element prev;

        Element(Node node){
            this.node = node;
            this.next = null;
            this.prev = null;
        }
    }

public class MinPriorityQ {

    Element head,tail;
    int size;

// inserting node into the queue
    void Enqueue(char val, int freq){
       Node node = new Node(val,freq);
       Enqueue(node);
    }

// overloaded method for Enqueueing
    void Enqueue(Node node){
        Element e = new Element(node);

        // if head is null, make the new node head and tail
        if(head == null){
            head = e;
            tail = e;

        // if the incoming node has frequency less than head
        // we make that node, head.
        } else if ( head.node.getFreq() >= node.getFreq()){
            e.next = head;
            head.prev = e;
            head = e;

        // if the incoming node has frequency greater than tail
        // we make it the tail
        } else if ( tail.node.getFreq() <= node.getFreq() ){
            tail.next = e;
            e.prev = tail;
            tail = e;

        // else we iterate through the list
        // and find its correct spot.
        } else {
            Element temp = head;
            while( temp.node.getFreq() < node.getFreq() ){
                temp = temp.next;
            }
            e.next = temp;
            if( temp.prev != null ){
                temp.prev.next = e;
            }
            e.prev = temp.prev;
            temp.prev = e;
        }
        size++;

    }

    // print the list of nodes
    void print(){
        Element temp = head;
        System.out.println("Node Val\tNode Freq");
        while(temp != null){
            System.out.println(temp.node.getVal()+"\t\t\t"+temp.node.getFreq());
            temp = temp.next;
        }
    }

    // Removing elements by moving head one step up
    // at each call until its null.
    Node Dequeue(){
        if (head == null){
            return null;
        } else {
            Node temp = head.node;
            if (head == tail){
               tail = null;
               head = null;
            }
            else{
                head = head.next;
                head.prev = null;
            }
            size--;
            return temp;
        }
    }

    // returning the size of the priority queue.
    int size(){
        return size;
    }

}
