

/*Maya Purohit
 * Project04
 * 02/27/2023
 * CS231
 * StackTests.java tests the Stack class in the linked list class
 * To run: java -ea StackTests
 */

public class StackTests {

    public static void main(String[] args) {
        // case 1: testing LinkedList() and size()
        {
            // setup
            Stack<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.push(i);
            }

            // verify
            System.out.println(ll + " != null");
            System.out.println(ll.size() + " == 5"); //checks the size

            // test
            assert ll != null : "Error in LinkedList::LinkedList()";
            assert ll.size() == 5 : "Error in LinkedList::Stack.size()";
        }

        // case 2: testing peek() and push()
        {
            // setup
            Stack<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.push(i);
            }

            // verify
            System.out.println(ll.peek() + " == 4"); //ckecks the value that is peeked


            // test
            assert ll.peek() == 4 : "Error in LinkedList::Stack.peek()";
            
        }

        // case 3: testing pop()
        {
            // setup
            Stack<Integer> list1 = new LinkedList<Integer>();
            Stack<Integer> list2 = new LinkedList<Integer>();
            
            for (int i = 0; i < 5; i++) {
                list1.push(i);
                list2.push(4-i);
            }

            // verify
            System.out.println(list1.pop() + " == 4"); //checks the poll value
            System.out.println(list2.pop() + " == 0"); //checks the poll value 

            // test
            assert list1.pop() == 3: "Error in LinkedList::pop()";
            assert list2.pop() ==  1 : "Error in LinkedList::pop()";
        }

        // case 4: testing removeFirst()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 5; i++) {
                ll.add(4-i);
            }

            int remove0 = ll.removeFirst();
            int remove1 = ll.removeFirst();

            // verify
            System.out.println(remove0 + " == 0"); //checks the value that is removed 
            System.out.println(remove1 + " == 1");

            // test
            assert remove0 == 0 : "Error in LinkedList::removeFirst()";
            assert remove1 == 1 : "Error in LinkedList::removeFirst()";
        }

        // case 5: testing addFirst()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 8; i++) {
                ll.addFirst(7-i);
            }
            int valueFirst = ll.get(0);
            int valueLast = ll.get(7);


            // verify
            System.out.println(valueFirst + " == 0"); //ensures that the value at the start of the list is correct 
            System.out.println(valueLast + " == 7");

            // test
            assert valueFirst == 0 : "Error in LinkedList::addFirst()";
            assert valueLast == 7 : "Error in LinkedList::addFirst()";
          
        }

        // case 6: testing addLast()
        {
            // setup
            LinkedList<Integer> ll = new LinkedList<Integer>();
            for (int i = 0; i < 8; i++) {
                ll.addLast(7-i);
            }

            int valueFirst = ll.get(0); //checks the value at the front and end of the list 
            int valueLast = ll.get(7);


            // verify
            System.out.println(valueFirst + " == 7");
            System.out.println(valueLast + " == 0");

            // test
            assert valueFirst == 7 : "Error in LinkedList::addLast()";
            assert valueLast == 0 : "Error in LinkedList::addLast()";



            
        }
        System.out.println("Done testing Stack!");
    }

}

